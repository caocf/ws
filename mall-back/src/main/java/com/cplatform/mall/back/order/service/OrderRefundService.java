package com.cplatform.mall.back.order.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.dao.LsDao;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.entity.LsItem;
import com.cplatform.mall.back.item.service.ItemManageService;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.order.dao.ActOrderGoodsInfoDao;
import com.cplatform.mall.back.order.dao.OrderRefundDao;
import com.cplatform.mall.back.order.dao.OrderRefundExceptionDao;
import com.cplatform.mall.back.order.dao.OrderRefundGoodsDao;
import com.cplatform.mall.back.order.entity.CallbackRequest;
import com.cplatform.mall.back.order.entity.CallbackResponse;
import com.cplatform.mall.back.order.entity.CodeInfo;
import com.cplatform.mall.back.order.entity.Order;
import com.cplatform.mall.back.order.entity.OrderRefund;
import com.cplatform.mall.back.order.entity.OrderRefundException;
import com.cplatform.mall.back.order.entity.OrderRefundGoods;
import com.cplatform.mall.back.order.entity.RefundParam;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.mall.back.utils.DoubleUtils;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.order.PaymentSpliteGoodsInfo;
import com.cplatform.order.PaymentSpliteOrderInfo;
import com.cplatform.pay.CommonRequest;
import com.cplatform.pay.PayChannel;
import com.cplatform.pay.PayMode;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.cplatform.pay.PaymentInfo;
import com.cplatform.pay.RequestOperate;
import com.cplatform.pay.unify_pay.info.CartPayApplyPayChannel;
import com.cplatform.pay.unify_pay.info.MerchPayApplyPayChannel;
import com.cplatform.pay.unify_pay.info.ProductionInfo;
import com.cplatform.pay.unify_pay.info.UnifyPayResponse;
import com.cplatform.pay.unify_pay.info.UnifyRefundRequest;
import com.cplatform.pay.unify_pay.info.UnifyRefundType;
import com.cplatform.util2.TimeStamp;
import com.cplatform.verifycode.RevokeReqInfo;
import com.cplatform.verifycode.RevokeRespInfo;
import com.cplatform.verifycode.VerifyService;

