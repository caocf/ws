package com.cplatform.b2c.repository;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.TActOrder;
import com.cplatform.b2c.model.TActOrderGoods;
import com.cplatform.b2c.model.TActOrderPayment;
import com.cplatform.b2c.model.TItemComment;
import com.cplatform.b2c.model.TOrderRefund;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.order.ActOrderStatus;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.cplatform.pay.PaymentInfo;

@Repository
public class MemberHnCenterRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PayServiceClient payServiceClient;

	@Autowired
	private AppConfig appConfig;

	/**
	 * 获取河南用户的积分兑换的商品
	 * 
	 * @param userId
	 *            用户编号
	 * @param name
	 *            关键字
	 * @param pageInfo
	 *            分页信息
	 * @param startTime
	 *            起始时间
	 * @param endTime
	 *            结束时间
	 * @param status
	 *            订单状态
	 * @param flag
	 * @param isdelivery
	 *            是否货到付款
	 * @param orderType
	 *            订单状态
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<TActOrder> getHnOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery, Long orderType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TActOrder.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();
		// 商品名称
		if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(specialGoodId)) {
			if (StringUtils.isNotEmpty(name) && StringUtils.isNumeric(name)) {
				criteria.add(Restrictions.eq("id", Long.valueOf(name)));
			} else {
				Criteria addcriteria = criteria.createCriteria("goodsInfos");
				if (StringUtils.isNotEmpty(name)) {
					addcriteria.add(Restrictions.like("goodsSubject", name, MatchMode.ANYWHERE));
				}
				String[] specialGoodIds = specialGoodId.split(",");
				for (String id : specialGoodIds) {
					addcriteria.add(Restrictions.ne("goodsId", Long.valueOf(id)));
				}
				// 过滤红包补差价商品id
				if (StringUtils.isNumeric(welfareGoodsId + "")) {
					addcriteria.add(Restrictions.ne("goodsId", welfareGoodsId));
				}
			}
		}

		if (StringUtils.isNotEmpty(startTime)) {
			criteria.add(Restrictions.ge("createTime", startTime + "000000"));
		}
		if (StringUtils.isNotEmpty(endTime)) {
			criteria.add(Restrictions.le("createTime", endTime + "235959"));
		}
		criteria.add(Restrictions.ne("deleteStatus", 1));
		if (StringUtils.isNotEmpty(flag) && flag.equals("0")) {
			if (StringUtils.isNotEmpty(status) && StringUtils.equals("1", status)) {
				criteria.add(Restrictions.eq("payOnDelivery", 0));
			}
			if (StringUtils.isNotBlank(isdelivery)) {
				criteria.add(Restrictions.eq("payOnDelivery", 1));
			}
		}
		if (StringUtils.isNotEmpty(status)) {
			if (StringUtils.equals("3", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_SENT), Restrictions.isNull("status")));
			} else if (StringUtils.equals("4", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_RECEIVED), Restrictions.isNull("status")));
			} else if (StringUtils.equals("5", status)) {
				criteria.add(Restrictions.eq("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			} else if (StringUtils.equals("2", status)) {
				criteria.add(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAID));
				criteria.createAlias("expressInfo", "express_info", Criteria.LEFT_JOIN);
				criteria.add(Restrictions.or(Restrictions.eq("express_info.status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND),
				                             Restrictions.isNull("express_info.status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}

		// 筛选订单类别
		criteria.add(Restrictions.eq("orderType", orderType));

		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1));
		criteria.setMaxResults(pageInfo.getPageRows());
		List<TActOrder> orders = criteria.list();

		for (TActOrder order : orders) {
			List<TActOrderGoods> goods = order.getGoodsInfos();
			boolean isXuni = false;
			String hql = "select img_path,item_mode from t_item_sale where id = :id";
			for (int i = 0; i < goods.size(); i++) {
				TActOrderGoods good = goods.get(i);
				List<Object[]> img = sessionFactory.getCurrentSession().createSQLQuery(hql).setLong("id", good.getGoodsId()).list();
				if (null != img && img.size() > 0 && null != img.get(0)[0]) {
					good.setFileName(img.get(0)[0].toString());
				} else {
					good.setFileName("");
				}
				if (null != img && img.size() > 0 && null != img.get(0)[1]) {
					if (!isXuni && StringUtils.equals("1", img.get(0)[1].toString())) {
						isXuni = true;
					}
				}

			}
			order.setXuNi(isXuni);

			Criteria cri = sessionFactory.getCurrentSession().createCriteria(TOrderRefund.class);
			cri.add(Restrictions.eq("orderId", order.getId()));
			List<TOrderRefund> refunds = cri.list();
			order.setIsRefund((null != refunds && refunds.size() > 0) ? "1" : "0");
			order.setIsComment(this.getOrderComment(order.getId()));
			if (order.getPayStatus() == 0) {
				List<TActOrderPayment> payments = order.getPayments();
				if (null != payments && payments.size() > 1) {
					try {
						List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(order.getId());
						int payCoin = 0;
						int payCash = 0;
						for (PayOrderInfo payOrderInfo : payOrderInfos) {
							if (payOrderInfo.getStatus() == PayOrderInfo.STATUS_SUCCESS) {
								List<PaymentInfo> infos = payOrderInfo.getPayments();
								for (PaymentInfo info : infos) {
									if (StringUtils.equalsIgnoreCase(info.getCurrency(), "coin")) {
										payCoin += info.getAmount();
									} else {
										payCash += info.getAmount();
									}
								}
							}
						}
						DecimalFormat fnum = new DecimalFormat("##0.00");
						if (payCash != 0) {
							order.setPayCash(fnum.format(payCash / 100));
						}
						if (payCoin != 0) {
							order.setPayCoin(String.valueOf(payCoin / 100));
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return orders;
	}

	/**
	 * 获取河南商品的订单总页数
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param flag
	 * @param isdelivery
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getHnOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery, Long orderType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TActOrder.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();
		// 商品名称
		if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(specialGoodId)) {
			if (StringUtils.isNotEmpty(name) && StringUtils.isNumeric(name)) {
				criteria.add(Restrictions.eq("id", Long.valueOf(name)));
			} else {
				Criteria addcriteria = criteria.createCriteria("goodsInfos");
				if (StringUtils.isNotEmpty(name)) {
					addcriteria.add(Restrictions.like("goodsSubject", name, MatchMode.ANYWHERE));
				}
				String[] specialGoodIds = specialGoodId.split(",");
				for (String id : specialGoodIds) {
					addcriteria.add(Restrictions.ne("goodsId", Long.valueOf(id)));
				}
				// 过滤红包补差价商品id
				if (StringUtils.isNumeric(welfareGoodsId + "")) {
					addcriteria.add(Restrictions.ne("goodsId", welfareGoodsId));
				}
			}
		}

		if (StringUtils.isNotEmpty(startTime)) {
			criteria.add(Restrictions.ge("createTime", startTime + "000000"));
		}
		if (StringUtils.isNotEmpty(endTime)) {
			criteria.add(Restrictions.le("createTime", endTime + "235959"));
		}
		criteria.add(Restrictions.ne("deleteStatus", 1));
		if (StringUtils.isNotEmpty(flag) && flag.equals("0")) {
			if (StringUtils.isNotEmpty(status) && StringUtils.equals("1", status)) {
				criteria.add(Restrictions.eq("payOnDelivery", 0));
			}
			if (StringUtils.isNotBlank(isdelivery)) {
				criteria.add(Restrictions.eq("payOnDelivery", 1));
			}
		}
		if (StringUtils.isNotEmpty(status)) {
			if (StringUtils.equals("3", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_SENT), Restrictions.isNull("status")));
			} else if (StringUtils.equals("4", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_RECEIVED), Restrictions.isNull("status")));
			} else if (StringUtils.equals("5", status)) {
				criteria.add(Restrictions.eq("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			} else if (StringUtils.equals("2", status)) {
				criteria.add(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAID));
				criteria.createAlias("expressInfo", "express_info", Criteria.LEFT_JOIN);
				criteria.add(Restrictions.or(Restrictions.eq("express_info.status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND),
				                             Restrictions.isNull("express_info.status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}

		// 过滤商品订单的类别
		criteria.add(Restrictions.eq("orderType", orderType));

		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TActOrder> list = criteria.list();
		pageInfo.setRecordCount(list.size());
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}

	/**
	 * 获取订单
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getHnOrdersByStatus(String userId, String status, String isdelivery, Long orderType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TActOrder.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();
		if (StringUtils.isNotEmpty(specialGoodId)) {
			Criteria addcriteria = criteria.createCriteria("goodsInfos");
			String[] specialGoodIds = specialGoodId.split(",");
			for (String id : specialGoodIds) {
				addcriteria.add(Restrictions.ne("goodsId", Long.valueOf(id)));
			}
			// 过滤红包补差价商品id
			if (StringUtils.isNumeric(welfareGoodsId + "")) {
				addcriteria.add(Restrictions.ne("goodsId", welfareGoodsId));
			}
		}
		criteria.add(Restrictions.ne("deleteStatus", 1));
		criteria.add(Restrictions.ne("closeStatus", 1));
		if (StringUtils.isNotBlank(isdelivery)) {
			criteria.add(Restrictions.eq("payOnDelivery", Integer.parseInt(isdelivery)));
		}
		if (StringUtils.isNotEmpty(status)) {
			if (StringUtils.equals("3", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
				addcriteria.add(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_SENT));
			} else {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
			}
		}

		// 过滤商品订单的类别
		criteria.add(Restrictions.eq("orderType", orderType));

		List<TActOrder> list = criteria.list();
		return list.size();
	}

	/**
	 * 获取货到付款的订单
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getHnOrdersByDelivery(String userId, String isdelivery, Long orderType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TActOrder.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();
		if (StringUtils.isNotEmpty(specialGoodId)) {
			Criteria addcriteria = criteria.createCriteria("goodsInfos");
			String[] specialGoodIds = specialGoodId.split(",");
			for (String id : specialGoodIds) {
				addcriteria.add(Restrictions.ne("goodsId", Long.valueOf(id)));
			}
			// 过滤红包补差价商品id
			if (StringUtils.isNumeric(welfareGoodsId + "")) {
				addcriteria.add(Restrictions.ne("goodsId", welfareGoodsId));
			}
		}
		criteria.add(Restrictions.ne("deleteStatus", 1));
		// criteria.add(Restrictions.ne("closeStatus", 1));
		if (StringUtils.isNotBlank(isdelivery)) {
			criteria.add(Restrictions.eq("payOnDelivery", Integer.parseInt(isdelivery)));
		}

		// 过滤商品订单的类别
		criteria.add(Restrictions.eq("orderType", orderType));

		List<TActOrder> list = criteria.list();
		return list.size();
	}

	/**
	 * 商品的评论
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getOrderComment(Long orderId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TItemComment.class);
		criteria.add(Restrictions.eq("actOrderId", orderId));
		List<TItemComment> comments = criteria.list();
		if (null != comments && comments.size() > 0) {
			return "1";
		} else {
			return "0";
		}
	}

}
