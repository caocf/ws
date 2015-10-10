package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.ItemSaleDataDTO.Item;
import com.cplatform.b2c.dto.LogisticsFeeDTO;
import com.cplatform.b2c.dto.LogisticsFeeDTO.Good;
import com.cplatform.b2c.dto.LogisticsFeeResp;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.b2c.repository.ItemSaleDao;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.TimeUtil;
import com.cplatform.b2c.web.order.OrderCommonException;

/**
 * 商品信息查询. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-18 下午6:33:34
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class ItemSaleService {

	private final Logger logger = Logger.getLogger(ItemSaleService.class);

	// 验证用户限制购买数量
	public static final String ERROR_MESSAGE_LIMIT = "CHECK_LIMIT";

	// 验证用户购买库存数量比较
	public static final String ERROR_MESSAGE_SOTCK = "CHECK_STOCK";

	@Autowired
	private ItemSaleDao itemSaleDao;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private MemberAddressService memberAddressService;

	@Autowired
	private ShopService shopService;

	/**
	 * 根据现有的商品编号查询出与其关联的商品信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> findRelationItemSaleById(Long id) throws SQLException {
		return itemSaleDao.findRelationItemSaleById(id);
	}

	/**
	 * 根据id获取商品信息
	 * 
	 * @param itemId
	 * @return
	 */
	public ItemSaleDataDTO getItemSaleById(String itemId) {
		// 获取商品信息
		ItemSaleDataDTO item = thirdInterDao.getItemById(itemId);
		return item;
	}

	/**
	 * 判断是否是限时购买商品
	 * 
	 * @param item
	 * @return
	 */
	public boolean checkIsPromptGoods(Item item) {
		if (null != item && null != item.getId()) {
			if (Constants.PROMPT_BUYING.equals(item.getIseckill())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断用户购买当前商品数量是否超过限制购买数
	 * 
	 * @param userBuy
	 *            用户购买数
	 * @param limmit
	 *            限制购买数量
	 * @return
	 */
	public boolean checkLimitBuyNum(int userBuy, Integer limmit) {
		logger.info("userBuy：" + userBuy + ", limmit：" + limmit);
		if (null == limmit || 0 == limmit || (limmit - userBuy) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断用户购买当前商品数量是否超过库存数
	 * 
	 * @param userBuy
	 *            用户购买数
	 * @param stock
	 *            当前商品库存数量
	 * @return
	 */
	public boolean checkStock(int userBuy, int stock) {
		logger.info("userBuy：" + userBuy + ", stock：" + stock);
		if (stock == -1000 || (stock - userBuy) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 验证并给予错误信息
	 * 
	 * @param type
	 *            检查类型
	 * @param message
	 *            提示信息
	 * @param userNum
	 *            用户购买数量
	 * @param storeNum
	 *            商城限定数量
	 */
	public void throwErrorMessage(String type, String message, int userNum, Integer storeNum) {
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(message)) {
			if (ERROR_MESSAGE_LIMIT.equals(type)) {
				logger.info("验证用户购限制买数量");
				if (!checkLimitBuyNum(userNum, storeNum)) {
					throw new OrderCommonException(message);
				}
			} else if (ERROR_MESSAGE_SOTCK.equals(type)) {
				logger.info("验证库存制买数量");
				if (!checkStock(userNum, storeNum)) {
					throw new OrderCommonException(message);
				}
			}
		}
	}

	/**
	 * 判断当前商品是否到开始销售日期
	 * 
	 * @param item
	 * @return
	 */
	public boolean checkStartTime(Item item) {
		if (null != item && StringUtils.isNotBlank(item.getStartTime())) {
			logger.info("验证商品开始销售时间---商品编号：" + item.getId() + "，商品开始时间：" + item.getStartTime());
			if (TimeUtil.compareTime(TimeUtil.now(), item.getStartTime()) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查商品对于会员级别的限制
	 * 
	 * @param item
	 * @param response
	 * @return null表示当前会员不限制购买，其他字符返回会员级别限制的项目，比如L1
	 * @throws SQLException
	 */
	public String checkGoodsBuyLimitVip(Item item, SessionUser sessionUser) throws SQLException {
		String limitUserLevel = item.getUserLevels();
		if (StringUtils.isBlank(limitUserLevel)) {
			logger.info("检查商品对于会员级别的限制---" + "商品编号：" + item.getId());
			return Constants.VIP_ITEM_SALE_NO_USER_LEVELS;
		} else {
			// 获取当前商品支付购买的会员
			String limitVip = this.findPriceTypeCodeByItemId(item.getId().toString(), ',');
			if (StringUtils.isBlank(limitVip)) {
				return Constants.VIP_NO_LIMIT_LEVELS;
			}

			if (Constants.RED_USER_LEVELS.equals(limitVip)) {
				limitUserLevel = Constants.RED_USER_LEVELS;
			} else if (Constants.UNION_USER_LEVELS.equals(limitVip)) {
				limitUserLevel = Constants.UNION_USER_LEVELS;
			} else if (StringUtils.contains(limitVip, Constants.RED_USER_LEVELS) && StringUtils.contains(limitVip, Constants.UNION_USER_LEVELS)) {
				limitUserLevel = Constants.RED_USER_LEVELS + "," + Constants.UNION_USER_LEVELS;
			}
			logger.info("检查商品对于会员级别的限制---" + "商品编号：" + item.getId() + "，商品会员购买：" + limitUserLevel);
			return checkMemberVip(sessionUser, limitUserLevel);
		}
	}

	/**
	 * 验证当前用户是否有购买当前商品的权限
	 * 
	 * @param user
	 *            用户信息
	 * @param goodsBuyLevels
	 *            商品购买时所需要的权限
	 * @return
	 */
	public String checkMemberVip(SessionUser user, String goodsBuyLevels) {
		// 判断是否是红砖会员
		boolean redMember = user.getRedMember() == Constants.RED_MEMBER;
		logger.info("用户编号：" + user.getId() + "，用户手机号：" + user.getTerminalId() + "，红钻会员状态：" + redMember);
		// 判断是否商盟会员
		boolean unionMember = orderService.isUnionMember(user.getTerminalId());
		logger.info("用户编号：" + user.getId() + "，用户手机号：" + user.getTerminalId() + "，商盟会员状态：" + unionMember);
		if (Constants.RED_USER_LEVELS.equals(goodsBuyLevels)) {
			if (!redMember) {
				return Constants.RED_USER_LEVELS;
			}
		} else if (Constants.UNION_USER_LEVELS.equals(goodsBuyLevels)) {
			if (!unionMember) {
				return Constants.UNION_USER_LEVELS;
			}
		} else if (goodsBuyLevels.contains(Constants.RED_USER_LEVELS) && goodsBuyLevels.contains(Constants.UNION_USER_LEVELS)) {
			if (!(redMember || unionMember)) { // 目前，当用户既不是商盟会员，也不是红钻会员时。提示没有商盟会员。
				return Constants.UNION_USER_LEVELS;
			}
		}
		return Constants.VIP_NO_FIND_LEVELS;
	}

	/**
	 * 查询当前商品支持的会员购买
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public String findPriceTypeCodeByItemId(String itemId, char separator) throws SQLException {
		List<String[]> list = itemSaleDao.findPriceTypeCodeByItemId(itemId);
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		if (null != list && list.size() > 0) {
			for (String[] strs : list) {
				for (String str : strs) {
					if (StringUtils.isNotBlank(str)) {
						sb.append(str).append(separator);
					}
				}
			}
			String content = sb.toString();
			if (StringUtils.isNotBlank(content) && content.indexOf(",") > -1) {
				return content.substring(0, content.length() - 1);
			}
		}
		return "";
	}

	/**
	 * 抛出商品对于会员级别的限制信息
	 * 
	 * @param item
	 * @param user
	 * @throws SQLException
	 */
	public void throwCheckGoogdBuyLimitVip(Item item, SessionUser user) throws SQLException {
		// 检查商品对于会员级别的限制
		String limitVipBuy = checkGoodsBuyLimitVip(item, user);
		if (StringUtils.isNotBlank(limitVipBuy)) {
			if (Constants.RED_USER_LEVELS.equals(limitVipBuy)) {
				throw new OrderCommonException("对不起，该商品仅限红钻会员购买，如需购买，请开通红钻会员，谢谢！");
			} else if (Constants.UNION_USER_LEVELS.equals(limitVipBuy)) {
				throw new OrderCommonException("对不起，该商品仅限商盟会员购买，如需购买，请开通商盟会员，谢谢！");
			}
		}
	}

	/**
	 * 为查询物流价格准备数据
	 * 
	 * @param terminalId
	 *            用户手机号
	 * @param count
	 *            商品信息
	 * @param goodId
	 * @return
	 */
	public LogisticsFeeDTO getLogisticsFeeDTO(String terminalId, int quantity, Long goodId) {
		LogisticsFeeDTO.Good good = new LogisticsFeeDTO.Good(quantity, goodId);
		List<Good> goods = new ArrayList<LogisticsFeeDTO.Good>();
		goods.add(good);
		return new LogisticsFeeDTO(goods, terminalId);

	}

	/**
	 * 获取需支付的物流价格
	 * 
	 * @param logisticsFeeDTO
	 * @return
	 * @throws Exception
	 */
	public LogisticsFeeResp getLogisticsFee(LogisticsFeeDTO logisticsFeeDTO) throws Exception {
		return thirdInterDao.getLogisticsFee(logisticsFeeDTO);
	}

	/**
	 * 设置当前商品的物流价格
	 * 
	 * @param areaCode
	 *            用户默认收货地址地区省编号
	 * @param item
	 *            商品信息
	 * @param quantity
	 *            购买数量
	 * @throws Exception
	 */
	public void setGoodsLogisticsFee(String areaCode, ItemSaleDataDTO item, int quantity) throws Exception {
		// 计算物流价格
		LogisticsFeeDTO logisticsFeeDTO = getLogisticsFeeDTO(areaCode, quantity, item.getItem().getId());
		LogisticsFeeResp logisticsFeeResp = getLogisticsFee(logisticsFeeDTO);
		if (null != logisticsFeeResp) {
			for (LogisticsFeeResp.Good goodsInfo : logisticsFeeResp.getGoods()) {
				if (null != goodsInfo && null != goodsInfo.getLogisticsfee()) {
					// 设置商品物流价
					item.getItem().setLogisticsFee(CommonUtils.toYuan(goodsInfo.getLogisticsfee()));
					logger.info("当前商品物流价格---商品编号：" + item.getItem().getId() + "，物流价格：" + item.getItem().getLogisticsFee());
				}
			}
		}
	}

	/**
	 * 设置当前商品的物流价格
	 * 
	 * @param userinfo
	 * @param item
	 * @param quantity
	 * @throws Exception
	 */
	public void setGoodsLogisticsFee(SessionUser userinfo, ItemSaleDataDTO item, int quantity) throws Exception {
		// 获取用户默认收货地址
		TMemberAddress tMemberAddress = memberAddressService.findTMemberAddressByUidAndDefult(userinfo.getId());
		if ((null == tMemberAddress || tMemberAddress.getId() == null || "".equals(tMemberAddress.getId()))) {
			// 收货地址为空
			logger.info("订单确认---进行下单");
			logger.info("尊敬的用户您好，请填写你的收货地址");
		} else {
			// 默认收货地址所属省份编号
			String provinceCode = shopService.getProvinceCodeByRegionCode(tMemberAddress.getRegion(), null);
			setGoodsLogisticsFee(provinceCode, item, quantity);
		}
	}
}