/**
 * 订单退款相关Service. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-25 下午02:58:59
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Service
public class OrderRefundService {
	private static Logger log=Logger.getLogger(OrderRefundService.class);
	@Autowired
	private LogUtils logUtils;
	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private OrderRefundDao orderRefundDao;

	@Autowired
	private OrderRefundGoodsDao orderRefundGoodsDao;
	
	@Autowired
	private ActOrderGoodsInfoDao actOrderGoodsInfoDao;

	@Autowired
	private VerifyService verifyClient;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ItemManageService itemManageService;

	@Autowired
	private PayServiceClient payService;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private LsDao lsDao;
	
	@Autowired
	OrderRefundExceptionDao exceptionDao;
	
	

	/**
	 * 保存或更新退款单
	 * @param orderRefund
	 */
	public void save(OrderRefund orderRefund){
		orderRefundDao.save(orderRefund);
	}
	/**
	 * 退款管理查询
	 * 
	 * @param terminalId
	 * @param orderId
	 * @param pageNo
	 * @return
	 * @throws SQLException
	 */
	public Page<OrderRefund> getOrderRefundList(OrderRefund orderRefund, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("  select refund.*,member.TERMINAL_ID  terminalId     ");
		sql.append("    from t_order_refund refund,t_store store,t_act_order ao,t_member member  ");
		sql.append("   where 1 = 1 and refund.store_id = store.id(+)  ");
		sql.append("   and refund.order_id = ao.id(+)  ");
		sql.append("   and ao.user_id = member.id(+)  ");
		List params = new ArrayList();
		if (null != orderRefund) {
			if (null != orderRefund.getId()) {
				sql.append(" and refund.id = ? ");
				params.add(orderRefund.getId());
			}
			if (null != orderRefund.getOrderId()) {
				sql.append(" and refund.order_id = ? ");
				params.add(orderRefund.getOrderId());
			}
			if (null != orderRefund.getRefundType()) {
				sql.append(" and refund.refund_type = ? ");
				params.add(orderRefund.getRefundType());
			}
			if (null != orderRefund.getStatus()) {
				sql.append(" and refund.status = ? ");
				params.add(orderRefund.getStatus());
			}
			if (StringUtils.isNotEmpty(orderRefund.getCreateTimeBegin())) {
				sql.append(" and refund.create_time >= ? ");
				params.add(orderRefund.getCreateTimeBegin()+"000000");
			}

			if (StringUtils.isNotEmpty(orderRefund.getCreateTimeEnd())) {
				sql.append(" and refund.create_time <= ? ");
				params.add(orderRefund.getCreateTimeEnd()+"235959");
			}
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_STORE));
		sql.append("order by refund.create_time desc        ");
		return dbHelper.getPage(sql.toString(), OrderRefund.class, pageNo, Page.DEFAULT_PAGESIZE, params.toArray());
	}

	/**
	 * 退款管理查询
	 * 
	 * @param terminalId
	 * @param orderId
	 * @param pageNo
	 * @return
	 * @throws SQLException
	 */
	public Page<Order> orderRefundQuery(Long payOnDelivery,String giftFlag,Long itemId,String terminalId, Long orderId, String actType, String payStatus,
			String closeStatus,
			String beginTime,String endTime, int pageNo,int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select tm.terminal_id terminalId, od.* from t_member tm ,");
		sql.append(" (select ord.user_id,  ");
		sql.append("         ord.id,                     ");
		sql.append("         ord.create_time,            ");
		sql.append("         ord.act_type,               ");
		sql.append("         ord.close_status,           ");
		sql.append("         ord.pay_status,             ");
		sql.append("         ord.delete_status,             ");
		sql.append("         ord.PAY_ON_DELIVERY,             ");
		sql.append("         ex.status expressStatus             ");
		sql.append("    from t_act_order ord,   ");
		sql.append("         t_act_order_express  ex ");
		sql.append("   where ord.id=ex.act_order_id(+) ) od      ");
		sql.append(" where tm.id(+) = od.user_id ");
		
		List params = new ArrayList();
		if (StringUtils.isNotEmpty(terminalId)) {
			sql.append(" and tm.terminal_id = ? ");
			params.add(terminalId.trim());
		}
		if (null != orderId) {
			sql.append(" and od.id = ? ");
			params.add(orderId);
		}
		if (actType != null && !"".equals(actType)) {
			sql.append(" and od.act_type = ? ");
			params.add(actType.trim());
		}
		if (payOnDelivery != null) {
			sql.append(" and od.PAY_ON_DELIVERY = ? ");
			params.add(payOnDelivery);
		}
		if (payStatus != null && !"".equals(payStatus)) {
			sql.append(" and od.pay_status = ? ");
			params.add(payStatus.trim());
		}
		if(StringUtils.isNotEmpty(closeStatus)){
			sql.append(" and od.close_status = ? ");
			params.add(closeStatus.trim());
		}
		
		if (StringUtils.isNotEmpty(beginTime)) {
			sql.append(" and od.create_Time > ?  ");
			params.add(beginTime+"000000");
		}
		if (StringUtils.isNotEmpty(endTime)) {
			sql.append(" and od.create_Time < ?  ");
			params.add(endTime+"235959");
		}
		if(itemId!=null){
			sql.append(" and od.id in ");
			sql.append(" (select goods.act_order_id from t_act_order_goods goods ");
			sql.append(" join t_item_sale sale ");
			sql.append(" on goods.goods_id=sale.id ");
			sql.append(" where sale.id=? ) ");
			params.add(itemId);
		}
		if(StringUtils.isNotEmpty(giftFlag)){
			sql.append(" and od.pay_status = 2 ");
			sql.append(" and od.id in ");
			sql.append(" (select goods.act_order_id from t_act_order_goods goods ");
			sql.append(" join t_item_sale sale ");
			sql.append(" on goods.goods_id=sale.id ");
			sql.append(" where sale.iseckill = 4 ) ");
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_STORE));
		sql.append(" order by od.create_time desc        ");
		return dbHelper.getPage(sql.toString(), Order.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 查询指定退款单
	 * 
	 * @param id
	 *            退款单ID
	 * @return
	 */
	@Transactional
	public OrderRefund findOne(Long id) {
		OrderRefund orderRefund = orderRefundDao.findOne(id);
		return orderRefund;
	}
	
	
	/**
	 * 根据退款单号查询商户信息
	 * @param actOrderId
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> getStoreById(long id) throws SQLException {
		StringBuilder sql = new StringBuilder(500);
		sql.append(" select s.name name from t_order_refund r  ");
		sql.append(" join t_store s on r.store_id=s.id ");
		sql.append(" where r.id=?");
		Object[] params = new Object[] { id };
		return dbHelper.getMap(sql.toString(), params);
	}

	/**
	 * 根据订单ID查询退款单
	 * 
	 * @param orderId
	 *            订单ID
	 * @return
	 */
	@Transactional
	public List<OrderRefund> findList(Long orderId) {
		List<OrderRefund> orderRefundList = orderRefundDao.findByOrderId(orderId);
		return orderRefundList;
	}

	/**
	 * 根据订单ID查询成功退款单
	 * 
	 * @param orderId
	 *            订单ID
	 * @return
	 */
	@Transactional
	public List<OrderRefund> getSuccessRefund(Long orderId) {
		List<OrderRefund> successOrderRefundList = orderRefundDao.findSuccessByOrderId(orderId);
		return successOrderRefundList;
	}

	/**
	 * 根据退款单ID查询退款商品
	 * 
	 * @param refundId
	 *            退款单ID
	 * @return
	 * @throws SQLException 
	 */
	@Transactional
	public List<OrderRefundGoods> findOrderRefundGoodsList(Long refundId) throws SQLException {
		try{
			List<OrderRefundGoods> orderRefundGoodsList = orderRefundGoodsDao.findByRefundId(refundId);
			return orderRefundGoodsList;
		}catch(Exception e){
			log.error(e.getMessage());
			throw new SQLException(e);
		}
	}
	
	/**
	 * 根据退款单号和商品号查询退款单商品
	 * @param refundId
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public OrderRefundGoods findRefundGoodsByRefundIdAndGoodId(Long refundId,Long goodsId) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from T_ORDER_REFUND_GOODS t ");
		sql.append(" where 1=1  ");
		List<Object> params = new ArrayList<Object>();
		if(refundId!=null){
			sql.append(" and t.REFUND_ID=? ");
			params.add(refundId);
		}
		if(goodsId!=null){
			sql.append(" and t.GOODS_ID = ?");
			params.add(goodsId);
		}
		sql.append(" order by t.id desc ");
		return dbHelper.getBean(sql.toString(), OrderRefundGoods.class, params.toArray());
	}

	/**
	 * 保存退款单
	 * 
	 * @param orderRefund
	 *            订单退款实体类
	 * @return 订单退款实体类
	 */
	@Transactional
	public OrderRefund saveOrderRefund(OrderRefund orderRefund, String[] orderGoodsIds, String[] goodsIds, String[] backNumbers, String method) {
		orderRefund = orderRefundDao.save(orderRefund);
		if ("add".equals(method) && null != backNumbers) {
			// 退款商品
			for (int i = 0; i < backNumbers.length; i++) {
				OrderRefundGoods orderRefundGoods = new OrderRefundGoods();
				// 判断是否退款商品
				if (StringUtils.isEmpty(backNumbers[i])) {
					continue;
				}
				orderRefundGoods.setRefundId(orderRefund.getId());
				orderRefundGoods.setOrderGoodsId(Long.valueOf(orderGoodsIds[i]));
				orderRefundGoods.setGoodsId(Long.valueOf(goodsIds[i]));
				orderRefundGoods.setBackNumber(Long.valueOf(backNumbers[i]));
//				if (StringUtils.isEmpty(cashs[i])) {
//					orderRefundGoods.setCash(0d);
//				} else {
//					orderRefundGoods.setCash(Double.valueOf(cashs[i]) * 100);
//				}
//				if (StringUtils.isEmpty(coins[i])) {
//					orderRefundGoods.setCoin(0d);
//				} else {
//					orderRefundGoods.setCoin(Double.valueOf(coins[i]) * 100);
//				}
				orderRefundGoods = orderRefundGoodsDao.save(orderRefundGoods);
			}
		}
		return orderRefund;
	}

	/**
	 * 查询订单（包含退款单）
	 * @param orderPage
	 * @return
	 * @throws Exception 
	 */
	public Page<Order> getOrderRefund(Page<Order> orderPage) throws Exception {
		if (null != orderPage && 0 < orderPage.getData().size()) {
			for (int i = 0; i < orderPage.getData().size(); i++) {
					Order order=orderPage.getData().get(i);
					//设置订单商品类型
					List<Map<String, String>> actOrderGoodsList = orderService.findOrderGoodsListByOrderId(order.getId());
					if(null!=actOrderGoodsList && actOrderGoodsList.size() > 0){
						if(null!=actOrderGoodsList.get(0).get("ITEMMODE")){
							order.setItemMode(Long.parseLong(actOrderGoodsList.get(0).get("ITEMMODE")));
						}
					}
					//设置订单中商品，从sql语句中取
//					ActOrderInfo actOrderInfo = orderService.getActOrderInfoById(order.getId());
//					order.setGoodsInfos(actOrderInfo.getGoodsInfos());
					List<ItemSale> itemSales=new ArrayList<ItemSale>();
					for(Map<String,String> mp:actOrderGoodsList){
						ItemSale item=new ItemSale();
						item.setId(Long.valueOf(mp.get("goods_id")));
						item.setName(mp.get("goodsName"));
						itemSales.add(item);
					}
					order.setItemSales(itemSales);
					//查询订单下所有退款单
					List<OrderRefund> orderRefunds=orderRefundDao.findRefundByOrderId(orderPage.getData().get(i).getId());
					order.setRefundCount(orderRefunds.size());
					order.setOrderRefunds(orderRefunds);
					//查询订单下成功退款单
//					List<OrderRefund> successOrderRefundList = orderRefundDao.findSuccessByOrderId(orderPage.getData().get(i).getId());
//					order.setSuccessRefundCount(successOrderRefundList.size());
					List<OrderRefund> successOrderRefundList=new ArrayList<OrderRefund>();
					for(OrderRefund refund:orderRefunds){
						if(null!=refund.getStatus() && refund.getStatus()==6){
							successOrderRefundList.add(refund);
						}
					}
					order.setSuccessRefundCount(successOrderRefundList.size());
					//设置订单状态
					if(order.getCloseStatus()==1){
						order.setOrderStatus("已取消");
					}else if(order.getCloseStatus()==0){
						if(order.getPayStatus()==0){
							order.setOrderStatus("等待买家付款");
						}else if(order.getPayStatus()==1){
							order.setOrderStatus("买家正在付款");
						}else if(null==order.getExpressStatus()){
							if(order.getPayStatus()==2){
								order.setOrderStatus("买家已付款");
							}							
						}else if(order.getPayStatus()==2  && order.getExpressStatus()==0){
							order.setOrderStatus("买家已付款");
						}else if(order.getExpressStatus()==1){
							order.setOrderStatus("等待买家收货");
						}else if(order.getPayStatus()==2 && order.getExpressStatus()==2){
							order.setOrderStatus("已完成");
						}
					}
					//设置是否有增加退款单权限
					//已支付和支付中的订单才可以增加退款单
					if(order.getPayStatus()==2 || order.getPayStatus()==1){
						//2次退款成功后不可以增加退款单
						if(order.getSuccessRefundCount()>=2 || order.getRefundCount() >= 5){
							order.setCanRefund(false);
						}else{
							if(null!=orderRefunds && orderRefunds.size()==0){
								//第一次增加退款单
								order.setCanRefund(true);
							}else if(null!=orderRefunds && orderRefunds.size()>0){
								//获取最新退款单状态
								//只有退款单状态为3（商户拒绝确认）,5（审核失败）,6(退款成功),7（退款失败）才可以增加退款单
								OrderRefund refund=orderRefunds.get(orderRefunds.size()-1);
								if(null!=refund.getStatus() && (refund.getStatus()==3 || refund.getStatus()==5 || refund.getStatus()==6 || refund.getStatus()==7)){
									order.setCanRefund(true);
								}else{
									order.setCanRefund(false);
								}
							}
						}
					}else{
						order.setCanRefund(false);
					}
					
				}
			}
//		}
		return orderPage;
	}

	/**
	 * 判断退款单是否需要退码 虚拟商品需要退码
	 * 
	 * @param orderPage
	 * @return
	 * @throws SQLException 
	 */
	public Page<OrderRefund> isRefundCode(Page<OrderRefund> orderRefundPage) throws SQLException {
		if (null != orderRefundPage && 0 < orderRefundPage.getData().size()) {
			for (int i = 0; i < orderRefundPage.getData().size(); i++) {
				OrderRefund refund=orderRefundPage.getData().get(i);
				//设置订单商品类型
				List<Map<String, String>> actOrderGoodsList = orderService.findOrderGoodsListByOrderId(refund.getOrderId());
				if(null!=actOrderGoodsList && actOrderGoodsList.size()>0){
					if(StringUtils.isNotEmpty(actOrderGoodsList.get(0).get("ITEMMODE"))){
						refund.setItemMode(Long.parseLong(actOrderGoodsList.get(0).get("ITEMMODE")));
					}
				}
//				List<OrderRefundGoods> orderRefundGoodsList =findOrderRefundGoodsList(refund.getId());
//				//初始化退款单退码标识,0表示无法退码
//				refund.setRefundVerifyCode("0");
//				if (null!=orderRefundGoodsList && 0 < orderRefundGoodsList.size()) {
//					for(OrderRefundGoods orderRefundGoods:orderRefundGoodsList){
//						ItemSale itemSale =itemManageService.findOneItemSale(orderRefundGoods.getGoodsId());
//						// 商品类型0--实物 1--虚拟物
//						//如果订单商品中有虚拟商品，那么退款单就可以退码
//						if (1L == itemSale.getItemMode()) {
//							refund.setRefundVerifyCode("1");
//						} 
//					}
//				}
			}
		}
		return orderRefundPage;
	}

	/**
	 * 设置添加退款单时的基本信息
	 * 
	 * @param orderId
	 *            订单号
	 * @return
	 */
	public OrderRefund setOrderRefundBaseInfo(Long orderId) {
		Map<String, String> order = orderService.findOneOrderById(orderId);
		OrderRefund orderRefund = new OrderRefund();
		orderRefund.setOrderId(orderId);
		orderRefund.setStoreId(Long.valueOf(order.get("shop_id")));
		return orderRefund;
	}

	/**
	 * 查询退款单商品，以及商品现金总额和商城币总额
	 * @param model
	 * @param paymengList	支付订单集合
	 * @param orderId	订单id
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Long> setActOrderGoodsList(Model model,List<Map<String, String>> paymengList,
			Long orderId) throws Exception {
		Map<String,Long> mp=new HashMap<String, Long>();
		//已退金额，从接口中取
		Double refundCash=0.0;
		Double refundCoin=0.0;
		Double refundScore=0.0;
		Double refundPhone=0.0;
		//现金总额
		Double totalCash=0.0;
		//商城币总额
		Double totalCoin=0.0;
		//积分总额
		Double totalScore=0.0;
		//话费总额
		Double totalPhone=0.0;
		//退款单类型,1平台自己,2拉手
		Long refundType=1L;
		
		List<PayOrderInfo> payOrderInfos=payService.getPayOrderInfosByActOrderId(orderId);
		if(null!=payOrderInfos && payOrderInfos.size()>0){
			for(PayOrderInfo tmp:payOrderInfos){
				//获取订单支付金额
				if(tmp.getOperate()==RequestOperate.Pay && tmp.getStatus()==PayOrderInfo.STATUS_SUCCESS){
					List<PaymentInfo> payments=tmp.getPayments();
					for(PaymentInfo paymentInfo:payments){
						if(paymentInfo.getCurrency().equals("balance")){
							totalPhone+=(double)tmp.getPaymentAmount()/100;
						}
						if(paymentInfo.getCurrency().equals("cash")){
							totalCash+=(double)tmp.getPaymentAmount()/100;
						}
						if(paymentInfo.getCurrency().equals("coin")){
							totalCoin+=(double)tmp.getPaymentAmount()/100;
						}
						if(paymentInfo.getCurrency().equals("score") ){
							totalScore+=(double)tmp.getPaymentAmount()/100;
						}
					}
				}
				//获取订单退款金额
				if(tmp.getOperate()==RequestOperate.Refund && tmp.getStatus()==PayOrderInfo.STATUS_SUCCESS){
					List<PaymentInfo> payments=tmp.getPayments();
					for(PaymentInfo paymentInfo:payments){
						if(paymentInfo.getCurrency().equals("balance")){
							refundPhone+=-(double)tmp.getPaymentAmount()/100;
						}
						if(paymentInfo.getCurrency().equals("cash")){
							refundCash+=-(double)tmp.getPaymentAmount()/100;
						}
						if(paymentInfo.getCurrency().equals("coin")){
							refundCoin+=-(double)tmp.getPaymentAmount()/100;
						}
						if(paymentInfo.getCurrency().equals("score")){
							refundScore+=-(double)tmp.getPaymentAmount()/100;
						}
					}
				}
			}
		}
		//已退金额
		model.addAttribute("oldRefundAmount", 
				new BigDecimal(refundCash.toString()).
				add(new BigDecimal(refundCoin.toString())).
				add(new BigDecimal(refundScore.toString())).
				add(new BigDecimal(refundPhone.toString())).doubleValue()
				);
		//物流费用
		Double expressCost=0.0;
		//订单价格
		Double orderSum=0.0;
		//商品类型
		Long itemMode=null;
		//查询订单下商品
		List<Map<String, String>> actOrderGoodsList = orderService.findOrderGoodsListByOrderId(orderId);
		if(null!=actOrderGoodsList && actOrderGoodsList.size()>0){
			//判断订单中商品类型，备注：订单中只有一种类型的商品
			itemMode=Long.parseLong(actOrderGoodsList.get(0).get("itemMode"));
			Long itemId=Long.parseLong(actOrderGoodsList.get(0).get("id"));
			model.addAttribute("itemMode", itemMode);
			//遍历商品，算出每个商品现金和商城币值 
    		for(int i=0;i<actOrderGoodsList.size();i++){
				if(null!=paymengList && paymengList.size()>0){
					for(int j=0;j<paymengList.size();j++){
						//查询支付表中"SPLITE_INFO"，算出每个商品支付情况
						String jsonString = paymengList.get(j).get("SPLITE_INFO").toString();
						if(StringUtils.isNotEmpty(jsonString)){
							jsonString = StringEscapeUtils.unescapeJavaScript(jsonString);
					        JSONObject jsonObject = JSONObject.fromObject(jsonString);
					        //转换规则
					        JsonConfig jsonConfig = new JsonConfig();
					        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
					        classMap.put("spliteGoodsInfos", PaymentSpliteGoodsInfo.class);
					        jsonConfig.setClassMap(classMap);
					        jsonConfig.setRootClass(PaymentSpliteOrderInfo.class);
					        // 转换
					        PaymentSpliteOrderInfo splitOrderInfo = (PaymentSpliteOrderInfo) JSONObject.toBean(jsonObject, jsonConfig);
					        //只能取一次订单价格和物流费用
					        if(i==0){
					        	//订单价格
						        orderSum+=(double)splitOrderInfo.getSumValue()/100;
						        //物流费用
						        expressCost+=(double) splitOrderInfo.getExpress()/100;
					        }
						}
				        
					}
				}else{
					log.info("获取订单支付信息失败,订单id="+orderId);
				}
				//订单中虚拟商品只有一种
				if(1L==itemMode){
					//设置各币种最大退款金额
					if(i==0){
						model.addAttribute("maxCash", DoubleUtils.doubleMinus(totalCash, refundCash));
						model.addAttribute("maxCoin", DoubleUtils.doubleMinus(totalCoin, refundCoin));
						model.addAttribute("maxPhone", DoubleUtils.doubleMinus(totalPhone, refundPhone));
						model.addAttribute("maxScore", DoubleUtils.doubleMinus(totalScore, refundScore));
					}
					//虚拟商品名称
					model.addAttribute("goodsName", actOrderGoodsList.get(0).get("goodsName"));
					//订单商品id
					model.addAttribute("itemId", itemId);
					//订单商品对应商品id
					model.addAttribute("goodsId", actOrderGoodsList.get(0).get("goods_id"));
					//虚拟商品数量
					model.addAttribute("count", Long.parseLong(actOrderGoodsList.get(0).get("count")));
					//订单商户
					String storeId=actOrderGoodsList.get(0).get("store_id");
					if(StringUtils.isNotEmpty(storeId)){
						Store store=storeService.findStoreById(Long.valueOf(storeId));
						model.addAttribute("store", store);
					}
					//查询商品码
					CodeInfo codeInfo=new CodeInfo();
					codeInfo.setActOrderId(orderId);
//					codeInfo.setItemOrderId(itemId);
					List<CodeInfo> codes=codeInfoService.listStoreCodes(codeInfo);
					//获取拉手商品是否支持退款
					//0不支持，1支持,null表示不是拉手商品
					String refundStrategy=actOrderGoodsList.get(0).get("refund_strategy");
					if(StringUtils.isNotEmpty(refundStrategy)){
						//设置退款单类型
						refundType=2L;
						//支持退款的商品显示商品码
						if(refundStrategy.equals("1")){
							int codeNum=0;
							//遍历码，算出可退码总数
							for(CodeInfo code:codes){
								if(code.getCodeStatus()==0L){
									codeNum++;
								}
							}
							model.addAttribute("codes", codes);
							model.addAttribute("codeNum", codeNum);
						}
					}else{
						model.addAttribute("codes", codes);
					}
				}
				//实物商品
				if(itemMode==0L){
					String orderGoodsId = actOrderGoodsList.get(i).get("ID");
					String goodId = actOrderGoodsList.get(i).get("GOODS_ID");
					List<OrderRefundGoods> orderRefundGoodses = getOrderRefundGoodses(Long.parseLong(orderGoodsId), Long.parseLong(goodId));
					List<Long> refundIds = getRefundId(orderRefundGoodses);
					if(refundIds.size() > 0){
						Long backNumber=0L;
						for(int k=0;k<refundIds.size();k++){
							backNumber += Long.parseLong(this.getBackNumber(refundIds.get(k)));
						}
						actOrderGoodsList.get(i).put("backNumber", backNumber+"");
					}else{
						actOrderGoodsList.get(i).put("backNumber", "0");
					}
				}
    		}
    	}
		//订单价格
		model.addAttribute("orderSum", orderSum);
		model.addAttribute("refundType", refundType);
		if(null!=itemMode && itemMode==0L){
			model.addAttribute("totalCash", DoubleUtils.doubleMinus(totalCash, refundCash));
			model.addAttribute("totalCoin", DoubleUtils.doubleMinus(totalCoin, refundCoin));
			model.addAttribute("totalScore", DoubleUtils.doubleMinus(totalScore, refundScore));
			model.addAttribute("totalPhone", DoubleUtils.doubleMinus(totalPhone, refundPhone));
			//物流费用
			model.addAttribute("expressCost", expressCost);
			model.addAttribute("actOrderGoodsList", actOrderGoodsList);
		}
		mp.put("itemMode", itemMode);
		mp.put("refundType", refundType);
		return mp;
	}

	
	/**
	 * 查询订单下码状态(拉手退款用)
	 * @param codeInfo
	 * @return	true可以审核,false不可以审核
	 * @throws SQLException
	 */
	public boolean judgeCodeStatus(CodeInfo codeInfo) throws SQLException{
		boolean flag=false;
		//正常码数量
		int i=0;
		//已作废码数量
		int j=0;
		List<CodeInfo> codes=codeInfoService.listStoreCodes(codeInfo);
		for(CodeInfo code:codes){
			if(code.getCodeStatus()==0L){
				i++;
			}
			if(code.getCodeStatus()==1L){
				j++;
			}
		}
		//订单中无正常码并且至少一个已作废的码，退款单就可以审核和退款了
		if(i==0 && j>0){
			flag=true;
		}
		return flag;
	}
	/**
	 * 查询是否存在未成功支付的币种
	 * @param orderRefund
	 * @param cashLimit	现金最大退款金额
	 * @param coinLimit	商城币最大退款金额
	 * @return	true:存在，false:不存在
	 * @throws Exception
	 */
	public boolean findOtherPayCurrency(OrderRefund orderRefund,Double cashLimit,Double coinLimit) throws Exception{
		boolean flag=false;
		List<String> list=new ArrayList<String>();
		if(cashLimit!=null && cashLimit==0.0){
			list.add("cash");
		}
		if(coinLimit!=null && coinLimit==0.0){
			list.add("coin");
		}
		if(list.size()>0){
			List<PayOrderInfo> payOrderInfos=payService.getPayOrderInfosByActOrderId(orderRefund.getOrderId());
			if(null!=payOrderInfos && payOrderInfos.size()>0){
				for(String currency:list){
					for(PayOrderInfo tmp:payOrderInfos){
						if(tmp.getOperate()==RequestOperate.Pay && tmp.getStatus()!=PayOrderInfo.STATUS_SUCCESS){
							List<PaymentInfo> payments=tmp.getPayments();
							for(PaymentInfo paymentInfo:payments){
								if(paymentInfo.getCurrency().equals(currency)){
									flag=true;
									log.info("未成功支付的币种:"+currency);
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
	 * 设置订单原价（含物流）
	 * 
	 * @param orderSum
	 *            订单原价
	 * @param paymengList
	 *            订单支付信息
	 * @return
	 */
	public Double setOrderAmount(Double orderSum, List<Map<String, String>> paymengList) {
		if (0 < paymengList.size()) {
			for (int i = 0; i < paymengList.size(); i++) {
				orderSum += Double.valueOf(paymengList.get(i).get("amount")) / 100;
			}
		}
		return orderSum;
	}

	/**
	 * 设置订单物流
	 * 
	 * @param expressCost
	 *            订单物流
	 * @param orderId
	 *            订单号
	 * @return
	 */
	public Double setExpressCost(Double expressCost, Long orderId) {
		Map<String, String> orderExpress = orderService.findOneOrderExpressByOrderId(orderId);
		if (null == orderExpress) {
			expressCost = 0d;
		} else {
			expressCost = Double.valueOf(orderExpress.get("express_cost")) / 100;
		}
		return expressCost;
	}

	/**
	 * 获得该订单已成功退款金额
	 * 
	 * @param oldRefundAmount
	 *            已成功退款金额
	 * @param orderId
	 *            订单号
	 * @return
	 */
	public Double setOldRefundAmount(Double oldRefundAmount, Long orderId) {
		List<OrderRefund> successOrderRefundList = getSuccessRefund(orderId);
		if (0 < successOrderRefundList.size()) {
			for (int i = 0; i < successOrderRefundList.size(); i++) {
				oldRefundAmount += successOrderRefundList.get(i).getRefundFee() / 100;
			}
		}
		return oldRefundAmount;
	}

	/**
	 * 判断商品为实物类还是虚拟类 实物类：需要商户确认 虚拟类：不需要商户进行确认
	 * 
	 * @param orderRefund
	 *            退款单实体类
	 * @return
	 */
	public OrderRefund setStatus(OrderRefund orderRefund) {
		List<Map<String, String>> actOrderGoodsList = orderService.findOrderGoodsListByOrderId(orderRefund.getOrderId());
		// 商品类型0--实物 1--虚拟物
		if (null != actOrderGoodsList && 0 < actOrderGoodsList.size()) {
			for (int i = 0; i < actOrderGoodsList.size(); i++) {
				ItemSale itemSale = itemManageService.findOneItemSale(Long.valueOf(actOrderGoodsList.get(i).get("goods_id")));
				if (null != itemSale) {
					if (0L == itemSale.getItemMode()) {
						orderRefund.setStatus(OrderRefund.STATUS_1);
						break;
					} else if (1L == itemSale.getItemMode()) {
						orderRefund.setStatus(OrderRefund.STATUS_2);
						break;
					} else {

					}
				}
			}
		}
		return orderRefund;
	}

	/**
	 * 四舍五入
	 * 
	 * @param num
	 * @param n
	 * @return
	 */
	public double getDecimal(double num, int n) {
		if (Double.isNaN(num)) {
			return 0;
		}
		BigDecimal bd = new BigDecimal(num);
		num = bd.setScale(n, BigDecimal.ROUND_HALF_UP).doubleValue();
		return num;
	}

	/**
	 * 虚拟商品退码
	 * 
	 * @param verifycode
	 * @return
	 * @throws Exception
	 */
	public RevokeRespInfo refundVerifyCode(CodeInfo codeInfo) {
		RevokeReqInfo request = new RevokeReqInfo();
		request.setCode(codeInfo.getOrderId());//码编号
		request.setCodeType(RevokeReqInfo.CodeType.OrderNo);
//		request.setfExtReqInfo(null);
		request.setPlatform_id(codeInfo.getPlatformId());//码平台
//		request.setStoreId(verifycode.get("store_id"));
		RevokeRespInfo response=null;
		try {
			response = verifyClient.revoke(request);
		} catch (Exception e) {
			log.error("调用退码接口异常:"+e.getMessage());
			logUtils.logAdd("退码接口异常", e.getMessage());
		}
		log.info("调用退码接口返回信息："+response.toString());
		return response;
	}
	
	public RevokeRespInfo refundVerifyCodeByItemOrderId(CodeInfo codeInfo) {
		RevokeReqInfo request = new RevokeReqInfo();
		request.setCode(codeInfo.getItemOrderId()+"");//商品订单号
		request.setCodeType(RevokeReqInfo.CodeType.OrderNo);
		request.setPlatform_id(codeInfo.getPlatformId());//码平台
		RevokeRespInfo response=null;
		try {
			response = verifyClient.revoke(request);
		} catch (Exception e) {
			log.error("调用退码接口异常:"+e.getMessage());
			logUtils.logAdd("退码接口异常", e.getMessage());
		}
		log.info("调用退码接口返回信息："+response.toString());
		return response;
	}
	
	
	/**
	 * 拉手退款单退码
	 * @param actOrderId	支付订单编号
	 * @throws Exception
	 */
	public void refundVerifyCodeLS(Long actOrderId) throws Exception {
		List<CodeInfo> codeInfos=codeInfoService.listCodesByActOrderId(actOrderId);
		log.info("商品订单数量:"+codeInfos.size());
		for(CodeInfo code:codeInfos){
			this.refundVerifyCodeByItemOrderId(code);
		}
//		ActOrderInfo order = orderService.getActOrderInfoById(actOrderId);
//		List<ActOrderGoodsInfo> actOrderGoodsInfos=order.getGoodsInfos();
//		for(ActOrderGoodsInfo actOrderGoodsInfo:actOrderGoodsInfos){
//			Long itemOrderId=actOrderGoodsInfo.getId();
//			CodeInfo codeInfo=new CodeInfo();
//			codeInfo.setActOrderId(actOrderId);
//			codeInfo.setItemOrderId(itemOrderId);
//			List<CodeInfo> codeInfos=codeInfoService.listStoreCodes(codeInfo);
//			if(null!=codeInfos && codeInfos.size()>0){
//				codeInfo=codeInfos.get(0);
//				this.refundVerifyCodeByItemOrderId(codeInfo);
//			}
//		}
	}
	
	
	/**
	 * 拉手退款通知
	 * @param orderRefund
	 * @throws Exception
	 */
	public void refundVerifyCodeLSResult(OrderRefund orderRefund) throws Exception {
		List<CodeInfo> codeInfos=codeInfoService.listCodesByActOrderId(orderRefund.getOrderId());
		log.info("商品订单数量:"+codeInfos.size());
		for(CodeInfo code:codeInfos){
			this.refundOpResult(code, orderRefund);
		}
		
//		ActOrderInfo order = orderService.getActOrderInfoById(orderRefund.getOrderId());
//		List<ActOrderGoodsInfo> actOrderGoodsInfos=order.getGoodsInfos();
//		for(ActOrderGoodsInfo actOrderGoodsInfo:actOrderGoodsInfos){
//			Long itemOrderId=actOrderGoodsInfo.getId();
//			CodeInfo codeInfo=new CodeInfo();
//			codeInfo.setActOrderId(orderRefund.getOrderId());
//			codeInfo.setItemOrderId(itemOrderId);
//			List<CodeInfo> codeInfos=codeInfoService.listStoreCodes(codeInfo);
//			if(null!=codeInfos && codeInfos.size()>0){
//				codeInfo=codeInfos.get(0);
//				this.refundOpResult(codeInfo, orderRefund);
//			}
//		}
	}
	/**
	 * 拉手退款通知
	 * @param codeInfo
	 * @param orderRefund
	 * @return
	 * @throws Exception 
	 */
	public RevokeRespInfo refundOpResult(CodeInfo codeInfo,OrderRefund orderRefund) throws Exception {
		RevokeReqInfo request = new RevokeReqInfo();
		request.setCode(codeInfo.getItemOrderId()+"");//商品订单编号
		request.setCodeType(RevokeReqInfo.CodeType.RefundOP);
		LsItem lsItem=null;
		//查询订单中商品
		ActOrderInfo order = orderService.getActOrderInfoById(orderRefund.getOrderId());
		List<ActOrderGoodsInfo> actOrderGoodsInfos=order.getGoodsInfos();
		if(null!=actOrderGoodsInfos && actOrderGoodsInfos.size()>0){
			ActOrderGoodsInfo actOrderGoodsInfo=actOrderGoodsInfos.get(0);
			lsItem=lsDao.findLsItemByItemId(actOrderGoodsInfo.getGoodsId());
		}
		HashMap<String, String> fExtReqInfo=new HashMap<String, String>();
		String money=Math.round(orderRefund.getRefundFee())+"";
		fExtReqInfo.put("money", money);
		fExtReqInfo.put("lashou_goods_Id", lsItem.getVenderItemId());
		request.setfExtReqInfo(fExtReqInfo);
		request.setPlatform_id(codeInfo.getPlatformId());//码平台
		RevokeRespInfo response=null;
		try {
			response = verifyClient.revoke(request);
		} catch (Exception e) {
			log.error("调用拉手退款通知接口异常:"+e.getMessage());
			logUtils.logAdd("拉手退款通知接口异常", e.getMessage());
		}
		log.info("调用拉手退款通知接口返回信息："+response.toString());
		return response;
	}

	/**
	 * 根据会员Id查询下单用户信息
	 * 
	 * @param userId
	 *            会员ID
	 * @return
	 */
	public Map<String, String> findMemberByUserId(long userId) {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.* from t_member t ");
		sqlBuff.append(" where t.id = ? ");

		try {
			return dbHelper.getMap(sqlBuff.toString(), new Object[] { userId });
		}
		catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 查询指定商品码
	 * 
	 * @param orderId
	 *            商品码ID
	 * @return
	 */
	public Map<String, String> findVerifyCodeByOrderId(String orderId) {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.* from t_verify_code_info t");
		sqlBuff.append(" where t.order_id = ?");
		sqlBuff.append(" order by t.item_order_id desc");
		try {
			return dbHelper.getMap(sqlBuff.toString(), new Object[] { orderId });
		}
		catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 退款接口，创建通用退款请求对象CommonRequest
	 * @param request
	 * @param actOrderInfo
	 * @param paymentInfo
	 * @return
	 * @throws Exception
	 */
	private CommonRequest createCommonRequest(PayOrderInfo payOrderInfo,RefundParam refundParam,OrderRefund orderRefund, ActOrderInfo actOrderInfo, ActOrderPaymentInfo paymentInfo) throws Exception {
		//退款金额为0，不发起请求
		//@add_by macl@c-platform.com
//		if(0d>=(Double) refundParam.getAmount()){
//			return null;
//		}
		
//		String payWay = request.getPayChannel();
//		String payChannel = "pay." + payWay + ".channel";

		long userId = actOrderInfo.getUserId();
		long shopId = actOrderInfo.getShopId();
		long actOrderId = actOrderInfo.getId();

		CommonRequest commonRequest = new CommonRequest();
		// 填写退款请求中通用部分
		{
			commonRequest.setActOrderId(actOrderId);
			commonRequest.setOperate(RequestOperate.Refund);
			commonRequest.setPayChannel(payOrderInfo.getPayChannel());
			commonRequest.setPaymentAmount((int) refundParam.getAmount());
			//设置支付信息modify by chen, start>>>
			List<PaymentInfo> payments = new ArrayList<PaymentInfo>();
			PaymentInfo info = new PaymentInfo();
			info.setCurrency(paymentInfo.getCurrency());
			info.setAmount((int) refundParam.getAmount());
			payments.add(info);
			commonRequest.setPayments(payments);
			//<<< end
//			commonRequest.addPaymentInfo(info);
			
			commonRequest.setPayMode(PayMode.Background);
			commonRequest.setRemark(orderRefund.getReason());
			commonRequest.setShopId(shopId);
			commonRequest.setUserId(userId);
			commonRequest.setPayOrderId(payOrderInfo.getPayOrderId());
//			List<PayOrderInfo> orderList = payService.getPayOrderInfosByActOrderId(actOrderId);
//			if(null == orderList ||
//					orderList.size()<=0){
//				throw new Exception("支付未完成，不能退款！");
//			}
			
			/**
			 * @modify_by macl@c-platform.com start>>>
			 */
			//commonRequest.setPayOrderId(orderList.get(0).getPayOrderId());
//			boolean _break = false;
//			for(PayOrderInfo orderInfo : orderList){
//				if(orderInfo.getStatus() != PayOrderInfo.STATUS_SUCCESS){
//					continue;
//				}
//				
//				List<PaymentInfo> paymentInfoList = orderInfo.getPayments();
//				
//				for(PaymentInfo pinfo :paymentInfoList){
//					if(info.getCurrency().equals(pinfo.getCurrency())){
//						commonRequest.setPayOrderId(orderInfo.getPayOrderId());
//						
//						commonRequest.setPayChannel(orderInfo.getPayChannel());
//						_break =true;
//						break;
//					}
//				}
//				if(_break){
//					break;
//				}
//			}
			/**
			 * @modify_by macl@c-platform.com <<<end
			 */
			
			commonRequest.setTime(TimeStamp.getTime(14));
		}
		//
		return commonRequest;
	}

	/**
	 * 退款方法.
	 * @param refundParam
	 * @param payOrderInfo
	 * @param orderRefund
	 * @param actOrderInfo
	 * @param paymentInfo
	 * @param paymentSpliteOrderInfo
	 * @return
	 * @throws Exception
	 */
	public UnifyPayResponse refundSubmitUnify(RefundParam refundParam,PayOrderInfo payOrderInfo,OrderRefund orderRefund,ActOrderInfo actOrderInfo, 
			ActOrderPaymentInfo paymentInfo,PaymentSpliteOrderInfo paymentSpliteOrderInfo) throws Exception {
		//1、创建通用请求对象
		CommonRequest commonRequest = createCommonRequest(payOrderInfo,refundParam,orderRefund, actOrderInfo, paymentInfo);
		log.info("退款物流费用:"+paymentSpliteOrderInfo.getExpress());
		log.info("CommonRequest数据："+commonRequest.toString());
		if (StringUtils.isBlank(commonRequest.getPayChannel())) {
			String text = "没有配置对应的支付渠道";
			UnifyPayResponse response = new UnifyPayResponse();
			response.setStatusCode(-1);
			response.setStatusText(text);
			return response;
		}
		// 2.创建统一退款请求
		UnifyRefundRequest unifyRefundRequest = new UnifyRefundRequest();
		//设置物流费
		unifyRefundRequest.setLogisticsFee(paymentSpliteOrderInfo.getExpress()+"");
		//设置退款方式和渠道
		setRefundPayChannelAndUnifyRefundType(unifyRefundRequest,payOrderInfo);
		//填写商品信息
		setRefundProductionInfo(orderRefund,unifyRefundRequest,actOrderInfo,paymentSpliteOrderInfo);
		log.info("unifyRefundRequest:"+unifyRefundRequest.toString());
		//调用退款接口
		String responseText = null;
		try{
			responseText = payService.submit(commonRequest, unifyRefundRequest);
		}catch(Exception e){
			logUtils.logAdd("退款接口异常", e.getMessage());
			log.error("调用退款接口submit异常："+responseText);
			UnifyPayResponse payResponse = new UnifyPayResponse();
			payResponse.setStatusCode(-1);
			payResponse.setStatusText("接口异常，请联系管理员");
			payResponse.setPayOrderRecordId(0L);
			return  payResponse;
		}
		log.info("调用退款接口submit："+responseText);
		UnifyPayResponse payResponse = PayServiceClient.jsonToBean(responseText, UnifyPayResponse.class);
		log.info("调用退款接口UnifyPayResponse："+payResponse.toString());
		if(null!=payResponse.getPayOrderRecordId()){
			if(refundParam.getCurrency().equals("balance")){
				refundParam.setCurrency("phone");
			}
			saveResponseRecordId( payResponse.getPayOrderRecordId(),orderRefund.getId(),refundParam.getCurrency());
		}
		return payResponse;
	}

	/**设置退款接口支付属性
	 * @param unifyRefundRequest
	 * @param payOrderInfo
	 */
	private void setRefundPayChannelAndUnifyRefundType(
			UnifyRefundRequest unifyRefundRequest, PayOrderInfo payOrderInfo) {
		String payChannel = payOrderInfo.getPayChannel();
		if (PayChannel.UNIFY_CART_APPLY_ALIPAY.equals(payChannel)) {
			unifyRefundRequest.setUnifyRefundType(UnifyRefundType.CART_PAY_APPLY);
			unifyRefundRequest.setPayChannel(CartPayApplyPayChannel.ALIPAY);
		} else if (PayChannel.UNIFY_CART_APPLY_CMPAY.equals(payChannel)) {
			unifyRefundRequest.setUnifyRefundType(UnifyRefundType.CART_PAY_APPLY);
			unifyRefundRequest.setPayChannel(CartPayApplyPayChannel.CMPAY);
		} else if (PayChannel.UNIFY_CART_APPLY_SCORE.equals(payChannel)) {
			unifyRefundRequest.setUnifyRefundType(UnifyRefundType.CART_PAY_APPLY);
			unifyRefundRequest.setPayChannel(CartPayApplyPayChannel.SCORE);
		} else if (PayChannel.UNIFY_CART_COIN.equals(payChannel)) {
			unifyRefundRequest.setUnifyRefundType(UnifyRefundType.CART_PAY_COIN);
			unifyRefundRequest.setPayChannel(CartPayApplyPayChannel.COIN);
		} else if (PayChannel.UNIFY_CART_SCORE.equals(payChannel)) {
			unifyRefundRequest.setUnifyRefundType(UnifyRefundType.CART_PAY_SCORE);
			unifyRefundRequest.setPayChannel(CartPayApplyPayChannel.SCORE);
		} else if (PayChannel.UNIFY_WAP_ALIPAY.equals(payChannel)) {
			unifyRefundRequest.setUnifyRefundType(UnifyRefundType.MERCH_PAY_APPLY);
			unifyRefundRequest.setPayChannel(MerchPayApplyPayChannel.ALIPAY_WAP);
		}else if (PayChannel.UNIFY_BALANCE.equals(payChannel)) {
			unifyRefundRequest.setUnifyRefundType(UnifyRefundType.BALANCE_PAY);
		}
		
	}
	/**设置退款接口商品信息属性
	 * @param orderRefund 
	 * @param unifyRefundRequest 
	 * @param store
	 * @param actOrderInfo
	 * @param paymentSpliteOrderInfo
	 * @return
	 * @throws SQLException 
	 */
	private void setRefundProductionInfo(OrderRefund orderRefund, UnifyRefundRequest unifyRefundRequest, 
			ActOrderInfo actOrderInfo,PaymentSpliteOrderInfo paymentSpliteOrderInfo) throws SQLException {
		Store store = storeService.findStoreById(orderRefund.getStoreId());
		List<ProductionInfo> productionInfoList = new ArrayList<ProductionInfo>();
		for (ActOrderGoodsInfo goodsInfo : actOrderInfo.getGoodsInfos()) {
			PaymentSpliteGoodsInfo spliteInfo = paymentSpliteOrderInfo.getSpliteGoodsInfo(goodsInfo.getId());
			if(null!=spliteInfo){
				ProductionInfo productionInfo = new ProductionInfo();
				ItemSale itemSale = itemManageService.findOneItemSale(goodsInfo.getGoodsId());
				//modify by chen 判断商品结算价是否存在 start>>>
//				if(null==itemSale.getSettlePrice()){
//					return null;
//				}
				//<<<end
//				int settlePrice = (int) (itemSale.getSettlePrice() * 100);
				int settlePrice=0;
				productionInfo.setAssignedCity("025");
				productionInfo.setBusinesId(String.valueOf(actOrderInfo.getShopId()));
				productionInfo.setDiscount(String.valueOf(spliteInfo.getDiscount()));
				productionInfo.setMerchId(/*refundRequest.getMerchId()*/store.getMerchid());
				productionInfo.setPrice(String.valueOf(spliteInfo.getPrice()));
				productionInfo.setProductionId(String.valueOf(goodsInfo.getGoodsId()));
				productionInfo.setProductionType(/*refundRequest.getProductionType()*/itemSale.getFeeType().toString());
				//查询退款单商品数量
				OrderRefundGoods refundGoods=this.findRefundGoodsByRefundIdAndGoodId(orderRefund.getId(),goodsInfo.getGoodsId() );
				if(null!=refundGoods){
					productionInfo.setQuantity(String.valueOf(refundGoods.getBackNumber()));
				}else{
					productionInfo.setQuantity(0+"");
				}
				productionInfo.setSettlementPrice(/*refundRequest.getSettlementPrice()*/String.valueOf(settlePrice));
				productionInfo.setTitle(/*refundRequest.getTitle()*/itemSale.getName());
				productionInfo.setProductionDesc(goodsInfo.getGoodsDescription());
				productionInfoList.add(productionInfo);
			}
		}
		unifyRefundRequest.setProductionInfos(productionInfoList.toArray(new ProductionInfo[0]));
		//
		Map<String, String> member = findMemberByUserId(actOrderInfo.getUserId());
		unifyRefundRequest.setMobile(member.get("terminal_id"));
		unifyRefundRequest.setRefundReason(orderRefund.getReason());
	}
	
	@Deprecated
	private void splitMoney(//
	        CommonRequest commonRequest, //
	        UnifyRefundRequest unifyRefundRequest, //
	        ActOrderInfo actOrderInfo, //
	        ActOrderPaymentInfo paymentInfo) throws Exception {
		// 计算分割比例
		List<Integer> scales = new ArrayList<Integer>();
		List<ActOrderPaymentInfo> paymentInfos = actOrderInfo.getPaymentInfos();
		Collections.sort(paymentInfos, new Comparator<ActOrderPaymentInfo>() {

			@Override
			public int compare(ActOrderPaymentInfo o1, ActOrderPaymentInfo o2) {
				Long v1 = o1.getId();
				Long v2 = o2.getId();
				return v1.compareTo(v2);
			}
		});
		int index = 0;
		for (int i = 0; i < paymentInfos.size(); i++) {
			ActOrderPaymentInfo actOrderPaymentInfo = paymentInfos.get(i);
			int amount = actOrderPaymentInfo.getAmount();
			scales.add(amount);
			if (actOrderPaymentInfo.getId() == paymentInfo.getId()) {
				index = i;
			}
		}
		// 开始分割金额
		String vStr;
		for (ProductionInfo productionInfo : unifyRefundRequest.getProductionInfos()) {
			// 商品单价
			vStr = productionInfo.getPrice();
			vStr = splitMoney(vStr, scales, index);
			productionInfo.setPrice(vStr);
			// 商品折扣
			vStr = productionInfo.getDiscount();
			vStr = splitMoney(vStr, scales, index);
			productionInfo.setDiscount(vStr);
			// 商品结算单价
			vStr = productionInfo.getSettlementPrice();
			vStr = splitMoney(vStr, scales, index);
			productionInfo.setSettlementPrice(vStr);
		}
	}
	@Deprecated
	private int splitMoney(int value, List<Integer> scales, int index) throws Exception {
		int remain = value;
		int splitValue = -1;
		for (int i = 0; i <= index; i++) {
			int scale = scales.get(i);
			int totalScale = 0;
			for (int j = i; j < scales.size(); j++) {
				totalScale += scales.get(j);
			}
			splitValue = (int) Math.round((double) remain * (double) scale / totalScale);
			remain -= splitValue;
		}
		if (splitValue >= 0) {
			return splitValue;
		} else {
			throw new Exception("支付金额计算异常");
		}
	}
	@Deprecated
	private String splitMoney(String value, List<Integer> scales, int index) throws Exception {
		int v = Integer.parseInt(value);
		v = splitMoney(v, scales, index);
		return String.valueOf(v);
	}

	/**
	 * 返回已经退货的数量
	 * 
	 * */
	public String getBackNumber(Long refundId){
		List<OrderRefund> orderRefunds = orderRefundDao.findSuccessByRefundId(refundId);
		if(orderRefunds.size() == 0){
			return "0";
		}else{
			//OrderRefund orderRefund  = orderRefunds.get(0);
			//Long refundId = orderRefund.getId();
			OrderRefundGoods orderRefundGoods = getOrderRefundGoods(refundId);
			if(null != orderRefundGoods ){
				Long backNumber = orderRefundGoods.getBackNumber();
				return backNumber+"";
			}
			return "0";
		}
	}
	/**
	 * 根据退款单ID和成功退款的状态查询退款商品，因为最多能退两次，调用这个的时候就是第二次，之前只可能有一个成功退款商品
	 * @param refundId    退款单ID 成功状态6
	 * @return
	 */
	public OrderRefundGoods getOrderRefundGoods(Long refundId){
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select * from t_order_refund_goods t where t.refund_id in (select t1.id from t_order_refund t1 where t1.id = ? and t1.status = 6)");
		try {
			return dbHelper.getBean(sql.toString(), OrderRefundGoods.class, new Object[]{refundId});
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}
//	public BackNumber getBackNumber(Long orderId,Long itemId){
//		StringBuilder sql = new StringBuilder();
//		sql.setLength(0);
//		sql.append("select refgoods.back_number backNumber ");
//		sql.append("from t_act_order_goods goods");
//		sql.append(" left join t_order_refund refund");
//		sql.append(" on refund.order_id = goods.act_order_id ");
//		sql.append(" left join t_order_refund_goods refgoods ");
//		sql.append(" on refund.id = refgoods.refund_id ");
//		sql.append("where goods.act_order_id=? and refund.status = 6 and goods.goods_id = ?");
//		try {
//			return dbHelper.getBean(sql.toString(), BackNumber.class, new Object[]{orderId,itemId});
//		} catch (SQLException e) {
//			log.error(e.getMessage());
//			return null;
//		}
//		
//	}
	public List<OrderRefundGoods> getOrderRefundGoodses(Long orderGoodsId,Long goodsId){
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("select t.refund_id from t_order_refund_goods t where t.order_goods_id = ? and t.goods_id = ?");
		try {
			return dbHelper.getBeanList(sql.toString(), OrderRefundGoods.class, new Object[]{orderGoodsId,goodsId});
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}
	public List<Long> getRefundId(List<OrderRefundGoods> orderRefundGoodses){
		List<Long> refundIds = new ArrayList<Long>();
		if(orderRefundGoodses.size() > 0){
			for(int i=0;i<orderRefundGoodses.size();i++){
				OrderRefundGoods  orderRefundGoods = orderRefundGoodses.get(i);
				OrderRefund orderRefund = orderRefundDao.findOne(orderRefundGoods.getRefundId());
				if(orderRefund != null && orderRefund.getStatus() == 6L ){
					refundIds.add(orderRefundGoods.getRefundId());
				}
			}
		}
		return refundIds;
	}
	public ActOrderGoodsInfo getActOrderGoodsInfo(Long actOrderId){
		return actOrderGoodsInfoDao.findOne(actOrderId);
	}
	public OrderRefund getOrderRefund(Long refundId){
		return orderRefundDao.findOne(refundId);
	}
	/**
	 * 保存退款接口返回的ResponseRecordId
	 * @param orderRecordId 退款接口返回的记录ID
	 * @param orderRefundId 退款单ID
	 * @param currency 退款资金种类(1:cash,2:coin,3:record)
	 */
	public void saveResponseRecordId(Long orderRecordId, Long orderRefundId, String currency)throws Exception{
		//根据currency保存到退款单相应的字段中
		OrderRefund orderRefund =  orderRefundDao.findOne(orderRefundId);
		//Method setCurrency= orderRefund.getClass().getMethod("setCurrency" + currency, String.class);
		//setCurrency.invoke(orderRefund, new Object[]{currency});
		Method setOrderRecordId = orderRefund.getClass().getMethod("setOrderRecord" + currency + "Id", Long.class);
		setOrderRecordId.invoke(orderRefund, new Object[]{orderRecordId});
		orderRefundDao.save(orderRefund);

	}
	
	
	/**
	 * 查询成功支付订单(未使用)
	 * @param actOrderId	业务订单id
	 * @return
	 * @throws SQLException
	 */
	public List<PayOrderInfo> findSuccessPayOrder(Long actOrderId) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from t_pay_order t ");
		sql.append(" where 1=1  ");
		sql.append(" and t.OPERATE=1 ");
		sql.append(" and t.STATUS=2 ");
		sql.append(" and t.ACT_ORDER_ID=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(actOrderId);
		sql.append(" order by t.id desc ");
		return dbHelper.getBeanList(sql.toString(), PayOrderInfo.class, params.toArray());
	}
	
	
	public PaymentSpliteOrderInfo getPaymentSpliteOrderInfo(ActOrderPaymentInfo paymentInfo) {
		PaymentSpliteOrderInfo paymentSpliteOrderInfo=null;
		String jsonString = paymentInfo.getSpliteInfo();
		if(StringUtils.isNotEmpty(jsonString)){
			jsonString = StringEscapeUtils.unescapeJavaScript(jsonString);
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			JsonConfig spliteInfoJsonConfig = new JsonConfig();
			spliteInfoJsonConfig.setRootClass(PaymentSpliteOrderInfo.class);
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("spliteGoodsInfos", PaymentSpliteGoodsInfo.class);
			spliteInfoJsonConfig.setClassMap(classMap);
			paymentSpliteOrderInfo=(PaymentSpliteOrderInfo) JSONObject.toBean(jsonObject, spliteInfoJsonConfig);
		}
		return paymentSpliteOrderInfo;
	}
	
	
	public void refund(ActOrderInfo actOrderInfo, 
			PayOrderInfo payOrderInfo, 
			ActOrderPaymentInfo actOrderPaymentInfo, 
	        String currency,
	        double amount,
	        PaymentSpliteOrderInfo paymentSpliteOrderInfo){
	}
	
	/**
	 * 退款预处理。
	 * @param refundParam
	 * @param actOrderInfo
	 * @param orderRefund
	 * @return
	 * @throws Exception
	 */
	public UnifyPayResponse beforeRefundSubmitUnify(RefundParam refundParam,ActOrderInfo actOrderInfo,
			OrderRefund orderRefund) throws Exception{
		UnifyPayResponse unifyPayResponse=null;
		PayOrderInfo payOrderInfo=null;
		//查询表T_PAY_ORDER中支付信息
		List<PayOrderInfo> payOrderInfos=payService.getPayOrderInfosByActOrderId(orderRefund.getOrderId());
		if(null!=payOrderInfos && payOrderInfos.size()>0){
			for(PayOrderInfo tmp:payOrderInfos){
				if(tmp.getOperate()==RequestOperate.Pay && tmp.getStatus()==PayOrderInfo.STATUS_SUCCESS){
					List<PaymentInfo> payments=tmp.getPayments();
					for(PaymentInfo paymentInfo:payments){
							if(paymentInfo.getCurrency().equals(refundParam.getCurrency())){
								payOrderInfo=tmp;
								break;
							}
						
					}
				}
			}
		}
		// 获取支付时的支付参数
		ActOrderPaymentInfo actOrderPaymentInfo = null;
		for (ActOrderPaymentInfo tmp : actOrderInfo.getPaymentInfos()) {
			if (refundParam.getCurrency().equals(tmp.getCurrency())) {
				actOrderPaymentInfo = tmp;
			}
		}
		if(null!=actOrderPaymentInfo){
			PaymentSpliteOrderInfo paymentSpliteOrderInfo =getPaymentSpliteOrderInfo(actOrderPaymentInfo);
			unifyPayResponse=refundSubmitUnify(refundParam,payOrderInfo,orderRefund,actOrderInfo,actOrderPaymentInfo,paymentSpliteOrderInfo);
		}
		return unifyPayResponse;
	}
	

	/**
	 * 
	 * @param payOrderId	支付订单号
	 * @param type	类型，pay和refund	
	 * @return
	 * @throws Exception
	 */
	public CallbackResponse orderCallback(Long payOrderId,String type) throws Exception{
		CallbackRequest callbackRequest = new CallbackRequest();
		if(type.equals("pay")){
			//支付
			callbackRequest.setOperate(RequestOperate.Pay); 
			callbackRequest.setStatusText("模拟支付成功");
		}else if(type.equals("refund")){
			//退款
			callbackRequest.setOperate(RequestOperate.Refund); 
			callbackRequest.setStatusText("模拟退款成功"); 
		}
		callbackRequest.setPayOrderId(payOrderId); //要支付或退款的支付订单号
		callbackRequest.setStatusCode(0);
		String str=payService.submit("/callback", callbackRequest, null);
		JSONObject jsonObject = JSONObject.fromObject(str);
		CallbackResponse response=(CallbackResponse) JSONObject.toBean(jsonObject,CallbackResponse.class);
		log.info("补单返回信息:"+response);
		return response;
	}
	
	public void exportExcell(List<Order> orderList,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] head = {"订单号","用户手机号","商品类型","创建时间","商品名称","订单类型","订单状态"};
		Connection conn=dbHelper.getConn();
		WritableWorkbook book = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		String dir = request.getSession().getServletContext().getRealPath("") +File.separator;
		String fileName ="order_" + time + ".xls";
		File file = new File(dir,fileName);
		CellView cellView=new CellView();
		cellView.setAutosize(true);
		log.info("导出订单数量："+orderList.size());
		//页码数
		int page=0;
		//行数
		int row=0;
		WritableSheet sheet=null;
		try {
			book = Workbook.createWorkbook(file);
			if(orderList.size()==0){
				sheet = book.createSheet("第"+(page+1)+"页", page);
				for (int i = 0; i < head.length; i++) {
					Label label = new Label(i, 0, head[i]);
					sheet.addCell(label);
				}
			}
			for(int n=0;n<orderList.size();n++){
				//每页10000条记录
				if(n%10000==0){
					sheet = book.createSheet("第"+(page+1)+"页", page);
					for (int i = 0; i < head.length; i++) {
						Label label = new Label(i, 0, head[i]);
						sheet.addCell(label);
//						sheet.setColumnView(0, cellView);
					}
					row=1;
					page++;
				}
				//列数
				int k=0;
				while(k<head.length){
					Label label1 = new Label(k++, row,orderList.get(n).getId()+"");
					sheet.addCell(label1);
					Label label2 = new Label(k++, row,orderList.get(n).getTerminalId());
					sheet.addCell(label2);
					Label label3 = new Label(k++, row,orderList.get(n).getItemModeName());
					sheet.addCell(label3);
					Label label4 = new Label(k++, row,orderList.get(n).getCreateTime());
					sheet.addCell(label4);
					String itemNameStr="";
					for(ItemSale sale:orderList.get(n).getItemSales()){
						itemNameStr+=sale.getName().trim()+",";
					}
					itemNameStr=itemNameStr.substring(0, itemNameStr.length()-1);
					Label label5 = new Label(k++, row,itemNameStr);
					sheet.addCell(label5);
					Label label6 = new Label(k++, row,orderList.get(n).getActTypeName());
					sheet.addCell(label6);
					Label label7 = new Label(k++, row,orderList.get(n).getOrderStatus());
					sheet.addCell(label7);
					sheet.setColumnView(row, cellView);
				}
				row++;
			}
			book.write();
			book.close();
		    response.setContentType("text/plain; charset=utf-8");  
		    response.setHeader("Content-disposition", "attachment; filename="  
		                + new String(fileName.getBytes("utf-8"), "ISO8859-1"));  
			bis = new BufferedInputStream(new FileInputStream(dir+fileName));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(null != conn){
					conn.close();
				}
				if(null != bis){
					bis.close();
				}
				if(null != bos){
					bos.close();
				}
				if (file.exists()) {
					file.delete();
				}
			} catch (Exception e) {
				throw e;
			}
		}
	}
	public PayOrderInfo getPayOrderId(Long orderId,String currency,RequestOperate requestOperate) throws Exception{
		List<PayOrderInfo> payOrderInfos=payService.getPayOrderInfosByActOrderId(orderId);
		if(null!=payOrderInfos && payOrderInfos.size()>0){
			for(PayOrderInfo tmp:payOrderInfos){
				if(tmp.getOperate()==requestOperate && tmp.getStatus()!=PayOrderInfo.STATUS_SUCCESS){
					List<PaymentInfo> payments=tmp.getPayments();
					for(PaymentInfo paymentInfo:payments){
						if(paymentInfo.getCurrency().equals(currency)){
							return tmp;
						}
						
					}
				}
			}
		}
		return null;
	}
	public void saveUpdateStatus(Long orderId,String currency,Long reasonId,HttpSession session,String updateTime) throws SQLException{
		OrderRefundException orderRefundException = new OrderRefundException();
		orderRefundException.setResourceId(orderId);
		orderRefundException.setResouceType(1L);
		orderRefundException.setPayment(currency);
		orderRefundException.setReason(reasonId);
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		orderRefundException.setCreateUserId(sessionUser.getId());
		orderRefundException.setCreateTime( updateTime);
		orderService.saveOrder(orderRefundException);
	}
	public List<OrderRefundException> getOrderRefundException(Long sourceId,Long sourceType){
		return exceptionDao.findOrderRefundExceptionBySourceIdAndSourceType(sourceId, sourceType);
	}
}
