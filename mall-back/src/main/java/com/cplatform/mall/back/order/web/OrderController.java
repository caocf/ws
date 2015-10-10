package com.cplatform.mall.back.order.web;
import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.order.entity.CallbackResponse;
import com.cplatform.mall.back.order.entity.CodeInfo;
import com.cplatform.mall.back.order.entity.ExpressStrack;
import com.cplatform.mall.back.order.entity.ExpressStrackData;
import com.cplatform.mall.back.order.entity.Order;
import com.cplatform.mall.back.order.entity.OrderPage;
import com.cplatform.mall.back.order.entity.OrderRefundException;
import com.cplatform.mall.back.order.entity.Pagination;
import com.cplatform.mall.back.order.entity.RequestUtils;
import com.cplatform.mall.back.order.service.CodeInfoService;
import com.cplatform.mall.back.order.service.OrderRefundService;
import com.cplatform.mall.back.order.service.OrderService;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.sys.service.UserService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.HttpUrlUtils;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderHistory;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderService;
import com.cplatform.order.PaymentSpliteGoodsInfo;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.RequestOperate;
import com.cplatform.util2.TimeStamp;

/**
 * 订单控制类 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-8 下午8:02:52
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	private static Logger log = Logger.getLogger(OrderController.class);
	@Autowired
	private LogUtils logUtils;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CodeInfoService codeInfoService;

	@Autowired
	ActOrderService actOrderService;

	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	HttpUrlUtils httpUrlUtils;
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/list")
	public String findOrderList(
			OrderPage order,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) throws SQLException {
		Page<OrderPage> pageData = this.orderService.findOrderList(order, page,
				Page.DEFAULT_PAGESIZE);
		// Page<Order> pageData = this.orderService.findPageList(order, page,
		// Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", pageData);
		return "/order/order-list";
	}

	@RequestMapping(value = "/view")
	public String orderView(@RequestParam(value = "id") Long id, Model model)
			throws Exception {
		ActOrderInfo order = orderService.getActOrderInfoById(id);
		List<ActOrderHistory> actOrderHistorys = actOrderService.getActOrderHistory(id);
		// 订单用户信息、商户信息map
		Map<String, String> userMap = orderService.getUserByOrderId(id);
		model.addAttribute("userMap", userMap);
		// 订单物流公司map
		Map<String, String> expressMap = orderService.getExpressByOrderId(id);
		model.addAttribute("expressMap", expressMap);
		// 配送方式和商品类型map
		Map<String, String> itemMap = orderService.getOrderDeliveryMode(id);
		model.addAttribute("itemMap", itemMap);
		// 支付信息和结算信息
		orderService.getPayMsg(order, model);

		//获取订单支付补单日志信息
		orderService.getExceptionModifyMsg(model, order.getId());
		
		String json = null;
		try {
			json = httpUrlUtils.getHttpIfCard("{'channel':"+1+",'orderId':"+id+"}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		model.addAttribute("ifCardPay", orderService.getIfCardPay(json));
		model.addAttribute("order", order);
		model.addAttribute("actOrderHistorys", actOrderHistorys);
		if(null!=itemMap && itemMap.get("itemMode").equals("1")){
			// 获取虚拟商品码
			CodeInfo codeInfo = new CodeInfo();
			codeInfo.setActOrderId(order.getId());
			List<ActOrderGoodsInfo> goodInfos = order.getGoodsInfos();
			if (null != goodInfos && goodInfos.size() > 0) {
//				codeInfo.setItemOrderId(goodInfos.get(goodInfos.size() - 1).getId());
				model.addAttribute("goodInfo",goodInfos.get(goodInfos.size() - 1));
			}
			List<CodeInfo> codes = codeInfoService.listStoreCodes(codeInfo);
			model.addAttribute("codes", codes);
			return "/order/order-view-virtual";
		}
		return "/order/order-view";

	}

	/**
	 * 订单物流追踪查看
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/showExpress")
	public String showExpress(Model model, HttpServletRequest request,
			HttpServletResponse response, Integer pageIndex, Long orderId)
			throws SQLException {
		Pagination pagination = new Pagination();
		Map<String, String> expressMap = orderService
				.getExpressByOrderId(orderId);
		String com = expressMap.get("ename");
		String nu = null;
		if (StringUtils.isNotEmpty(expressMap.get("expressNo"))) {
			nu = expressMap.get("expressNo");
		}
		// 获取物流信息json对象
		String jsonString = null;
		try {
			jsonString = orderService.getExpressJsonString(com, nu);
		} catch (SocketException e) {
			logUtils.logAdd("调用快递100接口超时", e.getMessage());
			log.error("调用快递100接口超时:" + e.getMessage());
		} catch (Exception e) {
			logUtils.logAdd("调用快递100接口失败", e.getMessage());
			log.error("调用快递100接口失败:" + e.getMessage());
		}
		logUtils.logAdd("调用接口成功", jsonString);
		log.info(jsonString);
		List<ExpressStrackData> datas = null;
		List<ExpressStrackData> clist = null;
		if (pageIndex == null) {
			pageIndex = 1;
		}
		Integer s1 = (pageIndex - 1) * pagination.getPageSize();
		Integer s2 = pageIndex * pagination.getPageSize();
		// 解析json
		if (StringUtils.isNotEmpty(jsonString)) {
			JSONArray jarr = JSONArray.fromObject("[" + jsonString + "]");
			List<ExpressStrack> listobject = (List<ExpressStrack>) JSONArray
					.toCollection(jarr, ExpressStrack.class);
			if (null != listobject && listobject.size() > 0) {
				datas = listobject.get(0).getData();
				if (null != datas && datas.size() > 0) {
					if (s2 >= datas.size()) {
						s2 = datas.size();
					}
					clist = datas.subList(s1, s2);
					// 设置分页对象属性
					String url = RequestUtils.assembleOneUri(request,
							"pageIndex");
					pagination.setUrl(url);
					pagination.setPageIndex(pageIndex);
					pagination.setTotalRecordCnt(datas.size());
					if (pageIndex < 1) {
						pageIndex = 1;
					} else if (pageIndex > pagination.getTotalPageCnt()) {
						pageIndex = pagination.getTotalPageCnt();
					}
					pagination.setDataList(clist);
				}
			}
		}
		model.addAttribute("pagination", pagination);
		model.addAttribute("orderId", orderId);
		return "order/order-express";
	}

	/**
	 * 补发验证码
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/reissued")
	@ResponseBody
	public Object reissued(@RequestParam(value = "id") Long id, Long orderId,Model model)
			throws Exception {
		// 调用补发接口
		String msg = orderService.reissued2(id,orderId);
		logUtils.logOther("补发验证码", "调用补发接口");
		return JsonRespWrapper.successAlert(msg);
	}

	/**
	 * 重发验证码
	 * 
	 * @param id
	 *            码编号
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/resend")
	@ResponseBody
	public Object resend(String id, Model model) throws SQLException {
		// 调用重发接口
		String msg = orderService.resend(id);
		logUtils.logOther("重发验证码", "调用重发接口");
		return JsonRespWrapper.successAlert(msg);
	}

	/**
	 * 统计订单列表页面
	 * 
	 * @param storeId
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/statisticaOrderList/{extInfo}/{payStatus}")
	public Object statisticaOrderList(
			Order order,
			@PathVariable Long extInfo,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) throws SQLException {
		order.setExtInfo(extInfo.toString());
		Page<Order> orderlist = this.orderService.statisticalOrderByActId(order, page, Page.DEFAULT_PAGESIZE);
		model.addAttribute("pageData", orderlist);
		model.addAttribute("PayStatusMap",Order.getPayStatusMap());
		model.addAttribute("syncGyFlagMsg", Store.syncGyFlagMsg);
		return "/order/statisticalOrder-list";
	}
	
	 
	@RequestMapping(value = "/statistView")
	public String statistView(@RequestParam(value = "id") Long id, Model model)
			throws Exception {
		Order order = orderService.findStiOrderById(id);
	List<PaymentSpliteGoodsInfo> ItmeInfos	= orderService.praseSpliteInfo(order.getSpliteInfo());
		model.addAttribute("order", order);
		model.addAttribute("ItmeInfos", ItmeInfos);
		model.addAttribute("payStatusMap", Order.getPayStatusMap());
		return "/order/statisticalOrder-view";
		
	}
	
	@RequestMapping(value = "/changeOrder")
	 public String changeOrder( Model model, Long id,OrderRefundException orderRefundException ){
		model.addAttribute("orderRefundException",orderRefundException);
		model.addAttribute("reasonMap", OrderRefundException.reasonMap);
		return "/order/order_refund_extion";
	}
	
	@RequestMapping(value = "/updatePayStatus/{orderId}")
	 public String updateStutas( Model model, @PathVariable Long orderId,OrderRefundException orderRefundException,HttpSession session ){
		Map<Long,String> reasonMap = OrderRefundException.reasonMap;
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		model.addAttribute("updateUser", sessionUser.getName());
		model.addAttribute("updateTime", TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		model.addAttribute("reasonMap", reasonMap);
		return "/order/order-updateStatus";
	}
	
	@RequestMapping(value = "/updatePayStatusGo")
	@ResponseBody
	public Object updatePayStatusGo(@RequestParam(value = "orderId") Long orderId, 
			@RequestParam(value = "updateTime") String updateTime,
			@RequestParam(value = "reasonId") Long reasonId,
			@RequestParam(value = "currency") String currency,
			HttpSession session,
			Model model) throws Exception{
		PayOrderInfo payOrderInfo = orderService.getPayOrderId(orderId, currency,RequestOperate.Pay);
		if(null != payOrderInfo){
			CallbackResponse response = orderRefundService.orderCallback(payOrderInfo.getId(), "pay");
			if( 0 == response.getStatusCode()){
				return orderService.saveUpdateStatus(orderId,currency,reasonId,session,updateTime);
			}
		}
		return "fail";
	}
	
}
