package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.TItemComment;
import com.cplatform.b2c.model.TMemberFavorite;
import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.repository.ItemRepository;
import com.cplatform.b2c.repository.ItemSaleDao;
import com.cplatform.b2c.repository.VSearchGoodDao;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.SysTypeUtil;

/**
 * 商品服务类 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) May 29, 2013 4:22:44 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class ItemService {

	@Autowired
	private SysTypeUtil sysTypeUtil;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	AppConfig appConfig;

	@Autowired
	private VSearchGoodDao goodDao;

	@Autowired
	private ItemSaleDao itemSaleDao;

	/**
	 * 获取商品评论
	 * 
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getItemComments(PageInfo pageInfo, String saleId) {
		return itemRepository.getItemComments(pageInfo, saleId);
	}

	public String getTotalComment(String saleId) {
		return itemRepository.getTotalComment(saleId);
	}

	/**
	 * 获取分页
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getItemCommentsScript(PageInfo pageInfo, String saleId) {
		return itemRepository.getItemCommentsScript(pageInfo, saleId);
	}

	/**
	 * 获取会员等级
	 * 
	 * @return
	 */
	public String getMemberLevel(Integer redMember) {
		String level = "L0";
		if (1 == redMember) {
			level = "L1";
		}
		return level;
	}

	/**
	 * 获取商品套餐信息
	 * 
	 * @param itemId
	 * @return
	 */
	public JSONObject getGroupFromInterface(String itemId) {
		String resp = CommonUtils.getResponseFromServer(appConfig.getInterfaceGroupInfo() + "?saleId=" + itemId, "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(resp);
		if (jsonObject.getJSONArray("group").isEmpty()) {
			return null;
		} else {
			return jsonObject;
		}
	}

	/**
	 * 获取商品物流等信息
	 * 
	 * @param saleId
	 * @return
	 */
	public List<String[]> getBasicInfo(String saleId) {
		return itemRepository.getBasicInfo(saleId);
	}

	/**
	 * 获取商品浏览记录
	 * 
	 * @param saleId
	 * @return
	 */
	public List<VSearchGood> getHistoryItem(String saleId) {
		String[] saleIds = saleId.split(",");
		Integer[] ids = new Integer[saleIds.length];
		for (int i = 0; i < saleIds.length; i++) {
			ids[i] = Integer.valueOf(saleIds[i]);
		}
		return goodDao.getList(ids);
	}

	/**
	 * 获取商品浏览记录
	 * 
	 * @param saleId
	 * @return
	 */
	public List<ItemSaleDataDTO> getHistoryItems(String saleId) {
		// 判断saleId是否为空
		if (StringUtils.isNotBlank(saleId)) {
			String[] itemIds = saleId.split(",");
			if (null != itemIds && itemIds.length > 0) {
				return itemSaleDao.findHistoryItems(itemIds);
			}
		}
		return null;
	}

	/**
	 * 获取商品成交记录
	 * 
	 * @param pageInfo
	 * @param saleIds
	 * @return
	 */
	public List<Object[]> getPurchaseRecords(PageInfo pageInfo, String saleId) {
		return itemRepository.getPurchaseRecords(pageInfo, saleId);
	}

	/**
	 * 获取分页
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getPurchaseRecordsScript(PageInfo pageInfo, String saleId) {
		return itemRepository.getPurchaseRecordsScript(pageInfo, saleId);
	}

	/**
	 * 获取商品套餐
	 * 
	 * @param saleId
	 * @return
	 */
	public Map<String, List<String[]>> getDistountItems(String saleId) {
		List<String[]> distountIds = itemRepository.getDistountId(saleId);
		Map<String, List<String[]>> reses = new HashMap<String, List<String[]>>();
		for (int i = 1; i < distountIds.size(); i++) {
			List<String[]> goods = itemRepository.getDistountGoods(distountIds.get(i)[0], saleId);
			reses.put(saleId + (i + 1), goods);
		}

		return reses;
	}

	/**
	 * 获取分页
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getItemConsultsScript(PageInfo pageInfo, String saleId) {
		return itemRepository.getItemConsultsScript(pageInfo, saleId);
	}

	/**
	 * 获取咨询
	 * 
	 * @param pageInfo
	 * @param saleId
	 * @return
	 */
	public List<Object[]> getItemConsults(PageInfo pageInfo, String saleId) {
		return itemRepository.getItemConsults(pageInfo, saleId);
	}

	/**
	 * 保存评价是否有用
	 * 
	 * @param saleId
	 */
	public boolean saveIsUse(String commentId, String flag) {
		return itemRepository.saveIsUse(commentId, flag);
	}

	/**
	 * 更新商品收藏数量
	 * 
	 * @param saleId
	 */
	public boolean updateCollectNum(String saleId, TMemberFavorite favorite) {
		return itemRepository.updateCollectNum(saleId, favorite);
	}

	/**
	 * 是否收藏
	 * 
	 * @param favorite
	 * @return
	 */
	public boolean isCollect(TMemberFavorite favorite) {
		TMemberFavorite res = itemRepository.getFavorite(favorite);
		if (null == res || res.getFavoriteId() == null) {
			return false;
		}
		return true;
	}

	/**
	 * 生成菜单目录
	 * 
	 * @param type_id
	 * @return
	 */
	public List<TSysType> makeMenuTab(String typeId) {
		return sysTypeUtil.getParentTypeList(Integer.valueOf(typeId));
	}

	/**
	 * 获取商品额外信息
	 * 
	 * @param saleId
	 * @return
	 */
	public Object[] getItemExt(String saleId) {
		return itemRepository.getItemExt(saleId);
	}

	/**
	 * 保存评价和咨询
	 * 
	 * @param comment
	 * @return
	 */
	public boolean saveComment(TItemComment comment) {
		return itemRepository.saveComment(comment);
	}

	/**
	 * 根据id查找对应的商品
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> getItemSaleById(Long id) throws SQLException {
		return itemRepository.getItemSaleById(id);
	}
}
