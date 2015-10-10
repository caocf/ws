package com.cplatform.mall.back.giftcard.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.giftcard.dao.GiftCardDao;
import com.cplatform.mall.back.giftcard.dao.GiftRequiredDao;
import com.cplatform.mall.back.giftcard.entity.GiftCard;
import com.cplatform.mall.back.giftcard.entity.GiftRequired;
import com.cplatform.mall.back.utils.Constants;
import com.cplatform.mall.back.utils.GiftCardUtils;

/**
 * 礼品卡(冻结、挂失、解挂)
 * @author zhangdong
 */
@Service
public class GiftCardService {

	@Autowired
	private DbHelper dbHelper;
	
	@Autowired
	private GiftCardDao giftCardDao;
	
	@Autowired
	private GiftRequiredDao giftRequiredDao;
	
	@Autowired
	private GiftCardUtils giftCardUtils;
	
	/**
	 * 礼品卡列表
	 * @param giftCard
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<GiftCard> findGiftCardPage(GiftCard giftCard, int pageNo, int pageSize) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.setLength(0);
		sqlBuilder.append("SELECT t.*,u.user_name user_name FROM T_GIFT_CARD t");
		sqlBuilder.append(" LEFT JOIN t_sys_user u on u.id = t.MEMBER_ID WHERE 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		if (giftCard != null) {
			if(giftCard.getStatus() == null){
				return null;
			}else {
				sqlBuilder.append(" AND t.status = ? " );
				params.add(giftCard.getStatus());
			}
			if(StringUtils.isNotBlank(giftCard.getSerialNo())){
				sqlBuilder.append(" AND t.SERIAL_NO = ? ");
				params.add(giftCard.getSerialNo());
			}
			if (giftCard.getBatchNo() != null) {
				sqlBuilder.append(" AND t.BATCH_NO  = ? ");
				params.add(giftCard.getBatchNo());
			}
		}
		return dbHelper.getPage(sqlBuilder.toString(), GiftCard.class, pageNo, pageSize, params.toArray());
	}
	
	/**
	 * 查找礼品卡详情
	 * @param serialNo
	 * @return
	 * @throws Exception
	 */
	public GiftCard findGiftCard(String serialNo) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.setLength(0);
		sqlBuilder.append("SELECT t.*,u.user_name user_name FROM T_GIFT_CARD t");
		sqlBuilder.append(" LEFT JOIN t_sys_user u on u.id = t.MEMBER_ID WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (serialNo != null) {
			sqlBuilder.append(" AND t.SERIAL_NO = ? ");
			params.add(serialNo);
		}
		return dbHelper.getBean(sqlBuilder.toString(), GiftCard.class, params.toArray());
	}
	
	/**
	 * 判断该礼品卡状态
	 * @param serialNo 礼品卡序列号
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> checkGiftCard(String serialNo, Long status, int type) {
		GiftCard op = this.giftCardDao.findGiftCardBySerialNoAndStatus(serialNo, status);
		Map<String, Object> map = new HashMap<String, Object>();
		if (op == null) {
			map.put("rs", false);
			map.put("rs_message", getOper(type));
			return map;
		}
		map.put("rs", true);
		return map;
	}
	
	/**
	 * 更改礼品卡状态
	 * @param giftCard
	 * @return
	 * @throws SQLException 
	 */
	@Transactional
	public Map<String, Object> modifyGiftCard(GiftCard giftCard, int type, String content) throws SQLException{
		Map<String, Object> map = null;
		Long status = (long) 0;
		if(giftCard != null && giftCard.getSerialNo() != null){
			map = new HashMap<String, Object>();
			//进行验证
			String message = checkGiftCard(giftCard);
			if(StringUtils.isNotBlank(message)){
				map.put("result", false);
				map.put("message", message);
				return map;
			}
			//继续对卡状态验证
			if(1 == type){
				status = Constants.GIFTCARD_STATUS_ACTIVATE;
				giftCard.setStatus(Constants.GIFTCARD_STATUS_BLOCK);
			}else if(2 == type){
				status = Constants.GIFTCARD_STATUS_ACTIVATE;
				giftCard.setStatus(Constants.GIFTCARD_STATUS_LOSS);
			}else if(3 == type){
				status = Constants.GIFTCARD_STATUS_LOSS;
				giftCard.setStatus(Constants.GIFTCARD_STATUS_ACTIVATE);
			}
			Map<String, Object> mapCheck = checkGiftCard(giftCard.getSerialNo(), status, type);
			if((Boolean) mapCheck.get("rs")){
				//调接口
				boolean operateFlag = giftCardUtils.operateCard(giftCard.getSerialNo(), String.valueOf(type), content);
				if(operateFlag){
					String sql = "UPDATE T_GIFT_CARD t SET t.STATUS = ? WHERE t.SERIAL_NO = ? ";
					int result = dbHelper.execute(sql, giftCard.getStatus(), giftCard.getSerialNo());
					if(result >= 1){
						map.put("result", true);
						return map;
					}
				}else{
					map.put("result", false);
					if(1 == type){
						map.put("message", "冻结失败");
					}else if(2 == type){
						map.put("message", "挂失失败");
					}else if(3 == type){
						map.put("message", "解挂失败");
					}
					return map;
				}
			}else{
				map.put("result", false);
				map.put("message", mapCheck.get("rs_message"));
				return map;
			}
		}
		map.put("result", false);
		map.put("message", "操作失败");
		return map;
	}
	
	/**
	 * 判断当前卡是否存在
	 * @param giftCard
	 * @return
	 */
	private Map<String, Object> isHaveGiftCard(GiftCard giftCard){
		Map<String, Object> map = new HashMap<String, Object>();
		GiftCard gCard = giftCardDao.findOne(giftCard.getSerialNo());
		if(gCard != null){
			map.put("resule", true);
			map.put("batchNo", gCard.getBatchNo());
			return map;
		}
		map.put("resule", false);
		map.put("message", Constants.MESSAGE_GIFTCARD_HAVENO);
		return map;
	}
	
	/**
	 * 判断此卡是否失效
	 * @param giftCard
	 * @return
	 */
	private String isExpiryGiftCard(Long batchNo){
		GiftRequired gRequired = giftRequiredDao.findOne(batchNo);
		//当前批次过期日期
		Long expiryDate = Long.parseLong(gRequired.getExpiryDate());
		Long currentTime = Long.parseLong(TimeUtil.now());
		if(expiryDate > currentTime){
			return "";
		}
		return Constants.MESSAGE_GIFTCARD_EXPIRY;
	}
	
	/**
	 * 验证礼品卡
	 * @param giftCard
	 * @return
	 */
	private String checkGiftCard(GiftCard giftCard){
		Map<String, Object> map = isHaveGiftCard(giftCard);
		if((Boolean) map.get("resule")){
			//存在验证是否失效
			return isExpiryGiftCard(Long.parseLong(map.get("batchNo").toString()));
		}else{
			return map.get("message").toString();
		}
	}
	
	/**
	 * 获取操作动作
	 * @return
	 */
	private String getOper(int type){
		String operName = "";
		switch (type) {
		case 1:
			operName = "冻结操作，礼品卡应为激活状态";
			break;
		case 2:
			operName = "挂失操作，礼品卡应为激活状态";
			break;
		case 3:
			operName = "解挂操作，礼品卡应为挂失状态";
			break;
		}
		return operName;
	}
}
