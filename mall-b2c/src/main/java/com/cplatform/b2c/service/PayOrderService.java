package com.cplatform.b2c.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.dto.PayDTO;
import com.cplatform.b2c.model.CodeInfo;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TOrderRefund;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.PaymentSpliteGoodsInfo;
import com.cplatform.order.PaymentSpliteOrderInfo;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.cplatform.pay.PaymentInfo;
import com.cplatform.pay.RequestOperate;
import com.cplatform.util2.TimeStamp;
import com.cplatform.verifycode.RevokeRespInfo;

/**
 * 订单支付处理. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-16 上午10:59:41
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class PayOrderService {

	Logger logger = Logger.getLogger(PayOrderService.class);

	@Autowired
	private OrderService2 orderService2;

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PayServiceClient payServiceClient;

	@Autowired
	private CodeInfoService codeInfoService;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private ItemService itemService;

	@Autowired
	private TOrderRefundGoodsService tOrderRefundGoodsService;

	@Autowired
	private CloseRequestService closeRequestService;

	/**
	 * 计算出当前订单支付信息
	 * 
	 * @param payOrderInfos
	 * @param actOrderInfo
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getPayMent(List<PayOrderInfo> payOrderInfos, ActOrderInfo actOrderInfo) throws SQLException {
		Map<String, Object> map = null;
		if (null != payOrderInfos && payOrderInfos.size() > 0) {
			map = new HashMap<String, Object>();
			int payBalance = 0; // 话费支付
			int payCash = 0; // 现金支付
			int paylCoin = 0; // 商城币支付
			int payScore = 0; // 积分支付
			int payRedpack = 0; // 红包支付

			int refundBalance = 0; // 话费退款
			int refundCash = 0; // 现金退款
			int refundlCoin = 0; // 商城币退款
			int refundScore = 0; // 积分退款
			int refundRedpack = 0; // 红包退款

			for (PayOrderInfo payOrderInfo : payOrderInfos) {
				// 订单支付
				if (payOrderInfo.getOperate() == RequestOperate.Pay && payOrderInfo.getStatus() == PayOrderInfo.STATUS_SUCCESS) {
					// 订单支付金额
					List<PaymentInfo> payments = payOrderInfo.getPayments();
					for (PaymentInfo paymentInfo : payments) {
						if (PayDTO.CURRENCY_BALANCE.equals(paymentInfo.getCurrency())) {
							payBalance += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_CASH.equals(paymentInfo.getCurrency())) {
							payCash += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_COIN.equals(paymentInfo.getCurrency())) {
							paylCoin += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_SCORE.equals(paymentInfo.getCurrency())) {
							payScore += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_REDPACK.equals(paymentInfo.getCurrency())) {
							payRedpack += payOrderInfo.getPaymentAmount();
						}
					}
				} else if (payOrderInfo.getOperate() == RequestOperate.Refund && payOrderInfo.getStatus() == PayOrderInfo.STATUS_SUCCESS) {
					List<PaymentInfo> payments = payOrderInfo.getPayments();
					for (PaymentInfo paymentInfo : payments) {
						if (PayDTO.CURRENCY_BALANCE.equals(paymentInfo.getCurrency())) {
							refundBalance += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_CASH.equals(paymentInfo.getCurrency())) {
							refundCash += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_COIN.equals(paymentInfo.getCurrency())) {
							refundlCoin += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_SCORE.equals(paymentInfo.getCurrency())) {
							refundScore += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_REDPACK.equals(paymentInfo.getCurrency())) {
							refundRedpack += Math.abs(payOrderInfo.getPaymentAmount());
						}
					}
				}

				// 已退款金额
				BigDecimal refunDecimal = new BigDecimal(refundBalance).add(new BigDecimal(refundCash)).add(new BigDecimal(refundlCoin))
				        .add(new BigDecimal(refundScore).add(new BigDecimal(refundRedpack)));
				map.put("refunded_yuan", CommonUtils.toYuan(Math.abs(refunDecimal.intValue())));

				// 物流费用
				int expressCost = 0;

				// 订单价格
				int orderSum = 0;

				// 商品类型
				Long itemMode = null;

				// 查询订单下商品
				List<Map<String, String>> actOrderGoodsList = this.findOrderGoodsListByOrderId(actOrderInfo.getId());

				if (null != actOrderGoodsList && actOrderGoodsList.size() > 0) {
					// 判断订单中商品类型，备注：订单中只有一种类型的商品
					itemMode = Long.parseLong(actOrderGoodsList.get(0).get("itemMode"));
					// 商品订单id
					Long itemId = Long.parseLong(actOrderGoodsList.get(0).get("id"));
					Long itemSaleId = Long.parseLong(actOrderGoodsList.get(0).get("goods_id"));

					map.put("itemMode", itemMode);

					// 获取订单预支付信息
					List<Map<String, String>> paymengList = findOrderPaymentListByOrderId(actOrderInfo.getId());

					// 遍历商品，算出每个商品现金和商城币值
					for (int i = 0; i < actOrderGoodsList.size(); i++) {
						if (null != paymengList && paymengList.size() > 0) {
							for (int j = 0; j < paymengList.size(); j++) {
								// 查询支付表中"SPLITE_INFO"，算出每个商品支付情况
								String jsonString = paymengList.get(j).get("SPLITE_INFO").toString();
								if (StringUtils.isNotEmpty(jsonString)) {
									jsonString = StringEscapeUtils.unescapeJavaScript(jsonString);
									JSONObject jsonObject = JSONObject.fromObject(jsonString);
									// 转换规则
									JsonConfig jsonConfig = new JsonConfig();
									Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
									classMap.put("spliteGoodsInfos", PaymentSpliteGoodsInfo.class);
									jsonConfig.setClassMap(classMap);
									jsonConfig.setRootClass(PaymentSpliteOrderInfo.class);
									// 转换
									PaymentSpliteOrderInfo splitOrderInfo = (PaymentSpliteOrderInfo) JSONObject.toBean(jsonObject, jsonConfig);
									// 只能取一次订单价格和物流费用
									if (i == 0) {
										// 订单价格
										orderSum += splitOrderInfo.getSumValue();
										// 物流费用
										expressCost += splitOrderInfo.getExpress();
									}
								}
							}
						} else {
							logger.info("获取订单支付信息失败,订单id=" + actOrderInfo.getId());
						}
						// 订单中虚拟商品只有一种
						if (1L == itemMode) {
							// 设置各币种最大退款金额
							if (i == 0) {
								map.put("maxBalance", this.getValue(payBalance, refundBalance));
								map.put("maxCash", this.getValue(payCash, refundCash));
								map.put("maxCoin", this.getValue(paylCoin, refundlCoin));
								map.put("maxScore", this.getValue(payScore, refundScore));
								map.put("maxRedpack", this.getValue(payRedpack, refundRedpack));
							}

							// 虚拟商品名称
							map.put("goodsName", actOrderGoodsList.get(0).get("goodsName"));

							// 订单商品id
							map.put("itemId", itemId);

							// 订单商品对应商品id
							map.put("goodsId", itemSaleId);

							// 虚拟商品数量
							map.put("count", Long.parseLong(actOrderGoodsList.get(0).get("count")));

							// 订单商户
							String storeId = actOrderGoodsList.get(0).get("store_id");
							map.put("storeId", storeId);

							// 查询商品码
							CodeInfo codeInfo = new CodeInfo();
							codeInfo.setActOrderId(actOrderInfo.getId());
							List<CodeInfo> codes = codeInfoService.listStoreCodes(codeInfo);

							// 获取拉手商品是否支持退款
							// 0不支持，1支持,null表示不是拉手商品
							String refundStrategy = actOrderGoodsList.get(0).get("refund_strategy");
							if (StringUtils.isNotEmpty(refundStrategy)) {
								// 设置退款单类型
								// refundType = 2L;
								int codeNum = 0;
								// 支持退款的商品显示商品码
								if (refundStrategy.equals("1")) {
									// 遍历码，算出可退码总数
									for (CodeInfo code : codes) {
										if (code.getCodeStatus() == 0L) {
											codeNum++;
										}
									}

								}
								map.put("codeNum", codeNum);
								map.put("codes", codes);
								map.put("refundStrategy", Long.valueOf(refundStrategy));
							} else {
								map.put("codes", codes);
							}
						} else if (itemMode == 0L) {
							String orderGoodsId = actOrderGoodsList.get(i).get("ID");
							String goodId = actOrderGoodsList.get(i).get("GOODS_ID");
							List<Map<String, String>> refundedList = this.getSuccessRefundGoodses(Long.parseLong(orderGoodsId),
							                                                                      Long.parseLong(goodId), actOrderInfo.getId());
							if (null != refundedList && refundedList.size() > 0) {
								// 退款数量
								Long backNumber = 0L;
								for (int k = 0; k < refundedList.size(); k++) {
									Map<String, String> orderRefundGoodsMap = refundedList.get(k);
									backNumber += Long.parseLong(orderRefundGoodsMap.get("BACK_NUMBER"));
								}
								actOrderGoodsList.get(i).put("backNumber", backNumber + "");
							} else {
								actOrderGoodsList.get(i).put("backNumber", "0");
							}
						}
					}
				}
				// 订单价格
				map.put("orderSum", CommonUtils.toYuan(orderSum));
				// model.addAttribute("refundType", refundType);
				if (null != itemMode && itemMode == 0L) {
					map.put("totalBalance", this.getValue(payBalance, refundBalance));
					map.put("totalCash", this.getValue(payCash, refundCash));
					map.put("totalCoin", this.getValue(paylCoin, refundlCoin));
					map.put("totalScore", this.getValue(payScore, refundScore));
					map.put("totalRedpack", this.getValue(payRedpack, refundRedpack));
					// 物流费用
					map.put("expressCost", CommonUtils.toYuan(expressCost));
					map.put("actOrderGoodsList", actOrderGoodsList);
				}
			}
		}
		return map;
	}

	/**
	 * 设置各币种退款金额，金额设置为“分”单位
	 * 
	 * @param tOrderRefund
	 */
	public void setPayFee(TOrderRefund tOrderRefund) {
		tOrderRefund.setCashFee(this.getDoubleValue(tOrderRefund.getCashFee(), 0.0) * 100);
		tOrderRefund.setCoinFee(this.getDoubleValue(tOrderRefund.getCoinFee(), 0.0) * 100);
		tOrderRefund.setPhoneFee(this.getDoubleValue(tOrderRefund.getPhoneFee(), 0.0) * 100);
		tOrderRefund.setScoreFee(this.getDoubleValue(tOrderRefund.getScoreFee(), 0.0) * 100);
		tOrderRefund.setRedpackageFee(this.getDoubleValue(tOrderRefund.getRedpackageFee(), 0.0) * 100);
		tOrderRefund.setRefundFee(tOrderRefund.getCashFee() + tOrderRefund.getCoinFee() + tOrderRefund.getPhoneFee() + tOrderRefund.getScoreFee()
		        + tOrderRefund.getRedpackageFee());
	}

	/**
	 * 获取指定订单的支付信息
	 * 
	 * @param orderId
	 *            订单号
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> findOrderPaymentListByOrderId(Long orderId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_act_order_payment t ");
		sqlBuff.append(" where t.act_order_id = ? ");
		sqlBuff.append(" order by t.id desc");

		return dbHelper.getMapList(sqlBuff.toString(), new Object[] { orderId });

	}

	/**
	 * 订单下商品信息
	 * 
	 * @param orderId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> findOrderGoodsListByOrderId(Long orderId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select goods.*, ts.name goodsName,ts.item_mode itemMode,ts.store_id storeId,ls.refund_strategy ");
		sqlBuff.append("  from t_act_order_goods goods ");
		sqlBuff.append("  join   t_item_sale ts on  goods.goods_id = ts.id ");
		sqlBuff.append("  left join  t_item_lashou ls on ts.id=ls.item_id  ");
		sqlBuff.append(" where goods.act_order_id = ? ");
		sqlBuff.append(" order by goods.id desc");

		return dbHelper.getMapList(sqlBuff.toString(), new Object[] { orderId });
	}

	/**
	 * 两金额之间的差值，并转成单位(元)
	 * 
	 * @param bigNum
	 * @param minNum
	 * @return
	 */
	private BigDecimal getValue(int bigNum, int minNum) {
		return CommonUtils.toYuan(bigNum - minNum);
	}

	/**
	 * 退款单类型
	 * 
	 * @param order
	 * @return
	 */
	public Long setRefundType(ActOrderInfo order) {
		Long refundType = 1L;// 平台自己
		if (order.getOrderType() == Constants.ORDER_TYPE_LASHOU) {// 拉手
			refundType = 2L;
		} else if (order.getOrderType() == Constants.ORDER_TYPE_INTEGRAL) {// 河南积分
			refundType = 4L;
		}
		return refundType;
	}

	/**
	 * 已退款的商品信息
	 * 
	 * @param orderGoodsId
	 *            订单商品编号
	 * @param goodsId
	 *            商品编号
	 * @param orderId
	 *            订单编号
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> getSuccessRefundGoodses(Long orderGoodsId, Long goodsId, Long orderId) throws SQLException {
		List<Object> params = new ArrayList<Object>();
		StringBuilder builder = new StringBuilder();
		builder.setLength(0);
		builder.append("SELECT t.* ");
		builder.append(" FROM t_order_refund_goods t ");
		builder.append(" JOIN t_order_refund r on r.id = t.refund_id ");
		builder.append("WHERE r.status = 6 ");
		builder.append(" AND r.order_id = ? ");
		builder.append(" AND t.order_goods_id = ? ");
		builder.append(" AND t.goods_id = ? ");
		params.add(orderId);
		params.add(orderGoodsId);
		params.add(goodsId);
		return dbHelper.getMapList(builder.toString(), params.toArray());
	}

	/**
	 * 根据订单编号查找该订单退款信息
	 * 
	 * @param orderId
	 *            订单编号
	 * @return
	 * @throws SQLException
	 */
	public List<TOrderRefund> findRefundByOrderId(Long orderId) throws SQLException {
		String sql = "SELECT * FROM T_ORDER_REFUND r WHERE r.ORDER_ID = ? ";
		return dbHelper.getBeanList(sql, TOrderRefund.class, orderId);
	}

	public List<TOrderRefund> findRefundByOrderIdAndRefundSource(Long orderId, Integer refundSource) throws SQLException {
		// String sql =
		// "SELECT * FROM T_ORDER_REFUND r WHERE r.ORDER_ID = ? AND r.REFUND_SOURCE = ?  AND (r.STATUS = ? OR r.STATUS = ?)";
		String sql = "SELECT * FROM T_ORDER_REFUND r WHERE r.ORDER_ID = ? ";
		return dbHelper.getBeanList(sql, TOrderRefund.class, orderId);
	}

	/**
	 * 获取订单退款信息(能否退款)
	 * 
	 * @param actOrderInfo
	 *            订单信息
	 * @return 0：未付款；1：可以发起退款单；2：有付款，前台当前操作已发起过退款申请
	 * @throws Exception
	 */
	public Integer getOderIsCanRefund(ActOrderInfo actOrderInfo, Integer refundSource) throws Exception {

		logger.info("获取订单退款信息，判断是否要进行退款。actOrderId：" + actOrderInfo.getId() + "refundSource：" + refundSource);

		// 查询订单下所有退款单
		List<TOrderRefund> orderRefunds = this.findRefundByOrderIdAndRefundSource(actOrderInfo.getId(), refundSource);

		// 设置是否有增加退款单权限
		// 已支付和支付中的订单才可以增加退款单
		List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(actOrderInfo.getId());
		if (payOrderInfos != null && payOrderInfos.size() > 0) {
			for (PayOrderInfo info : payOrderInfos) {
				if (info.getOperate() == RequestOperate.Pay && info.getStatus() == PayOrderInfo.STATUS_SUCCESS) {
					if (null == orderRefunds || orderRefunds.size() == 0) {
						// 第一次增加退款单
						return Constants.ALLOW_REFUND;
					}
					// 前台退款申请只能申请一次，无论退款失败或成功
					return Constants.UNALLOW_REFUND_HAVA;
				}
			}
		}
		return Constants.UNALLOW_REFUND_UNPAY;
	}

	/**
	 * 设置退款单状态
	 * 
	 * @param orderRefund
	 *            退款单实体类
	 * @param itemMode
	 *            订单商品类型，0实物，1虚拟
	 * @param smsFlag
	 *            短信购订单标志
	 * @param request
	 */
	public void setOrderRefundStatus(TOrderRefund tOrderRefund, Long itemMode, HttpServletRequest request) {
		// 实物类：需要商户确认 ;如果是组合支付，只完成部分支付，不需要商户确认
		// 虚拟类：不需要商户进行确认，拉手除外
		// 短信购退款不需要商户确认，拉手除外
		// 话费退款不需要商户确认
		if (0L == itemMode) {
			Double cashLimit = Double.valueOf(request.getParameter("cashLimit"));
			Double coinLimit = Double.valueOf(request.getParameter("coinLimit"));
			Double scoreLimit = Double.valueOf(request.getParameter("scoreLimit"));
			Double phoneLimit = Double.valueOf(request.getParameter("phoneLimit"));
			Double redpackageLimit = Double.valueOf(request.getParameter("redpackageLimit"));
			boolean flag;
			try {
				flag = this.findOtherPayCurrency(tOrderRefund.getOrderId(), cashLimit, coinLimit, scoreLimit, phoneLimit, redpackageLimit);
				if (flag) {// 组合支付，部分支付成功
					tOrderRefund.setStatus(TOrderRefund.STATUS_2);
				} else {
					tOrderRefund.setStatus(TOrderRefund.STATUS_1);
				}
			}
			catch (Exception e) {
				logger.error("查询支付币种失败");
			}

		} else if (1L == itemMode) {// 虚拟商品退款
			if (tOrderRefund.getRefundType() == TOrderRefund.REFUND_TYPE_2 || tOrderRefund.getRefundType() == TOrderRefund.REFUND_TYPE_4) {// 拉手、河南积分退款单
				// 设置初始状态为等待商户确认，退款单状态由接口更改。
				tOrderRefund.setStatus(TOrderRefund.STATUS_1);
			} else {
				tOrderRefund.setStatus(TOrderRefund.STATUS_2);
			}
		}

	}

	/**
	 * 查询是否存在未成功支付的币种
	 * 
	 * @param orderRefund
	 * @param cashLimit
	 *            现金最大退款金额
	 * @param coinLimit
	 *            商城币最大退款金额
	 * @param scoreLimit
	 *            积分最大退款金额
	 * @param phoneLimit
	 *            话费最大退款金额
	 * @return true:存在，false:不存在
	 * @throws Exception
	 */
	public boolean findOtherPayCurrency(Long orderId, Double cashLimit, Double coinLimit, Double scoreLimit, Double phoneLimit, Double redpackageLimit)
	        throws Exception {
		boolean flag = false;
		List<String> list = new ArrayList<String>();
		if (cashLimit != null && cashLimit == 0.0) {
			list.add(TOrderRefund.CURRENCY_CASH);
		}
		if (coinLimit != null && coinLimit == 0.0) {
			list.add(TOrderRefund.CURRENCY_COIN);
		}
		if (scoreLimit != null && scoreLimit == 0.0) {
			list.add(TOrderRefund.CURRENCY_SCORE);
		}
		if (phoneLimit != null && phoneLimit == 0.0) {
			list.add(TOrderRefund.CURRENCY_BALANCE);
		}
		if (redpackageLimit != null && redpackageLimit == 0.0) {
			list.add(TOrderRefund.CURRENCY_REDPACK);
		}
		if (list.size() > 0) {
			List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(orderId);
			if (null != payOrderInfos && payOrderInfos.size() > 0) {
				for (String currency : list) {
					for (PayOrderInfo tmp : payOrderInfos) {
						if (tmp.getOperate() == RequestOperate.Pay && tmp.getStatus() != PayOrderInfo.STATUS_SUCCESS) {
							List<PaymentInfo> payments = tmp.getPayments();
							for (PaymentInfo paymentInfo : payments) {
								if (paymentInfo.getCurrency().equals(currency)) {
									flag = true;
									logger.info("未成功支付的币种:" + currency);
									break;
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Double值为空处理
	 * 
	 * @param value
	 *            传输值
	 * @param defauleValue
	 *            默认值
	 * @return
	 */
	public Double getDoubleValue(Double value, Double defaultValue) {
		return (value != null) ? value : defaultValue;
	}

	public Double getDoubleValue(Object value, Double defaultValue) {
		return (value != null) ? Double.valueOf(value.toString()) : defaultValue;
	}

	/**
	 * Double值为空处理
	 * 
	 * @param value
	 *            传输值
	 * @param defauleValue
	 *            默认值
	 * @return
	 */
	public String getStringValue(String value, String defaultValue) {
		return (StringUtils.isNotBlank(value)) ? value : defaultValue;
	}

	/**
	 * 将Object类型转成Double
	 * 
	 * @param value
	 * @return
	 */
	public Double objectToDouble(Object value) {
		String stringValue = ObjectUtils.toString(value);
		if (StringUtils.isNotBlank(stringValue)) {
			return Double.parseDouble(stringValue);
		}
		return null;
	}

	/**
	 * 计算要退款的金额
	 * 
	 * @param mapPayMent
	 * @return
	 */
	public boolean isHaveRefundAmout(Map<String, Object> mapPayMent) {
		// 订单价格
		Double orderSum = Double.parseDouble(ObjectUtils.toString(mapPayMent.get("orderSum")));
		// 已退款价格
		Integer refunded = CommonUtils.toFen(ObjectUtils.toString(mapPayMent.get("refunded_yuan")));

		// 计算订单价格与已退款的差值
		if ((orderSum - refunded) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 取消订单操作-设置实物商品退款单状态
	 * 
	 * @param tOrderRefund
	 * @param cashLimit
	 * @param coinLimit
	 * @param scoreLimit
	 * @param phoneLimit
	 */
	public void setOrderRefundStatusSW(TOrderRefund tOrderRefund, Double cashLimit, Double coinLimit, Double scoreLimit, Double phoneLimit,
	        Double redpackageLimit) {
		try {
			tOrderRefund.setStatus(TOrderRefund.STATUS_2);
		}
		catch (Exception e) {
			logger.error("查询支付币种失败");
		}
	}

	/**
	 * 取消订单操作-设置虚拟品退款单状态
	 * 
	 * @param tOrderRefund
	 * @param cashLimit
	 * @param coinLimit
	 * @param scoreLimit
	 * @param phoneLimit
	 */
	public void setOrderRefundStatusXN(TOrderRefund tOrderRefund) {
		if (tOrderRefund.getRefundType() == TOrderRefund.REFUND_TYPE_2 || tOrderRefund.getRefundType() == TOrderRefund.REFUND_TYPE_4) {// 拉手、河南积分退款单
			// 设置初始状态为等待商户确认，退款单状态由接口更改。
			tOrderRefund.setStatus(TOrderRefund.STATUS_1);
		} else {
			tOrderRefund.setStatus(TOrderRefund.STATUS_2);
		}
	}

	/**
	 * 转换类型
	 * 
	 * @param mapPayMent
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> objectToList(Map<String, Object> mapPayMent) {
		Object obj = mapPayMent.get("actOrderGoodsList");
		if (null != obj) {
			return (List<Map<String, String>>) obj;
		}
		return null;
	}

	/**
	 * 转换List<CodeInfo>
	 * 
	 * @param mapPayMent
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CodeInfo> getCancelCodes(Map<String, Object> mapPayMent) {
		Object obj = mapPayMent.get("codes");
		if (null != obj) {
			return (List<CodeInfo>) obj;
		}
		return null;
	}

	/**
	 * 退款订单下的商品信息-实物
	 * 
	 * @param actOrderGoodsList
	 * @return
	 */
	public String[] orderRefundGoodsSW(List<Map<String, String>> actOrderGoodsList) {
		String[] orderRefundGoods = null;
		if (null != actOrderGoodsList && actOrderGoodsList.size() > 0) {
			orderRefundGoods = new String[actOrderGoodsList.size()];
			for (int i = 0; i < actOrderGoodsList.size(); i++) {
				Map<String, String> map = actOrderGoodsList.get(i);
				orderRefundGoods[i] = this.montage(map.get("id"), map.get("goods_id"), (Integer.parseInt(map.get("count")) - (StringUtils
				        .isNotBlank(map.get("backNumber")) ? Integer.parseInt(map.get("backNumber")) : 0)));
			}
		}
		return orderRefundGoods;
	}

	/**
	 * 获取虚拟商品orderIds
	 * 
	 * @param codes
	 * @return
	 */
	public String[] actOrderGoodsCheck(List<CodeInfo> codes) {
		if (null != codes && codes.size() > 0) {
			String[] orderIds = new String[codes.size()];
			for (int i = 0; i < codes.size(); i++) {
				orderIds[i] = codes.get(0).getOrderId();
			}
			return orderIds;
		}
		return null;
	}

	/**
	 * 字符串拼接
	 * 
	 * @param value1
	 * @param value2
	 * @param value3
	 * @return
	 */
	public String montage(String value1, String value2, Object value3) {
		return value1 + "_" + value2 + "_" + value3;
	}

	/**
	 * 设置各币种退款金额，金额设置为“分”单位
	 * 
	 * @param tOrderRefund
	 */
	public void setCancelPayFee(TOrderRefund tOrderRefund, Map<String, Object> mapPayMent, Long itemMode) {
		if (itemMode == 0) {
			tOrderRefund.setCashFee(this.getDoubleValue(mapPayMent.get("totalCash"), 0.0) * 100);
			tOrderRefund.setCoinFee(this.getDoubleValue(mapPayMent.get("totalCoin"), 0.0) * 100);
			tOrderRefund.setPhoneFee(this.getDoubleValue(mapPayMent.get("totalBalance"), 0.0) * 100);
			tOrderRefund.setScoreFee(this.getDoubleValue(mapPayMent.get("totalScore"), 0.0) * 100);
			// 红包商品
			tOrderRefund.setRedpackageFee(this.getDoubleValue(mapPayMent.get("totalRedpack"), 0.0) * 100);
			tOrderRefund.setRefundFee(tOrderRefund.getCashFee() + tOrderRefund.getCoinFee() + tOrderRefund.getPhoneFee() + tOrderRefund.getScoreFee()
			        + tOrderRefund.getRedpackageFee());
		} else if (itemMode == 1) {
			tOrderRefund.setCashFee(this.getDoubleValue(mapPayMent.get("maxCash"), 0.0) * 100);
			tOrderRefund.setCoinFee(this.getDoubleValue(mapPayMent.get("maxCoin"), 0.0) * 100);
			tOrderRefund.setPhoneFee(this.getDoubleValue(mapPayMent.get("maxBalance"), 0.0) * 100);
			tOrderRefund.setScoreFee(this.getDoubleValue(mapPayMent.get("maxScore"), 0.0) * 100);
			// 红包商品
			tOrderRefund.setRedpackageFee(this.getDoubleValue(mapPayMent.get("maxRedpack"), 0.0) * 100);
			tOrderRefund.setRefundFee(tOrderRefund.getCashFee() + tOrderRefund.getCoinFee() + tOrderRefund.getPhoneFee() + tOrderRefund.getScoreFee()
			        + tOrderRefund.getRedpackageFee());
		}

	}

	/**
	 * 取消订单添加退款申请
	 * 
	 * @param tOrderRefund
	 * @param refundStrategy
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public boolean docancelOrderRefund(TOrderRefund tOrderRefund, Long refundStrategy, Integer refundSource, ActOrderInfo actOrderInfo,
	        Long itemMode, Map<String, Object> mapPayMent, String[] orderRefundGoodsInfos, String[] orderIds) throws Exception {
		if (null == tOrderRefund.getCashFee() && null == tOrderRefund.getCoinFee() && null == tOrderRefund.getScoreFee()
		        && null == tOrderRefund.getPhoneFee() && null == tOrderRefund.getRedpackageFee()) {
			logger.error("取消订单-退款单总金额不能为0");
			return false;
		}

		try {
			// 虚拟商品要先退码，然后保存退款单
			if (itemMode == 1L) {
				Long successCode = 0L;

				if (tOrderRefund.getRefundType() == TOrderRefund.REFUND_TYPE_4) {// 河南积分退码
					// 发送退码请求
					thirdInterDao.refundVerifyCodeHN(tOrderRefund.getOrderId());
				} else if (tOrderRefund.getRefundType() == 2L) {// 拉手订单退码
					List<CodeInfo> codeInfos = codeInfoService.listOrderCodesByActOrderId(tOrderRefund.getOrderId());
					if (codeInfos != null && codeInfos.size() > 0) {
						if (null != refundStrategy && refundStrategy == 0L) {
							logger.error("取消订单-商品不支持退款！");
							return false;
						}
						codeInfoService.refundVerifyCodeLS(tOrderRefund.getOrderId());
					} else {
						// 如果没有码，则不用退码，不需要拉手确认，退款单状态为等待审核
						tOrderRefund.setStatus(TOrderRefund.STATUS_2);
					}
				} else if (tOrderRefund.getRefundType() == 1L) {// 普通退款单退码
					if (orderRefundGoodsInfos != null && StringUtils.isNotBlank(orderRefundGoodsInfos[0].split("_")[0])) {
						Long itemId = Long.valueOf(orderRefundGoodsInfos[0].split("_")[0]);
						Map<String, String> itemSale = itemService.getItemSaleById(itemId);
						if (null != itemSale && org.apache.commons.lang3.StringUtils.isNotBlank(itemSale.get("SEND_CODE_CHANNEL"))
						        && Long.parseLong(itemSale.get("SEND_CODE_CHANNEL")) == 3L) {// 卡密商品，不需要退码
							logger.info("订单卡密商品id:" + itemSale.get("id"));
							tOrderRefund.setSuccessCode(successCode);

							// 执行申请退款，并且执行取消操作
							Map<String, Object> map = closeRequestService.refundAndClose(tOrderRefund, refundSource, orderRefundGoodsInfos);
							logger.info("取消订单-退款单添加成功");
							return true;
						} else {// 非卡密商品，需要退码
							List<CodeInfo> codeInfos = codeInfoService.listValidCodesByActOrderId(tOrderRefund.getOrderId());// 查询订单中是否存在有效码
							if (null != codeInfos && codeInfos.size() > 0) {
								if (null == orderIds) {
									logger.error("取消订单-请选择退款商品码！");
									return false;
								}
								// 循环退码
								for (String orderId : orderIds) {
									CodeInfo codeInfo = codeInfoService.findCodeById(orderId);
									RevokeRespInfo resule = codeInfoService.refundVerifyCode(codeInfo);
									if (null != resule && resule.getStatusCode() == 0) {
										successCode++;
									}
								}
							}
						}
					}

				}
				tOrderRefund.setSuccessCode(successCode);
				// 执行申请退款，并且执行取消操作
				Map<String, Object> map = closeRequestService.refundAndClose(tOrderRefund, refundSource, orderRefundGoodsInfos);
				tOrderRefund = (TOrderRefund) map.get("tOrderRefund");
				if (tOrderRefund.getRefundType() == 2L || tOrderRefund.getRefundType() == 4L) {
					logger.info("取消订单-退款单添加成功");
					return true;
				}
				logger.info("退款单添加成功,退码成功" + successCode + "个,失败" + (tOrderRefund.getTotalCode() - successCode) + "个");
				return true;
			} else {// 实物商品退款
				// 执行申请退款，并且执行取消操作
				Map<String, Object> map = closeRequestService.refundAndClose(tOrderRefund, refundSource, orderRefundGoodsInfos);
				logger.info("取消订单-添加成功");
				return true;
			}

		}
		catch (Exception ex) {
			logger.error(ex);
			return false;
		}
	}

	/**
	 * 取消订单前，申请退款操作
	 * 
	 * @param actOrderId
	 * @param itemMode
	 * @param refundStrategy
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean beforeCancelToRefund(Long actOrderId, HttpServletResponse response) throws Exception {
		// 获取当前订单信息
		ActOrderInfo actOrderInfo = orderService2.getOrderInfo(actOrderId);
		// 查询订单支付信息
		List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(actOrderId);
		Map<String, Object> mapPayMent = getPayMent(payOrderInfos, actOrderInfo);
		if (null == mapPayMent || mapPayMent.isEmpty()) {
			return true;
		}
		TOrderRefund tOrderRefund = null;

		// 判断是否支付中(及看是否存在可退款的金额)
		boolean isNeedRefund = isHaveRefundAmout(mapPayMent);
		if (isNeedRefund) {
			tOrderRefund = new TOrderRefund();
			// 取消时添加退款理由
			tOrderRefund.setReason("用户取消");
			// 判断订单中商品类型
			Long itemModel = Long.parseLong(ObjectUtils.toString(mapPayMent.get("itemMode")));
			tOrderRefund.setOrderId(actOrderId);
			tOrderRefund.setStoreId(Integer.parseInt(String.valueOf(actOrderInfo.getShopId())));
			// 获取订单退款类型
			Long refundType = setRefundType(actOrderInfo);
			tOrderRefund.setRefundType(Integer.parseInt(String.valueOf(refundType)));
			SessionUser sessionUser = SessionUser.getSessionUser(response);
			tOrderRefund.setCreateTime(TimeStamp.getTime(14));
			tOrderRefund.setUserId(Integer.parseInt(sessionUser.getId().toString()));

			// 设置各币种退款金额，金额设置为“分”单位
			setCancelPayFee(tOrderRefund, mapPayMent, itemModel);

			if (null != itemModel) {
				if (0 == itemModel) { // 实物商品
					// 设置退款单状态
					setOrderRefundStatusSW(tOrderRefund, objectToDouble(mapPayMent.get("totalCash")), objectToDouble(mapPayMent.get("totalCoin")),
					                       objectToDouble(mapPayMent.get("totalScore")), objectToDouble(mapPayMent.get("totalBalance")),
					                       objectToDouble(mapPayMent.get("totalRedpack")));
					// 订单下的商品
					List<Map<String, String>> actOrderGoodsList = objectToList(mapPayMent);

					String[] orderRefundGoodsInfos = orderRefundGoodsSW(actOrderGoodsList);

					return docancelOrderRefund(tOrderRefund, null, TOrderRefund.REFUND_SOURCE_MALL_CANCEL, actOrderInfo, itemModel, mapPayMent,
					                           orderRefundGoodsInfos, null);

				} else if (1 == itemModel) { // 虚拟商品
					// 设置退款单状态
					setOrderRefundStatusXN(tOrderRefund);
					String[] orderRefundGoodsInfos = new String[1];
					orderRefundGoodsInfos[0] = montage(ObjectUtils.toString(mapPayMent.get("itemId")),
					                                   ObjectUtils.toString(mapPayMent.get("goodsId")), ObjectUtils.toString(mapPayMent.get("count")));
					List<CodeInfo> codes = getCancelCodes(mapPayMent);
					String[] orderIds = actOrderGoodsCheck(codes);

					// 拉手商品是否支持退款标志，0不支持，1支持,null非拉手退款
					Long refundStrategy = (null != mapPayMent.get("refundStrategy")) ? Long.parseLong(mapPayMent.get("refundStrategy").toString())
					        : null;

					return docancelOrderRefund(tOrderRefund, refundStrategy, TOrderRefund.REFUND_SOURCE_MALL_CANCEL, actOrderInfo, itemModel,
					                           mapPayMent, orderRefundGoodsInfos, orderIds);
				}
			}
		} else {
			logger.info("不需要退款");
			return true;
		}
		logger.error("取消订单失败，请联系管理员");
		return false;

	}

	/**
	 * 计算出当前订单可退款金额信息
	 * 
	 * @param payOrderInfos
	 * @param actOrderInfo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getRefundPayAount(ActOrderInfo actOrderInfo) throws Exception {
		Map<String, Object> map = null;
		// 查询订单支付信息
		List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(actOrderInfo.getId());
		if (null != payOrderInfos && payOrderInfos.size() > 0) {
			map = new HashMap<String, Object>();
			int payBalance = 0; // 话费支付
			int payCash = 0; // 现金支付
			int paylCoin = 0; // 商城币支付
			int payScore = 0; // 积分支付
			int payRedpack = 0; // 红包支付

			int refundBalance = 0; // 话费退款
			int refundCash = 0; // 现金退款
			int refundlCoin = 0; // 商城币退款
			int refundScore = 0; // 积分退款
			int refundRedpack = 0; // 红包退款

			for (PayOrderInfo payOrderInfo : payOrderInfos) {
				// 订单支付
				if (payOrderInfo.getOperate() == RequestOperate.Pay && payOrderInfo.getStatus() == PayOrderInfo.STATUS_SUCCESS) {
					// 订单支付金额
					List<PaymentInfo> payments = payOrderInfo.getPayments();
					for (PaymentInfo paymentInfo : payments) {
						if (PayDTO.CURRENCY_BALANCE.equals(paymentInfo.getCurrency())) {
							payBalance += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_CASH.equals(paymentInfo.getCurrency())) {
							payCash += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_COIN.equals(paymentInfo.getCurrency())) {
							paylCoin += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_SCORE.equals(paymentInfo.getCurrency())) {
							payScore += payOrderInfo.getPaymentAmount();
						}
						if (PayDTO.CURRENCY_REDPACK.equals(paymentInfo.getCurrency())) {
							payRedpack += payOrderInfo.getPaymentAmount();
						}
					}
				} else if (payOrderInfo.getOperate() == RequestOperate.Refund && payOrderInfo.getStatus() == PayOrderInfo.STATUS_SUCCESS) {
					List<PaymentInfo> payments = payOrderInfo.getPayments();
					for (PaymentInfo paymentInfo : payments) {
						if (PayDTO.CURRENCY_BALANCE.equals(paymentInfo.getCurrency())) {
							refundBalance += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_CASH.equals(paymentInfo.getCurrency())) {
							refundCash += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_COIN.equals(paymentInfo.getCurrency())) {
							refundlCoin += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_SCORE.equals(paymentInfo.getCurrency())) {
							refundScore += Math.abs(payOrderInfo.getPaymentAmount());
						}
						if (PayDTO.CURRENCY_REDPACK.equals(paymentInfo.getCurrency())) {
							refundRedpack += Math.abs(payOrderInfo.getPaymentAmount());
						}
					}
				}

				// 已退款金额
				BigDecimal refunDecimal = new BigDecimal(refundBalance).add(new BigDecimal(refundCash)).add(new BigDecimal(refundlCoin))
				        .add(new BigDecimal(refundScore).add(new BigDecimal(refundRedpack)));
				map.put("refunded_yuan", CommonUtils.toYuan(Math.abs(refunDecimal.intValue())));

				// 商品类型
				Long itemMode = null;

				// 查询订单下商品
				List<Map<String, String>> actOrderGoodsList = this.findOrderGoodsListByOrderId(actOrderInfo.getId());

				if (null != actOrderGoodsList && actOrderGoodsList.size() > 0) {
					// 判断订单中商品类型，备注：订单中只有一种类型的商品
					itemMode = Long.parseLong(actOrderGoodsList.get(0).get("itemMode"));

					map.put("itemMode", itemMode);

					// 遍历商品，算出每个商品现金和商城币值
					for (int i = 0; i < actOrderGoodsList.size(); i++) {
						// 订单中虚拟商品只有一种
						if (1L == itemMode) {
							// 设置各币种最大退款金额
							if (i == 0) {
								map.put("maxBalance", this.getValue(payBalance, refundBalance));
								map.put("maxCash", this.getValue(payCash, refundCash));
								map.put("maxCoin", this.getValue(paylCoin, refundlCoin));
								map.put("maxScore", this.getValue(payScore, refundScore));
								map.put("maxRedpack", this.getValue(payRedpack, refundRedpack));
							}
						}
					}
				}
				if (null != itemMode && itemMode == 0L) {
					map.put("totalBalance", this.getValue(payBalance, refundBalance));
					map.put("totalCash", this.getValue(payCash, refundCash));
					map.put("totalCoin", this.getValue(paylCoin, refundlCoin));
					map.put("totalScore", this.getValue(payScore, refundScore));
					map.put("totalRedpack", this.getValue(payRedpack, refundRedpack));
				}
			}
		}
		return map;
	}

	/**
	 * 判断退款金额跟
	 * 
	 * @param map
	 * @param tOrderRefund
	 * @param itemMode
	 * @return
	 */
	public boolean isCheckRefundAmount(Map<String, Object> map, TOrderRefund tOrderRefund, Long itemMode) {
		logger.info("判断当前退款金额与可退金额是否一致");
		if (map != null && !map.isEmpty() && tOrderRefund != null) {
			// 实物商品操作
			if (itemMode == 0L) {
				if (Double.compare(getDoubleValue(tOrderRefund.getCashFee(), 0.0), objectToDouble(map.get("totalCash"))) != 0) {
					logger.info("实物退款---现金，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getCoinFee(), 0.0), objectToDouble(map.get("totalCoin"))) != 0) {
					logger.info("实物退款---商城币，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getScoreFee(), 0.0), objectToDouble(map.get("totalScore"))) != 0) {
					logger.info("实物退款---积分，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getPhoneFee(), 0.0), objectToDouble(map.get("totalBalance"))) != 0) {
					logger.info("实物退款---话费，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getRedpackageFee(), 0.0), objectToDouble(map.get("totalRedpack"))) != 0) {
					logger.info("实物退款---红包，退款金额错误");
					return false;
				}
				return true;
			} else if (itemMode == 1L) {
				if (Double.compare(getDoubleValue(tOrderRefund.getCashFee(), 0.0), objectToDouble(map.get("maxCash"))) != 0) {
					logger.info("虚拟退款---现金，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getCoinFee(), 0.0), objectToDouble(map.get("maxCoin"))) != 0) {
					logger.info("虚拟退款---商城币，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getScoreFee(), 0.0), objectToDouble(map.get("maxScore"))) != 0) {
					logger.info("虚拟退款---积分，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getPhoneFee(), 0.0), objectToDouble(map.get("maxBalance"))) != 0) {
					logger.info("虚拟退款---话费，退款金额错误");
					return false;
				}
				if (Double.compare(getDoubleValue(tOrderRefund.getRedpackageFee(), 0.0), objectToDouble(map.get("maxRedpack"))) != 0) {
					logger.info("虚拟退款---红包，退款金额错误");
					return false;
				}
				return true;
			}
		}
		return false;
	}
}
