package com.cplatform.b2c.repository;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.CardGift;
import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.SzMallOrderHistory;
import com.cplatform.b2c.model.TActOrder;
import com.cplatform.b2c.model.TActOrderGoods;
import com.cplatform.b2c.model.TActOrderNew;
import com.cplatform.b2c.model.TActOrderPayment;
import com.cplatform.b2c.model.TItemComment;
import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.b2c.model.TOrderRefund;
import com.cplatform.b2c.model.TUserCenterVisitLog;
import com.cplatform.b2c.service.PerformService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.ArrayUtil;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderStatus;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.cplatform.pay.PaymentInfo;

/**
 * 个人中心数据层 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Jun 6, 2013 4:19:21 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Repository
public class MemberCenterRepository {

	private static Log log = LogFactory.getLog(MemberCenterRepository.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PayServiceClient payServiceClient;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private PerformService performService;

	@Autowired
	DbHelper dbHelper;

	/**
	 * 获取我的评价
	 * 
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getComments(PageInfo pageInfo, String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id,");
		sql.append("       g.G_SHORT_NAME,");
		sql.append("       g.G_WEB_PATH,");
		sql.append("       t.content commentContent,");
		sql.append("       decode(t.rank, null, 0, t.rank),");
		sql.append("       to_date(t.update_time, 'yyyy-mm-dd hh24:mi:ss') commentTime,");
		sql.append("       decode(r.content,null,'',r.content) replyContent,");
		sql.append("       to_date(r.update_time, 'yyyy-mm-dd hh24:mi:ss') replyTime,");
		sql.append("       decode(r.nickname,null,'',r.nickname),");
		sql.append("       g.G_ID,");
		sql.append("       t.status");
		sql.append("  from t_item_comment t");
		sql.append("  left join t_item_comment_reply r on t.id = r.comment_id, v_search_good g");
		sql.append(" where t.sale_id = g.G_ID");
		sql.append("   and t.type = 1 and t.user_id = :userId");
		sql.append(" order by t.id desc");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();

	}

	/**
	 * 获取我的评价总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getCommentsScript(PageInfo pageInfo, String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(t.id)");
		sql.append("  from t_item_comment t");
		sql.append("  left join t_item_comment_reply r on t.id = r.comment_id, v_search_good g");
		sql.append(" where t.sale_id = g.G_ID");
		sql.append("   and t.type = 1 and t.user_id = :userId");
		sql.append(" order by t.id desc");
		Object rowTotal = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId)).list().get(0);
		pageInfo.setRecordCount(Integer.valueOf(rowTotal.toString()));
		pageInfo.setPageTotals();

		return pageInfo.getScript();
	}

	/**
	 * 获取我的咨询
	 * 
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getConsults(PageInfo pageInfo, String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id,");
		sql.append("       g.G_SHORT_NAME,");
		sql.append("       g.G_WEB_PATH,");
		sql.append("       t.content commentContent,");
		sql.append("       decode(t.question_type,1,'商品咨询',2,'促销活动咨询',3,'库存及物流咨询',4,'售后咨询'),");
		sql.append("       to_date(t.update_time, 'yyyy-mm-dd hh24:mi:ss') commentTime,");
		sql.append("       r.content replyContent,");
		sql.append("       to_date(r.update_time, 'yyyy-mm-dd hh24:mi:ss') replyTime,");
		sql.append("       r.nickname,");
		sql.append("       g.G_ID,");
		sql.append("       t.status");
		sql.append("  from t_item_comment t");
		sql.append("  left join t_item_comment_reply r on t.id = r.comment_id, v_search_good g");
		sql.append(" where t.sale_id = g.G_ID");
		sql.append("   and t.type = 2 and t.user_id = :userId");
		sql.append(" order by t.id desc");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();
	}

	/**
	 * 获取我的咨询总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getConsultsScript(PageInfo pageInfo, String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(t.id)");
		sql.append("  from t_item_comment t");
		sql.append("  left join t_item_comment_reply r on t.id = r.comment_id, v_search_good g");
		sql.append(" where t.sale_id = g.G_ID");
		sql.append("   and t.type = 2 and t.user_id = :userId");
		sql.append(" order by t.id desc");
		Object rowTotal = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId)).list().get(0);
		pageInfo.setRecordCount(Integer.valueOf(rowTotal.toString()));
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}

	/**
	 * 获取我的收货地址
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getAddresses(String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,");
		sql.append("       a.name,");
		sql.append("       r.region,");
		sql.append("       a.address,");
		sql.append("       a.zipcode,");
		sql.append("       decode(a.mobile, null, '', a.mobile || ' ') || a.phone,");
		sql.append("       a.default_shipping");
		sql.append("  from t_member_address a, v_region r");
		sql.append(" where a.region = r.region_code");
		sql.append("   and a.mid = :userId");

		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId)).list();
	}

	/**
	 * 保存或更新收货地址
	 * 
	 * @param address
	 */
	public boolean saveOrUpdateAddress(TMemberAddress address) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(address);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取地址
	 * 
	 * @param id
	 * @return
	 */
	public TMemberAddress getAddress(String id) {
		try {
			return (TMemberAddress) sessionFactory.getCurrentSession().get(TMemberAddress.class, Integer.valueOf(id));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除地址
	 * 
	 * @param address
	 * @return
	 */
	public boolean delAddress(TMemberAddress address) {
		try {
			sessionFactory.getCurrentSession().delete(address);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 设置默认地址
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public boolean setAddress(String id, String userId) {
		int cnt = sessionFactory.getCurrentSession().createSQLQuery("update t_member_address set default_shipping = 0 where mid = :userId")
		        .setLong("userId", Long.valueOf(userId)).executeUpdate();
		if (cnt > 0) {
			cnt = sessionFactory.getCurrentSession().createSQLQuery("update t_member_address set default_shipping = 1 where id = :id")
			        .setLong("id", Long.valueOf(id)).executeUpdate();
		}
		if (cnt > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取地区编码
	 * 
	 * @param regionName
	 * @return
	 */
	public String getRegionCode(String regionName) {
		return (String) sessionFactory.getCurrentSession().createSQLQuery("select region_code from t_sys_region where region_name = :regionName")
		        .setString("regionName", regionName).list().get(0);
	}

	/**
	 * 保存评论
	 * 
	 * @param comment
	 * @return
	 */
	public boolean saveComment(TItemComment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getRegion(String type, String parentId) {
		return sessionFactory.getCurrentSession()
		        .createSQLQuery("select region_name, region_code from t_sys_region where region_level = :type and parent_region = :parentId")
		        .setLong("type", Long.valueOf(type)).setString("parentId", parentId).list();
	}

	/**
	 * 获取用户订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TActOrder> getOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery) {
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
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND), Restrictions.isNull("status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}
		// 1是竞拍，2是秒杀，3是团购，10是红包，20是拉手团购，30.,31是演出票，50,51是河南商品，60是代金券，1000是礼品卡
		criteria.add(Restrictions.ne("orderType", 1L));
		criteria.add(Restrictions.ne("orderType", 2L));
		criteria.add(Restrictions.ne("orderType", 3L));
		criteria.add(Restrictions.ne("orderType", 10L));
		criteria.add(Restrictions.ne("orderType", 20L));
		criteria.add(Restrictions.ne("orderType", 60L));
		criteria.add(Restrictions.ne("orderType", 30L));
		criteria.add(Restrictions.ne("orderType", 31L));
		criteria.add(Restrictions.ne("orderType", 50L));
		criteria.add(Restrictions.ne("orderType", 51L));
		criteria.add(Restrictions.ne("orderType", 1000L));
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
			// if (isXuni) {
			// List<Object> codeStatus = getVerifyCode(order.getId());
			// if (null != codeStatus && codeStatus.size() > 0) {
			// String ss = codeStatus.get(0).toString();
			// if (StringUtils.equals("2", ss)) {
			// order.setStatus(4);
			// }
			// }
			// }
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getVerifyCode(Long orderId) {
		String sql = "select code_status, code, expire_date,CODE_2D from t_verify_code_info where act_order_id =  :orderId";
		List<Object[]> codeStatus = sessionFactory.getCurrentSession().createSQLQuery(sql).setLong("orderId", orderId).list();
		return codeStatus;
	}

	/**
	 * 订单是否评价
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

	/**
	 * 获取退单信息
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TOrderRefund> getRefunds(Long orderId, ActOrderInfo info) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TOrderRefund.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		List<TOrderRefund> refunds = criteria.list();
		if (null != refunds && refunds.size() > 0) {
			DecimalFormat fnum = new DecimalFormat("##0.00");
			StringBuffer sql = new StringBuffer();
			sql.append("select g.goods_id, g.back_number, g.cash, g.coin, o.goods_subject, o.count");
			sql.append("  from t_order_refund_goods g, t_act_order_goods o");
			sql.append(" where g.order_goods_id = o.id and g.refund_id = :refundId");
			for (TOrderRefund refund : refunds) {
				refund.setTotalPrice(fnum.format(Float.valueOf(info.getTotalPayAmount()) / 100));
				// refund.setTotalPrice("2500.00");
				List<Object[]> goods = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("refundId", refund.getId()).list();
				refund.setRefundGoods(goods);
			}
		}
		return refunds;
	}

	/**
	 * 获取我的订单总页数
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery) {
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
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND), Restrictions.isNull("status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}

		criteria.add(Restrictions.ne("orderType", 1L));
		criteria.add(Restrictions.ne("orderType", 2L));
		criteria.add(Restrictions.ne("orderType", 3L));
		criteria.add(Restrictions.ne("orderType", 10L));
		criteria.add(Restrictions.ne("orderType", 20L));
		criteria.add(Restrictions.ne("orderType", 30L));
		criteria.add(Restrictions.ne("orderType", 31L));
		criteria.add(Restrictions.ne("orderType", 50L));
		criteria.add(Restrictions.ne("orderType", 51L));
		criteria.add(Restrictions.ne("orderType", 60L));
		criteria.add(Restrictions.ne("orderType", 1000L));
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
	public int getOrdersByStatus(String userId, String status, String isdelivery,Long [] orderTypes) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TActOrder.class);
		if(ArrayUtil.isNotEmpty(orderTypes)){
			criteria.add(Restrictions.in("orderType", orderTypes));
		}
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
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_SENT), Restrictions.isNull("status")));
			} else {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
			}
		}

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
	public int getOrdersByDelivery(String userId, String isdelivery) {
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
		criteria.add(Restrictions.eq("orderType", 0L));

		List<TActOrder> list = criteria.list();
		return list.size();
	}

	/**
	 * 获取某个订单中的商品
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getSaleIdByOrder(String orderId) {
		List<Object[]> saleIds = sessionFactory.getCurrentSession()
		        .createSQLQuery("select goods_id from t_act_order_goods where act_order_id = :orderId").setLong("orderId", Long.valueOf(orderId))
		        .list();
		StringBuffer res = new StringBuffer();
		if (null != saleIds && saleIds.size() > 0) {
			for (int i = 0; i < saleIds.size(); i++) {
				if (i > 0) {
					res.append(",");
				}
				res.append(saleIds.get(i));
			}

		}
		return res.toString();
	}

	/**
	 * 获取我的收藏商品
	 * 
	 * @param userId
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getFavoriteGoods(String userId, PageInfo pageInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("select f.id,");
		sql.append("       f.favorite_id,");
		sql.append("       s.img_path,");
		sql.append("       s.market_price,");
		sql.append("       s.shop_price,");
		sql.append("       st.id shopId,");
		sql.append("       st.name,");
		sql.append("       s.name sale_name");
		sql.append("  from t_member_favorite f, t_item_sale s, t_store st");
		sql.append(" where f.favorite_id = s.id");
		sql.append("   and s.store_id = st.id");
		sql.append("   and f.favorite_type = 1");
		sql.append("   and f.user_id = :userId");

		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();
	}

	/**
	 * 获取我的收藏商品总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getFavoriteGoodsScript(PageInfo pageInfo, String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)");
		sql.append("  from t_member_favorite f, t_item_sale s, t_store st");
		sql.append(" where f.favorite_id = s.id");
		sql.append("   and s.store_id = st.id");
		sql.append("   and f.favorite_type = 1");
		sql.append("   and f.user_id = :userId");
		Object rowTotal = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId)).list().get(0);
		pageInfo.setRecordCount(Integer.valueOf(rowTotal.toString()));
		pageInfo.setPageTotals();

		return pageInfo.getScript();
	}

	/**
	 * 删除收藏
	 * 
	 * @param userId
	 * @return
	 */
	public boolean delFavorite(Integer[] favoriteIds, String userId, String type) {
		if (StringUtils.equals("1", type)) {
			sessionFactory.getCurrentSession().createSQLQuery("update t_item_sale_ext set collect_num = collect_num - 1 where sale_id in (:saleId)")
			        .setParameterList("saleId", favoriteIds).executeUpdate();
		}
		int cnt = sessionFactory.getCurrentSession()
		        .createQuery("delete from TMemberFavorite where favoriteId in (:favoriteIds) and userId = :userId and favorite_type = :type")
		        .setParameterList("favoriteIds", favoriteIds).setLong("userId", Long.valueOf(userId)).setLong("type", Long.valueOf(type))
		        .executeUpdate();
		return cnt > 0;
	}

	/**
	 * 获取我的收藏商户
	 * 
	 * @param userId
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getFavoriteStores(String userId, PageInfo pageInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("select f.id, f.favorite_id, s.name, v.shortname");
		sql.append("  from t_member_favorite f, t_store s left join v_region v on s.area_code = v.region_code");
		sql.append(" where f.favorite_id = s.id");
		sql.append("   and f.favorite_type = '2'");
		sql.append("   and f.user_id = :userId");

		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();
	}

	/**
	 * 获取我的收藏商户总页数
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getFavoriteStoresScript(PageInfo pageInfo, String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)");
		sql.append("  from t_member_favorite f, t_store s left join v_region v on s.area_code = v.region_code");
		sql.append(" where f.favorite_id = s.id");
		sql.append("   and f.favorite_type = '2'");
		sql.append("   and f.user_id = :userId");
		Object rowTotal = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userId", Long.valueOf(userId)).list().get(0);
		pageInfo.setRecordCount(Integer.valueOf(rowTotal.toString()));
		pageInfo.setPageTotals();

		return pageInfo.getScript();
	}

	public String getgCheapOrders(String userId, String name, String startTime, String endTime, String type, String curpage) throws Exception {
		String jsonResponce = "";

		StringBuilder param = new StringBuilder();

		param.append("userId=");
		if (!StringUtils.isEmpty(userId)) {
			param.append(userId);
		}
		param.append("&goodsName=");
		if (!StringUtils.isEmpty(name)) {
			param.append(URLEncoder.encode(name, "UTF-8"));
		}
		param.append("&startTime=");
		if (!StringUtils.isEmpty(startTime)) {
			param.append(startTime);
		}
		param.append("&endTime=");
		if (!StringUtils.isEmpty(endTime)) {
			param.append(endTime);
		}
		param.append("&type=");
		if (!StringUtils.isEmpty(type)) {
			param.append(type);
		}
		param.append("&pageNo=");
		if (!StringUtils.isEmpty(curpage)) {
			param.append(curpage);
		}
		// 执行post请求
		log.info("开始获取G实惠商品");
		jsonResponce = HttpClientUtils.httpGet(appConfig.getgCheapOrderUrl(), param.toString(), 0, 0);
		log.info("结束获取G实惠商品");
		// JSONArray jsonObject1 = JSONArray.fromObject(jsonResponce);
		// List<SeckillGoods> list = JSONArray.toList(jsonObject1, new
		// SeckillGoods(), new JsonConfig());
		// return list;
		return jsonResponce;

	}

	public String getgCheapDeposit(Long userId) {
		String jsonResponce = "";
		try {
			// 执行post请求
			jsonResponce = HttpClientUtils.httpGet(appConfig.getgCheapDepositUrl(), "userId=" + userId, 0, 0);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResponce;
	}

	public Date getGCheapCreateTime(String businessId, String type) {
		Date createTime = new Date();
		if (null != type) {
			if (type.equals("1")) {
				String auctionSql = "select CREATE_TIME from T_MARKET_ORDER where  ID =  :businessId";
				List<Date> ls = sessionFactory.getCurrentSession().createSQLQuery(auctionSql).setLong("businessId", Long.parseLong(businessId))
				        .list();
				if (ls != null && ls.size() > 0) {
					createTime = ls.get(0);
				}
			} else if (type.equals("2")) {
				String seckillSql = "select CREATE_TIME from T_MARKET_SECKILL where  ID =  :businessId";
				List<Date> ls = sessionFactory.getCurrentSession().createSQLQuery(seckillSql).setLong("businessId", Long.parseLong(businessId))
				        .list();
				if (ls != null && ls.size() > 0) {
					createTime = ls.get(0);
				}
			}
		}
		return createTime;

	}

	@SuppressWarnings("unchecked")
	public boolean isExistMobile(String mobile) {
		String sql = "select id from t_member where terminal_id =  :mobile";
		List<Object[]> idList = sessionFactory.getCurrentSession().createSQLQuery(sql).setString("mobile", mobile).list();
		if (idList != null && idList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean bindMobile(Long userId, String mobile) {
		String sql = "update t_member set terminal_id = :mobile where id = :userId";
		int cnt = sessionFactory.getCurrentSession().createSQLQuery(sql).setString("mobile", mobile).setLong("userId", userId).executeUpdate();
		return cnt > 0;
	}

	@SuppressWarnings("unchecked")
	public List<SzMallOrderHistory> getSZMallOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SzMallOrderHistory.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		if (StringUtils.isNotEmpty(name)) {
			criteria.add(Restrictions.like("goodsName", "%" + name + "%"));
		}
		if (StringUtils.isNotEmpty(startTime)) {
			criteria.add(Restrictions.ge("orderTime", startTime + "000000"));
		}
		if (StringUtils.isNotEmpty(endTime)) {
			criteria.add(Restrictions.le("orderTime", endTime + "235959"));
		}
		if (StringUtils.isNotEmpty(status) && StringUtils.isNumeric(status)) {
			criteria.add(Restrictions.eq("status", status));
		}
		criteria.addOrder(Order.desc("orderTime"));
		criteria.setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1));
		criteria.setMaxResults(pageInfo.getPageRows());
		List<SzMallOrderHistory> mallOrderHistories = criteria.list();

		return mallOrderHistories;
	}

	@SuppressWarnings("unchecked")
	public String getSZMallOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SzMallOrderHistory.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		if (StringUtils.isNotEmpty(name)) {
			criteria.add(Restrictions.like("goodsName", name));
		}
		if (StringUtils.isNotEmpty(startTime)) {
			criteria.add(Restrictions.ge("orderTime", startTime + "000000"));
		}
		if (StringUtils.isNotEmpty(endTime)) {
			criteria.add(Restrictions.le("orderTime", endTime + "235959"));
		}
		if (StringUtils.isNotEmpty(status) && StringUtils.isNumeric(status)) {
			criteria.add(Restrictions.eq("status", status));
		}
		criteria.addOrder(Order.desc("orderTime"));
		List<SzMallOrderHistory> mallOrderHistories = criteria.list();
		pageInfo.setRecordCount(mallOrderHistories.size());
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCardGifts(String phone, PageInfo pageInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("select d.*");
		sql.append("  from T_MARKET_CARD_GIFT_DETAILS d,T_MARKET_CARD_GIFT_CHANNEL c");
		sql.append(" where d.card_id=c.card_id");
		sql.append("   and c.channel_id=1");
		sql.append("   and d.MOBILE = :mobile");
		sql.append("   order by d.create_time desc");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setString("mobile", phone)
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();

	}

	@SuppressWarnings("unchecked")
	public String getCardGiftsScript(String phone, PageInfo pageInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)");
		sql.append("  from T_MARKET_CARD_GIFT_DETAILS d,T_MARKET_CARD_GIFT_CHANNEL c");
		sql.append(" where d.card_id=c.card_id");
		sql.append("   and c.channel_id=1");
		sql.append("   and d.MOBILE = :mobile");
		sql.append("   order by d.create_time desc");
		Object rowTotal = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setString("mobile", phone).list().get(0);
		pageInfo.setRecordCount(Integer.valueOf(rowTotal.toString()));
		pageInfo.setPageTotals();

		return pageInfo.getScript();
	}

	public List<CardGift> getGiftCardByCardId(Long cardId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CardGift.class);
		criteria.add(Restrictions.eq("id", cardId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TActOrder> getGiftOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
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
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND), Restrictions.isNull("status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}

		criteria.add(Restrictions.eq("orderType", 1000L));
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
			// if (isXuni) {
			// List<Object> codeStatus = getVerifyCode(order.getId());
			// if (null != codeStatus && codeStatus.size() > 0) {
			// String ss = codeStatus.get(0).toString();
			// if (StringUtils.equals("2", ss)) {
			// order.setStatus(4);
			// }
			// }
			// }
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	public String getGiftOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
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
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND), Restrictions.isNull("status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}

		criteria.add(Restrictions.eq("orderType", 1000L));
		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TActOrder> list = criteria.list();
		pageInfo.setRecordCount(list.size());
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}

	/**
	 * 验证改手机号是否有参与红包的资格
	 * 
	 * @param terminalId
	 * @return
	 */
	public boolean getBonusTerminal(Long terminalId) {
		String sqlString = "select  id from T_BONUS_TERMINAL where TERMINAL_ID = :terminalId";
		List<String[]> list = sessionFactory.getCurrentSession().createSQLQuery(sqlString).setLong("terminalId", terminalId).list();
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<TActOrderNew> getRedOrders(String userId, PageInfo pageInfo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TActOrderNew.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();

		criteria.add(Restrictions.ne("deleteStatus", 1));
		criteria.add(Restrictions.eq("orderType", 10L));
		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1));
		criteria.setMaxResults(pageInfo.getPageRows());
		List<TActOrderNew> orders = criteria.list();

		for (TActOrderNew order : orders) {

			// 商品名称
			if (StringUtils.isNotEmpty(specialGoodId)) {
				Criteria addcriteria = sessionFactory.getCurrentSession().createCriteria(TActOrderGoods.class);
				String[] specialGoodIds = specialGoodId.split(",");
				for (String id : specialGoodIds) {
					addcriteria.add(Restrictions.ne("goodsId", Long.valueOf(id)));
				}
				// 过滤红包补差价商品id
				if (StringUtils.isNumeric(welfareGoodsId + "")) {
					addcriteria.add(Restrictions.ne("goodsId", welfareGoodsId));
				}

				addcriteria.add(Restrictions.eq("order.id", order.getId()));
				order.setGoodsInfos(addcriteria.list());
			}

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
			// if (isXuni) {
			// List<Object> codeStatus = getVerifyCode(order.getId());
			// if (null != codeStatus && codeStatus.size() > 0) {
			// String ss = codeStatus.get(0).toString();
			// if (StringUtils.equals("2", ss)) {
			// order.setStatus(4);
			// }
			// }
			// }
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	public String getRedOrdersScript(String userId, PageInfo pageInfo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TActOrderNew.class);
		criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
		criteria.add(Restrictions.ne("deleteStatus", 1));
		criteria.add(Restrictions.eq("orderType", 10L));
		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TActOrderNew> list = criteria.list();
		pageInfo.setRecordCount(list.size());
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}

	@SuppressWarnings("unchecked")
	public List<TActOrder> getGroupBuyOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status,
	        String movieType) {
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
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND), Restrictions.isNull("status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}
		if (StringUtils.isNotBlank(movieType)) {
			criteria.add(Restrictions.eq("orderType", 40L));
		} else {
			criteria.add(Restrictions.or(Restrictions.eq("orderType", 3L), Restrictions.eq("orderType", 20L)));
		}

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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	public String getGroupBuyOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status,
	        String movieType) {
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
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND), Restrictions.isNull("status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}
		if (StringUtils.isNotBlank(movieType)) {
			criteria.add(Restrictions.eq("orderType", 40L));
		} else {
			criteria.add(Restrictions.or(Restrictions.eq("orderType", 3L), Restrictions.eq("orderType", 20L)));
		}
		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TActOrder> list = criteria.list();
		pageInfo.setRecordCount(list.size());
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}

	@SuppressWarnings("unchecked")
	public List<TActOrder> getGoodsOrders(String userId, String name, String startTime, String endTime, String status) {
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
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.or(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND), Restrictions.isNull("status")));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}
		// 代金券ordertype=60
		criteria.add(Restrictions.eq("orderType", 60L));
		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult(0);
		criteria.setMaxResults(20);
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
	 * 查询商盟的代金券
	 * 
	 * @param mobile
	 * @param name
	 *            :搜藏关键字
	 * @param startTime
	 * @param endTime
	 * @param status
	 *            ：order状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TActOrder> getGoodsOrders777(String mobile, String name, String startTime, String endTime, String status) {
		// 判断参数
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		if (StringUtils.isBlank(name)) {
			name = "";// 匹配区别字符
		}
		if (StringUtils.isBlank(startTime)) {
			startTime = "20000101000000";// 2000年1月1日
		}
		if (StringUtils.isBlank(endTime)) {
			endTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // 今天
		}
		// 取得商盟的schema
		String schema777 = appConfig.getSchema777();
		if (StringUtils.isBlank(schema777)) {
			return null;
		}
		List<TActOrder> orderList = new ArrayList<TActOrder>();
		StringBuffer sql = new StringBuffer();

		endTime += "235959";
		/**
		 * SQL
		 */
		// sql.append("select distinct o2oorder.user_id, o2oorder.good_id,
		// o2oorder.good_title as orderTitle, ")
		// .append("good.good_title as good_title,
		// userOrder.create_time,o2oorder.mobile, ")
		// .append("userOrder.id as order_id,o2oorder.amount,o2oorder.status,
		// shop.name ").append("from cylm.t_12580_o2o_order o2oorder ")
		// .append("left join cylm.t_12580_user_order_link link ").append("on
		// link.link_id = o2oorder.id ")
		// .append("left join cylm.t_12580_user_order userOrder ").append("on
		// link.order_id = userOrder.Id ")
		// .append("left join cylm.t_12580_o2o_good good ").append("on good.id =
		// o2oorder.good_id ").append("left join cylm.t_12580_shop shop ")
		// .append("on good.shop_id = shop.id ").append("left join
		// cylm.t_12580_user_order_cart cart ")
		// .append("on userOrder.cart_id = cart.cart_id ").append("where
		// o2oorder.mobile = :mobile ").append("AND good.good_title like :name
		// ")
		// .append("AND o2oorder.create_time >= :startTime ").append("AND
		// o2oorder.create_time <= :endTime ")
		// .append("AND userOrder.id is not null ").append("order by
		// userOrder.create_time desc ");
		sql.append("select distinct o2oorder.user_id, ");
		sql.append("                o2oorder.good_id,");
		sql.append("                o2oorder.good_title as orderTitle,");
		sql.append("                good.good_title as good_title,");
		sql.append("                userOrder.create_time,");
		sql.append("                o2oorder.mobile,");
		sql.append("                userOrder.id as order_id,");
		sql.append("                o2oorder.amount,");
		sql.append("                userOrder.state,");
		sql.append("                good.goods_pic_path,");
		sql.append("                o2oorder.id");
		sql.append("  from cylm.t_12580_o2o_order o2oorder");
		sql.append("  left join cylm.t_12580_user_order_link link on link.link_id = o2oorder.id and link.link_type='16'");
		sql.append("  left join cylm.t_12580_user_order userOrder on link.order_id =");
		sql.append("                                                 userOrder.Id");
		sql.append("  left join cylm.t_12580_o2o_good good on good.id = o2oorder.good_id");
		sql.append(" where o2oorder.mobile = :mobile ");
		sql.append("   AND good.good_title like :name ");
		sql.append("   AND userOrder.create_time >= :startTime ");
		sql.append("   AND userOrder.create_time <= :endTime ");
		sql.append("   AND userOrder.id is not null ");
		sql.append("   and good.good_title not like '%汽车票%' ");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		query.setParameter("mobile", mobile);
		query.setParameter("name", "%" + name + "%");
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
		log.info("SQL:" + sql.toString());
		log.info("SQL Parameter:" + mobile + " " + name + " " + startTime + "  " + endTime);
		List<Object[]> searchList = query.list();
		if (null == searchList || searchList.size() <= 0) {
			return orderList;
		}
		log.info("SQL list.size=" + searchList.size());
		// 读取并组装TActOrder
		for (Object[] objs : searchList) {
			TActOrder order = new TActOrder();
			try { // 防止映射错误
				BigDecimal userIdObj = new BigDecimal(0); // user_id
				if (null != (BigDecimal) objs[0]) {
					userIdObj = (BigDecimal) objs[0];
				}
				BigDecimal goodIdObj = new BigDecimal(0); // good_id
				if (null != (BigDecimal) objs[1]) {
					goodIdObj = ((BigDecimal) objs[1]);
				}
				String orderTitleObj = ""; // orderTitle
				if (null != (String) objs[2]) {
					orderTitleObj = (String) objs[2];
				}
				String goodTitleObj = ""; // good_title
				if (null != (String) objs[3]) {
					goodTitleObj = (String) objs[3];
				}
				String createTimeObj = ""; // create_time
				if (null != (String) objs[4]) {
					createTimeObj = (String) objs[4];
				}

				String mobileObj = ""; // mobile
				if (null != (String) objs[5]) {
					mobileObj = (String) objs[5];
				}
				BigDecimal orderIdObj = new BigDecimal(0); // order_id
				if (objs[6] instanceof String) {
					orderIdObj = new BigDecimal((String) objs[6]);
				} else if (objs[6] instanceof BigDecimal) {
					orderIdObj = (BigDecimal) objs[6];
				}

				BigDecimal amoutObj = new BigDecimal(0); // amount
				if (null != (BigDecimal) objs[7]) {
					amoutObj = ((BigDecimal) objs[7]);
				}

				BigDecimal statusObj = new BigDecimal(0); // status 默认0，未付款
				if (objs[8] instanceof String) {
					statusObj = new BigDecimal((String) objs[8]);
				} else if (objs[8] instanceof BigDecimal) {
					statusObj = (BigDecimal) objs[8];
				}
				// 商户信息不展示
				// String shopObj = ""; // mobile
				// if (null != (String) objs[9]) {
				// shopObj = (String) objs[9];
				// }
				String goodsPic = "";
				if (null != (String) objs[9]) {
					goodsPic = (String) objs[9];
				}

				BigDecimal o2oOrderId = new BigDecimal(0);
				if (null != (BigDecimal) objs[10]) {
					o2oOrderId = (BigDecimal) objs[10];
				}

				order.setUserId(userIdObj.longValue());
				order.setGoodsName(goodTitleObj);
				order.setCreateTime(createTimeObj);
				// order.setUserId(Long.parseLong(mobileObj));
				order.setId(orderIdObj.longValue());
				order.setPayAmount(amoutObj.toString());
				order.setStatus(statusObj.intValue()); // 订单状态0--未付款 1--已付款
				// 2--失效 3--退款处理中 4--退款成功
				// 5--已消费（验证码已验证）6已消费退款
				order.setOrderType(60L);// 代金券60；
				// order.setShopSubject(shopObj);//

				order.setSource("1");// 商盟

				List<TActOrderGoods> goods = order.getGoodsInfos();
				TActOrderGoods good = new TActOrderGoods();
				good.setFileName(goodsPic);
				good.setGoodsId(goodIdObj.longValue());
				goods.add(good);

				order.setGoodsInfos(goods);
				order.setSubject(o2oOrderId.toString());// 存放商盟业务订单号
				order.setRemark(mobileObj);// 存放商盟业务订单用户手机号码
				orderList.add(order);
			}
			catch (Exception e) {
				log.error("出错：代金券 - 个人中心 - 商盟数据 - 数据映射,mobile=" + mobile, e);
			}
		}

		return orderList;
	}

	/**
	 * 查询我的演出票订单
	 * 
	 * @param userId
	 * @param name
	 * @param pageInfo
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TActOrder> getPerformOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
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
		if (StringUtils.isNotEmpty(status)) {
			if (StringUtils.equals("3", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
				addcriteria.add(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_SENT));
			} else if (StringUtils.equals("4", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_RECEIVED));
			} else if (StringUtils.equals("5", status)) {
				criteria.add(Restrictions.eq("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			} else if (StringUtils.equals("2", status)) {
				criteria.add(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAID));
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}

		criteria.add(Restrictions.or(Restrictions.eq("orderType", 30L), Restrictions.eq("orderType", 31L)));
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
				Map<String, String> ticketInfo = performService.getPerformTicketInfo(Long.toString(good.getGoodsId()));
				if (ticketInfo != null) {
					good.setTicketTime(ticketInfo.get("ticket_time"));
					good.setUrl(ticketInfo.get("url"));
					good.setImgPath(ticketInfo.get("img_path"));
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	public String getPerformOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status) {
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
		if (StringUtils.isNotEmpty(status)) {
			if (StringUtils.equals("3", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
				addcriteria.add(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_SENT));
			} else if (StringUtils.equals("4", status)) {
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_RECEIVED));
			} else if (StringUtils.equals("5", status)) {
				criteria.add(Restrictions.eq("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			} else if (StringUtils.equals("2", status)) {
				criteria.add(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAID));
				Criteria addcriteria = criteria.createCriteria("expressInfo");
				addcriteria.add(Restrictions.eq("status", ActOrderStatus.EXPRESS_STATUS_NOT_SEND));
			} else if (StringUtils.equals("1", status)) {
				criteria.add(Restrictions.or(Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_UNPAID),
				                             Restrictions.eq("payStatus", ActOrderStatus.PAY_STATUS_PAYING)));
				criteria.add(Restrictions.ne("closeStatus", ActOrderStatus.CLOSE_STATUS_CLOSED));
			}
		}

		criteria.add(Restrictions.or(Restrictions.eq("orderType", 30L), Restrictions.eq("orderType", 31L)));
		criteria.addOrder(Order.desc("createTime"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TActOrder> list = criteria.list();
		pageInfo.setRecordCount(list.size());
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}

	@SuppressWarnings("unchecked")
	public List<TActOrderNew> getNewOrders(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status,
	        String flag, String isdelivery,Long[] orderTypes) {

		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();
		List<Object> params = new ArrayList<Object>();
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.setLength(0);
		hqlBuilder.append("SELECT aorder ");
		hqlBuilder.append("  FROM TActOrderNew aorder ");
		hqlBuilder.append("  INNER JOIN FETCH aorder.goodsInfos ginfo ");
		hqlBuilder.append("  LEFT JOIN aorder.orderStatus ostatus ");
		hqlBuilder.append(" WHERE aorder.userId = ? ");
		params.add(Long.valueOf(userId));
		hqlBuilder.append("  AND aorder.deleteStatus <> 1 ");
		if(ArrayUtils.isNotEmpty(orderTypes)){
			hqlBuilder.append(" AND (");
			for(int i=0;i<orderTypes.length;i++){
				if(i==0){
					hqlBuilder.append("aorder.orderType = ?");
				}else {
					hqlBuilder.append(" or aorder.orderType = ?");
				}
				params.add(orderTypes[i]);
			}
			
			hqlBuilder.append(")");
		}
		
		if (StringUtils.isNotBlank(name)) {
			if (StringUtils.isNumeric(name)) {
				hqlBuilder.append("  AND aorder.id = ? ");
				params.add(Long.valueOf(name.trim()));
			} else {
				hqlBuilder.append("  AND ginfo.goodsSubject like ? ");
				params.add("%" + name.trim() + "%");
				// 过滤竞拍保证金
				String[] specialGoodIds = specialGoodId.split(",");
				for (String id : specialGoodIds) {
					hqlBuilder.append(" AND ginfo.goodsId <> ? ");
					params.add(Long.valueOf(id));
				}

				// 过滤红包补差价商品id
				if (StringUtils.isNumeric(welfareGoodsId + "")) {
					hqlBuilder.append(" AND ginfo.goodsId <> ? ");
					params.add(Long.valueOf(welfareGoodsId));
				}

			}
		}
		// 时间条件查询
		if (StringUtils.isNotBlank(startTime)) {
			hqlBuilder.append(" AND aorder.createTime >= ? ");
			params.add(startTime + "000000");
		}
		if (StringUtils.isNotBlank(endTime)) {
			hqlBuilder.append(" AND aorder.createTime <= ? ");
			params.add(endTime + "235959");
		}

		if ("0".equals(flag)) {
			if (StringUtils.isNotBlank(status) && StringUtils.equals("1", status)) {
				hqlBuilder.append(" AND aorder.payOnDelivery = ? ");
				params.add(0);
			}
			if (StringUtils.isNotBlank(isdelivery)) {
				hqlBuilder.append(" AND aorder.payOnDelivery = ? ");
				params.add(1);
			}
		}

		// 订单状态查询视图
		if (StringUtils.isNotBlank(status)) {
			if (StringUtils.equals("1", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(1);
			} else if (StringUtils.equals("2", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(2);
			} else if (StringUtils.equals("3", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(3);
			} else if (StringUtils.equals("4", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(4);
			} else if (StringUtils.equals("5", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(5);
			} else if (StringUtils.equals("6", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(6);
			} else if (StringUtils.equals("7", status)) {// 有退款
				hqlBuilder.append(" AND ostatus.hasRefund = ? ");
				params.add(1);
			}
		}
		hqlBuilder.append(" ORDER BY aorder.createTime DESC ");
		Query query = sessionFactory.getCurrentSession().createQuery(hqlBuilder.toString())
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows());
		for (int i = 1; i <= params.size(); i++) {
			query.setParameter(i - 1, params.get(i - 1));
		}
		List<TActOrderNew> orders = query.list();

		for (TActOrderNew order : orders) {
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
						log.error("新我购买的商品查询订单接口获取金额错误" + e.getMessage());
					}
				}
			}
		}
		return orders;
	}

	public String getNewOrdersScript(String userId, String name, PageInfo pageInfo, String startTime, String endTime, String status, String flag,
	        String isdelivery,Long[] orderTypes) {

		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();
		List<Object> params = new ArrayList<Object>();
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.setLength(0);
		hqlBuilder.append("SELECT COUNT(aorder) ");
		hqlBuilder.append("  FROM TActOrderNew aorder ");
		hqlBuilder.append("  JOIN aorder.goodsInfos ginfo ");
		hqlBuilder.append("  LEFT JOIN aorder.orderStatus ostatus ");
		hqlBuilder.append(" WHERE aorder.userId = ? ");
		params.add(Long.valueOf(userId));
		hqlBuilder.append("  AND aorder.deleteStatus <> 1 "); 
		if(ArrayUtils.isNotEmpty(orderTypes)){
			hqlBuilder.append(" AND (");
			for(int i=0;i<orderTypes.length;i++){
				if(i==0){
					hqlBuilder.append("aorder.orderType = ?");
				}else { 
					hqlBuilder.append(" or aorder.orderType = ?");
				}
				params.add(orderTypes[i]);
			}
			
			hqlBuilder.append(")");
		}

		if (StringUtils.isNotBlank(name)) {
			if (StringUtils.isNumeric(name)) {
				hqlBuilder.append("  AND aorder.id = ? ");
				params.add(Long.valueOf(name.trim()));
			} else {
				hqlBuilder.append("  AND ginfo.goodsSubject like ? ");
				params.add("%" + name.trim() + "%");
				// 过滤竞拍保证金
				String[] specialGoodIds = specialGoodId.split(",");
				for (String id : specialGoodIds) {
					hqlBuilder.append(" AND ginfo.goodsId <> ? ");
					params.add(Long.valueOf(id));
				}

				// 过滤红包补差价商品id
				if (StringUtils.isNumeric(welfareGoodsId + "")) {
					hqlBuilder.append(" AND ginfo.goodsId <> ? ");
					params.add(Long.valueOf(welfareGoodsId));
				}

			}
		}
		// 时间条件查询
		if (StringUtils.isNotBlank(startTime)) {
			hqlBuilder.append(" AND aorder.createTime >= ? ");
			params.add(startTime + "000000");
		}
		if (StringUtils.isNotBlank(endTime)) {
			hqlBuilder.append(" AND aorder.createTime <= ? ");
			params.add(endTime + "235959");
		}

		if (StringUtils.isNotBlank(flag) && flag.equals("0")) {
			if (StringUtils.isNotBlank(status) && StringUtils.equals("1", status)) {
				hqlBuilder.append(" AND aorder.payOnDelivery = ? ");
				params.add(0);
			}
			if (StringUtils.isNotBlank(isdelivery)) {
				hqlBuilder.append(" AND aorder.payOnDelivery = ? ");
				params.add(1);
			}
		}

		// 订单状态查询视图
		if (StringUtils.isNotBlank(status)) {
			if (StringUtils.equals("1", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(1);
			} else if (StringUtils.equals("2", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(2);
			} else if (StringUtils.equals("3", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(3);
			} else if (StringUtils.equals("4", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(4);
			} else if (StringUtils.equals("5", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(5);
			} else if (StringUtils.equals("6", status)) {
				hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
				params.add(6);
			} else if (StringUtils.equals("7", status)) {// 有退款
				hqlBuilder.append(" AND ostatus.hasRefund = ? ");
				params.add(1);
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hqlBuilder.toString());
		for (int i = 1; i <= params.size(); i++) {
			query.setParameter(i - 1, params.get(i - 1));
		}
		Integer count = Integer.parseInt(query.list().get(0).toString());
		pageInfo.setRecordCount(count);
		pageInfo.setPageTotals();
		return pageInfo.getMemPageInfo();
	}
	
	
	
	public int countOrdersByStatus(String userId, String status, String startTime, String endTime, String name, String flag, String isdelivery,
	        Long[] orderTypes) {
		int count = 0; // 接受结果参数
		String specialGoodId = appConfig.getSpecialGoodId();
		Long welfareGoodsId = appConfig.getWelfareGoodsId();
		List<Object> params = new ArrayList<Object>();
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.setLength(0);
		hqlBuilder.append("SELECT aorder ");
		hqlBuilder.append("  FROM TActOrderNew aorder ");
		hqlBuilder.append("  INNER JOIN FETCH aorder.goodsInfos ginfo ");
		hqlBuilder.append("  LEFT JOIN aorder.orderStatus ostatus ");
		hqlBuilder.append(" WHERE aorder.userId = ? ");
		params.add(Long.valueOf(userId));
		hqlBuilder.append("  AND aorder.deleteStatus <> 1 ");
		if (ArrayUtils.isNotEmpty(orderTypes)) {
			hqlBuilder.append(" AND (");
			for (int i = 0; i < orderTypes.length; i++) {
				if (i == 0) {
					hqlBuilder.append("aorder.orderType = ?");
				} else {
					hqlBuilder.append(" or aorder.orderType = ?");
				}
				params.add(orderTypes[i]);
			}

			hqlBuilder.append(")");
		}

		if (StringUtils.isNotBlank(name)) {
			if (StringUtils.isNumeric(name)) {
				hqlBuilder.append("  AND aorder.id = ? ");
				params.add(Long.valueOf(name.trim()));
			} else {
				hqlBuilder.append("  AND ginfo.goodsSubject like ? ");
				params.add("%" + name.trim() + "%");
				// 过滤竞拍保证金
				String[] specialGoodIds = specialGoodId.split(",");
				for (String id : specialGoodIds) {
					hqlBuilder.append(" AND ginfo.goodsId <> ? ");
					params.add(Long.valueOf(id));
				}

				// 过滤红包补差价商品id
				if (StringUtils.isNumeric(welfareGoodsId + "")) {
					hqlBuilder.append(" AND ginfo.goodsId <> ? ");
					params.add(Long.valueOf(welfareGoodsId));
				}

			}
		}
		// 时间条件查询
		if (StringUtils.isNotBlank(startTime)) {
			hqlBuilder.append(" AND aorder.createTime >= ? ");
			params.add(startTime + "000000");
		}
		if (StringUtils.isNotBlank(endTime)) {
			hqlBuilder.append(" AND aorder.createTime <= ? ");
			params.add(endTime + "235959");
		}

		if (StringUtils.isNotBlank(flag) && flag.equals("0")) {
			if (StringUtils.isNotBlank(status) && StringUtils.equals("1", status)) {
				hqlBuilder.append(" AND aorder.payOnDelivery = ? ");
				params.add(0);
			}
			if (StringUtils.isNotBlank(isdelivery)) {
				hqlBuilder.append(" AND aorder.payOnDelivery = ? ");
				params.add(1);
			}
		}

		// 订单状态查询视图
		if (StringUtils.isNotBlank(status)) {
			hqlBuilder.append(" AND ostatus.actOrderStatus = ? ");
			if (StringUtils.equals("7", status)) {
				params.add(1);
			} else {
				params.add(Integer.valueOf(status));
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hqlBuilder.toString());
		for (int i = 1; i <= params.size(); i++) {
			query.setParameter(i - 1, params.get(i - 1));
		}
		List<TActOrderNew> orders = query.list();
		if (orders != null) {
			count = orders.size();
		}
		return count;
	}

	/**
	 * 查询出商品的参数属性
	 * 
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findGoodsParam(long goodsId) throws SQLException {
		String sql = "select tip.param_key,tip.param_value,label  from T_ITEM_PARAM tip where item_id= ?";
		return dbHelper.getNativeMapList(sql, goodsId);
	}

	/**
	 * 查询单个商品的详细 属性
	 * 
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findGoodsDetail(long goodsId) throws SQLException {
		String sql = "select t.id,t.img_path  from T_ITEM_SALE t where id = ? ";
		return dbHelper.getNativeMap(sql, goodsId);
	}

	/**
	 * 获取我的抽奖结果
	 * 
	 * @param pageInfo
	 * @param userTel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getLotteryList(PageInfo pageInfo, String userTel) {
		StringBuffer sql = new StringBuffer(500);
		// 关联表 t_lottery_active 活动表 t_lottery_log 抽奖表 t_lottery_prize 奖品表
		sql.append("select la.name,");
		sql.append("       to_date(s.hit_time,'yyyy-mm-dd hh24:mi:ss') ht,");
		sql.append("       s.name sn");
		sql.append("  from ( select ll.active_id,ll.target_id,ll.hit_time,lp.name from t_lottery_log ll  join t_lottery_prize lp on ll.prize_id=lp.id) s,");
		sql.append("       t_lottery_active la");
		sql.append("  where s.target_id=:userTel and s.active_id=la.id");
		sql.append("  order by ht desc");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userTel", Long.valueOf(userTel))
		        .setFirstResult(pageInfo.getPageRows() * (pageInfo.getCurPage() - 1)).setMaxResults(pageInfo.getPageRows()).list();

	}

	/**
	 * 获取我得抽奖结果总页数
	 * 
	 * @param pageInfo
	 * @param userTel
	 * @return
	 */
	public String getLotteryScript(PageInfo pageInfo, String userTel) {
		StringBuffer sql = new StringBuffer(500);
		// 关联表 t_lottery_active 活动表 t_lottery_log 抽奖表 t_lottery_prize 奖品表
		sql.append("select count(la.name)");
		sql.append("  from ( select ll.target_id,ll.hit_time,ll.active_id from t_lottery_log ll  join t_lottery_prize lp on ll.prize_id=lp.id) s,");
		sql.append("  		t_lottery_active la");
		sql.append("  where s.target_id=:userTel and s.active_id=la.id");
		Object rowTotal = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setLong("userTel", Long.valueOf(userTel)).list().get(0);
		pageInfo.setRecordCount(Integer.valueOf(rowTotal.toString()));
		pageInfo.setPageTotals();
		return pageInfo.getScript();
	}
	
	public TUserCenterVisitLog getUserCenterVisitLog(Long userId) {
		String hql="from TUserCenterVisitLog t where t.userId=:userId";
		return (TUserCenterVisitLog)sessionFactory.getCurrentSession().createQuery(hql).setParameter("userId", userId).uniqueResult();
	}
	
	/**
	 * 保存或更新用户中心向导记录
	 * 
	 * @param address
	 */
	public boolean logUserCenterVisit(TUserCenterVisitLog visitLog) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(visitLog);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
