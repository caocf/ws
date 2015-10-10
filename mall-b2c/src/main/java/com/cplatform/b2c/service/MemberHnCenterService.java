package com.cplatform.b2c.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.TActOrder;
import com.cplatform.b2c.repository.MemberHnCenterRepository;
import com.cplatform.b2c.util.Constants;
import com.cplatform.order.ActOrderInfo;

/**
 * 河南商户新增菜单. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-8 下午5:24:07
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class MemberHnCenterService {

	private final Logger logger = Logger.getLogger(MemberHnCenterService.class);

	@Autowired
	private MemberHnCenterRepository repository;

	@Autowired
	private MemberCenterService memberCenterService;

	/**
	 * 获取河南用户兑换商品
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param flag
	 * @param isdelivery
	 * @param orderType
	 * @return
	 */
	public List<TActOrder> getHnOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery, Long orderType) {
		return repository.getHnOrders(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery, orderType);
	}

	/**
	 * 获取河南用户的订单总页数
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param flag
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getHnOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery, Long orderType) {
		return repository.getHnOrdersScript(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery, orderType);
	}

	/**
	 * 根据状态获取订单
	 * 
	 * @param userId
	 * @param status
	 * @param isdelivery
	 * @param orderType
	 * @return
	 */
	public int getHnOrdersByStatus(String userId, String status, String isdelivery, Long orderType) {
		return repository.getHnOrdersByStatus(userId, status, isdelivery, orderType);
	}

	/**
	 * 获取货到付款的订单
	 * 
	 * @param userId
	 * @param isdelivery
	 * @param orderType
	 * @return
	 */
	public int getHnOrdersByDelivery(String userId, String isdelivery, Long orderType) {
		return repository.getHnOrdersByDelivery(userId, isdelivery, orderType);
	}

	/**
	 * 获取结算总金额
	 * 
	 * @param info
	 * @param goodAmount
	 * @param discount
	 * @return
	 * @throws Exception
	 */
	public String getTotal(ActOrderInfo info, int goodAmount, int discount, String isXuNi) throws Exception {
		StringBuffer amount = new StringBuffer();
		int expressFee = 0;
		if (null != info.getExpressInfo()) {
			expressFee = info.getExpressInfo().getExpressCost();
		}
		int total = goodAmount + expressFee - discount;
		// 判断是否是货到付款
		if (info.getPayOnDelivery() == 1) {
			amount.append("<label class=\"order_totalprice\">应付").append(total).append("个</label>");
		} else {
			amount.append("商品金额：").append(goodAmount).append("个");

			if (StringUtils.equals("0", isXuNi)) {
				amount.append(" + 运费：").append(expressFee).append("个");
			}

			logger.info("调用礼金券接口获得优惠/if_card/info开始");
			// 调用礼金券接口获得优惠
			String json = memberCenterService.getCardGiftByOrderId(info.getId());
			logger.info("调用礼金券接口获得优惠/if_card/info结果" + json);

			int cardPay = 0;
			if (json != null) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject = JSONObject.fromObject(json);
					if (jsonObject.getInt("flag") == 0) {
						JSONArray paymentMap = jsonObject.getJSONArray("payment");
						for (int i = 0; i < paymentMap.size(); i++) {
							cardPay += paymentMap.getJSONObject(i).getDouble("payAmount") * 100;
						}
						float dis = Float.valueOf(discount) - Float.valueOf(cardPay);
						if (dis > 0) {
							amount.append(" - 优惠金额：").append(dis).append("个");
						}
						if (cardPay > 0) {
							amount.append(" - 礼金券抵扣：").append(cardPay).append("个");
						}
						// total = total - cardPay;
					} else {
						if (discount > 0) {
							amount.append(" - 优惠金额：").append(discount).append("个");
						}
					}
				}
				catch (Exception ex) {
					logger.error("调用礼金券接口获得优惠/if_card/info错误" + ex.getMessage());
					if (discount > 0) {
						amount.append(" - 优惠金额：").append(discount).append("个");
					}
				}
			} else {
				if (discount > 0) {
					amount.append(" - 优惠金额：").append(discount).append("个");
				}
			}
			amount.append(" = <label class=\"order_totalprice\">应付总金额：").append(total).append("个</label>");
		}

		return amount.toString();
	}

	/**
	 * 获取页面title
	 * 
	 * @param actionType
	 * @return
	 */
	public String getPageTitle(String actionType) {
		if (StringUtils.isNotBlank(actionType)) {
			if (Constants.HN_ACTION_INTEGRAL.equals(actionType)) {
				return Constants.HN_PAGE_TITLE_INTEGRAL;
			} else if (Constants.HN_ACTION_COUPON.equals(actionType)) {
				return Constants.HN_PAGE_TITLE_COUPON;
			}
		}
		return "移动商城";
	}

}
