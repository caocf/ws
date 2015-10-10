package com.cplatform.b2c.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.act.ActServiceClient;
import com.cplatform.act.CloseRequest;
import com.cplatform.act.CloseResponse;
import com.cplatform.act.PayResponse;
import com.cplatform.b2c.constant.OrderConstant;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.QueryWelfareResp;
import com.cplatform.b2c.model.CardGiftDetails;
import com.cplatform.b2c.model.CodeInfo;
import com.cplatform.b2c.model.ItemComment;
import com.cplatform.b2c.model.ItemLottery;
import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.SzMallOrderHistory;
import com.cplatform.b2c.model.TActOrder;
import com.cplatform.b2c.model.TActOrderNew;
import com.cplatform.b2c.model.TItemComment;
import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.b2c.model.TOrderRefund;
import com.cplatform.b2c.repository.MemberCenterRepository;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.CloseRequestService;
import com.cplatform.b2c.service.CodeInfoService;
import com.cplatform.b2c.service.ItemService;
import com.cplatform.b2c.service.JmsMessageService;
import com.cplatform.b2c.service.MemberCenterService;
import com.cplatform.b2c.service.OrderService2;
import com.cplatform.b2c.service.PayOrderService;
import com.cplatform.b2c.service.PerformService;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.service.TOrderRefundGoodsService;
import com.cplatform.b2c.service.UMPInterfaceService;
import com.cplatform.b2c.service.WelfareOrderService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.ArrayUtil;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.b2c.util.MenuCodeUtil;
import com.cplatform.b2c.util.StringUtil;
import com.cplatform.b2c.util.TimeUtil;
import com.cplatform.b2c.web.validator.AddressValidator;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.order.ActOrderGoodsInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderPaymentInfo;
import com.cplatform.order.ActOrderService;
import com.cplatform.order.ActOrderStatus;
import com.cplatform.pay.PayOrderInfo;
import com.cplatform.pay.PayServiceClient;
import com.cplatform.sso.lmsh.bean.LoginUserBean;
import com.cplatform.util2.TimeStamp;
import com.cplatform.verify.api.ResendRequest;
import com.cplatform.verify.api.ResendRequest.CodeType;
import com.cplatform.verify.api.ResendResponse;
import com.cplatform.verify.client.VerifyServiceClient;
import com.cplatform.verifycode.Decrypt;
import com.cplatform.verifycode.RevokeRespInfo;

/**
 * 个人中心控制类 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Jun 6, 2013 3:27:05 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Controller
public class MemberCenterController {

	private final Logger logger = Logger.getLogger(MemberCenterController.class);

	@Autowired
	private MemberCenterService centerService;

	@Autowired
	private ActOrderService client;

	@Autowired
	private PayServiceClient payServiceClient;

	@Autowired
	private ActServiceClient actBusinessClient;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private JmsMessageService jmsMessageService;

	@Autowired
	private WelfareOrderService welfareOrderService;

	@Autowired
	private UMPInterfaceService umpInterfaceService;

	@Autowired
	private MemberHnCenterController memberHnCenterController;

	@Autowired
	private PerformService performService;

	@Autowired
	private OrderService2 orderService2;

	@Autowired
	private PayOrderService payOrderService;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private ItemService itemService;

	@Autowired
	private CodeInfoService codeInfoService;

	@Autowired
	private TOrderRefundGoodsService tOrderRefundGoodsService;

	@Autowired
	private CloseRequestService closeRequestService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private MenuCodeUtil menuCodeUtil;

	@Autowired
	private AddressValidator addressValidator;

	/**
	 * 获取我的评价
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/center/comments")
	public String getMyComments(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		List<ItemComment> list = centerService.getComments(pageInfo, userId);
		model.addAttribute("list", list);
		model.addAttribute("pageInfos", centerService.getCommentsScript(pageInfo, userId));

		return "center/comments";
	}

	/**
	 * 获取我的咨询
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/center/consults")
	public String getMyConsults(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		LoginUserBean userinfo = null;
		userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		List<ItemComment> list = centerService.getConsults(pageInfo, userId);
		model.addAttribute("list", list);
		model.addAttribute("pageInfos", centerService.getConsultsScript(pageInfo, userId));
		return "center/consults";
	}

	/**
	 * 获取我的收货地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/addresses")
	public String getMyAddress(HttpServletRequest request, HttpServletResponse response, Model model) {
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		List<String[]> list = centerService.getAddresses(userId);
		model.addAttribute("list", list);
		model.addAttribute("address", new TMemberAddress());
		model.addAttribute("webRoot", appConfig.getWebRoot());
		return "center/addresses";
	}

	/**
	 * 获取某个地址
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/center/getAddress", method = RequestMethod.GET)
	public void getAddress(HttpServletResponse response, @RequestParam("id") String id) {
		response.setContentType("text/json; charset=GBK");
		Map<String, TMemberAddress> map = new HashMap<String, TMemberAddress>();
		map.put("address", centerService.getAddress(id));
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			logger.error("获取地址报错", e);
		}
	}

	/**
	 * 保存我的收货地址
	 * 
	 * @param request
	 * @param address
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/saveAddress", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateAddress(TMemberAddress tMemberAddress, HttpServletRequest request, HttpServletResponse response, Model model,
	        BindingResult bResult) {
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		TMemberAddress address = new TMemberAddress();
		// 验证收货地址信息
		addressValidator.validate(address, bResult);
		if (bResult.hasErrors()) {
			return JsonRespWrapper.failure(bResult.getFieldErrors().get(0).getDefaultMessage());
		}

		if (null != tMemberAddress.getId() && !"".equals(tMemberAddress.getId())) {
			address = centerService.getAddress(tMemberAddress.getId().toString());
		} else {
			address.setDefaultShipping("0");
		}
		address.setRegion(tMemberAddress.getRegion());
		address.setZipcode(tMemberAddress.getZipcode());
		address.setAddress(tMemberAddress.getAddress());
		address.setName(tMemberAddress.getName());
		address.setMobile(tMemberAddress.getMobile());
		address.setPhone(tMemberAddress.getPhone());
		address.setMid(Integer.valueOf(userId));
		boolean flag = centerService.saveOrUpdateAddress(address);
		model.addAttribute("flag", flag ? "1" : "0");
		return JsonRespWrapper.redirectSuccess(null, "addresses.chtml");
	}

	/**
	 * 设为默认地址
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/center/setAddress", method = RequestMethod.GET)
	public void setAddress(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
		response.setContentType("text/json; charset=GBK");
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		boolean flag = centerService.setAddress(id, userId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", flag ? "1" : "0");
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			logger.error("设置为默认地址", e);
		}
	}

	/**
	 * 删除地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/delAddress", method = RequestMethod.POST)
	@ResponseBody
	public Object delAddress(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		boolean flag = centerService.delAddress(id);
		model.addAttribute("flag", flag ? "1" : "0");
		return JsonRespWrapper.redirectSuccess(null, "addresses.chtml");
	}

	/**
	 * 订单评价
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/center/isComment", method = RequestMethod.GET)
	public void getComment(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
		response.setContentType("text/json; charset=GBK");
		Map<String, String> map = new HashMap<String, String>();
		String flag = centerService.getOrderComment(Long.valueOf(id));
		map.put("flag", flag);
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			logger.error("订单评价", e);
		}
	}

	/**
	 * 进入评价面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/addComment", method = RequestMethod.GET)
	public String addComment(HttpServletRequest request, @RequestParam("saleIds") String saleIds, @RequestParam("flag") String flag,
	        @RequestParam("storeId") String storeId, Model model) {
		if (StringUtils.equals(flag, "1")) {
			model.addAttribute("orderId", saleIds);
			saleIds = centerService.getSaleIdByOrder(saleIds);
		}
		List<ItemSaleDataDTO> list = centerService.getCommentItems(saleIds);
		model.addAttribute("list", list);
		model.addAttribute("saleIds", saleIds);
		model.addAttribute("storeId", storeId);
		return "center/addComment";
	}

	/**
	 * 保存评价
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/saveComment", method = RequestMethod.POST)
	public String saveCommet(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginUserBean userinfo = null;
		userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		String nickname = userinfo.getNickName();
		String userId = userinfo.getId().toString();
		String saleId = request.getParameter("saleIds");
		String orderId = request.getParameter("orderId");
		String storeId = request.getParameter("storeId");
		String[] saleIds = saleId.split(",");
		for (String id : saleIds) {
			TItemComment comment = new TItemComment();
			comment.setContent(request.getParameter("message_" + id));
			comment.setNickname(StringUtils.isEmpty(nickname) ? userId : nickname);
			comment.setUserId(Integer.valueOf(userId));
			comment.setSaleId(Integer.valueOf(id));
			comment.setStoreId(Long.valueOf(storeId));
			comment.setRank(Integer.valueOf(request.getParameter("star_" + id)));
			if (StringUtils.isNotEmpty(orderId)) {
				comment.setActOrderId(Long.valueOf(orderId));
			}
			centerService.saveComment(comment);
		}
		return "redirect:./comments.chtml";
	}

	@RequestMapping(value = "/center/region")
	public void genRegion(HttpServletResponse response) {
		response.setContentType("text/json; charset=GBK");
		List<String[]> a = centerService.getRegion("0", "0");
		StringBuffer sql = new StringBuffer();
		sql.append("[");
		int z = 0;
		for (String[] l : a) {
			if (z > 0) {
				sql.append(",");
			}
			sql.append("{n:'").append(l[0]).append("',c:[");
			List<String[]> citys = centerService.getRegion("1", l[1]);
			int j = 0;
			for (String[] city : citys) {
				if (j > 0) {
					sql.append(",");
				}
				sql.append("{n:'").append(city[0]).append("',a:[");
				List<String[]> qus = centerService.getRegion("2", city[1]);
				int i = 0;
				for (String[] qu : qus) {
					if (i > 0) {
						sql.append(",");
					}
					sql.append("'").append(qu[0]).append("'");
					i++;
				}
				sql.append("]}");
				j++;
			}
			sql.append("]}");
			z++;
		}
		sql.append("]");
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", sql.toString());
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			logger.error("MemberCenterController.genRegion", e);
		}
	}

	/**
	 * 个人中心--我买的商品，老版本
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/oldOrderManager")
	public String getOrders(HttpServletRequest request, HttpServletResponse response, Model model) {
		SessionUser userinfo = SessionUser.getSessionUser(response);
		// 判断是否河南商品
		if (userinfo != null && StringUtils.isNotBlank(userinfo.getAreaCode())) {
			String provRegionCode = shopService.getProvinceCodeByRegionCode(userinfo.getAreaCode(), null);
			if (StringUtils.isNotBlank(provRegionCode) && Constants.PROV_HENAN_REGION_CODE.equals(provRegionCode)) {
				return memberHnCenterController.getHnOrders(request, response, model);
			}
		}
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String isdelivery = request.getParameter("isdelivery");
		String flag = request.getParameter("flag");
		String userId = userinfo.getId().toString();
		List<TActOrder> list = centerService.getOrders(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery);
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("webRoot", appConfig.getWebRoot());
//		model.addAttribute("unpay", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_PAY), startTime, endTime,name, flag, isdelivery,orderTypes));
//		model.addAttribute("sent", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_RECEIPT), startTime, endTime,name, flag, isdelivery,orderTypes));
		model.addAttribute("delivery", centerService.getOrdersByDelivery(userId, "1"));
		model.addAttribute("isdelivery", isdelivery);
		model.addAttribute("pageInfos", centerService.getOrdersScript(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery));
		return "center/orders";
	}

	/**
	 * 我的红包订单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/redPackage")
	public String getRedPackage(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		SessionUser userinfo = SessionUser.getSessionUser(response);
		// 判断该用户手机号是否有红包的查看
		if (userinfo != null && !StringUtils.isBlank(userinfo.getTerminalId())) {
			// boolean flag =
			// centerService.getBonusTerminal(Long.parseLong(userinfo.getTerminalId()));
			// if (flag) {
			String userId = userinfo.getId().toString();
			List<TActOrderNew> list = centerService.getRedOrders(userId, pageInfo);
			model.addAttribute("list", list);
			model.addAttribute("webRoot", appConfig.getWebRoot());
			// 显示订单
			model.addAttribute("red", 1);
			model.addAttribute("pageInfos", centerService.getRedOrdersScript(userId, pageInfo));
			// } else {
			// // 没有资格
			// model.addAttribute("red", 0);
			// }
		} else {
			// 手机未绑定
			model.addAttribute("red", 2);
		}

		return "center/redPackageOrders";
	}

	/**
	 * 我的 竞拍type=1,我的秒杀type=2
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/center/gCheapOrder")
	public String getgCheapOrders(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		JSONObject jsonObject = new JSONObject();
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String type = request.getParameter("orderTypes");
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		// 修改这个方法，调用接口
		String orderJson = null;
//		String orderJson = centerService.getgCheapOrders(userId, name, startTime, endTime, type, curpage);
		if (!StringUtils.isEmpty(orderJson)) {
			if (orderJson.indexOf("FLAG") <= 0) {
				jsonObject = JSONObject.fromObject(orderJson);
			}
			if (orderJson.indexOf("TOTAL_ROW") > 0) {
				PageInfo pageInfo = centerService.getgCheapOrdersPageInfo(curpage, jsonObject);
				model.addAttribute("pageInfos", pageInfo.getScript());
				model.addAttribute("totalPage", pageInfo.getPageTotal());
				JSONArray jsonArray = (JSONArray) jsonObject.get("DATA");
				if (jsonArray != null && jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject myObject = (JSONObject) jsonArray.get(i);
						// 设置过期时间
						if (type.equals("1")) {
							myObject.put("buyTime", TimeUtil.addMinutesTim(myObject.getString("createTime"), appConfig.getgCheapAuctionTime()));
						} else if (type.equals("2")) {
							myObject.put("buyTime", TimeUtil.addMinutesTim(myObject.getString("createTime"), appConfig.getgCheapSeckillTime()));
						}
						String flag = "0";
						if (TimeUtil.compareTime(TimeUtil.now(),
						                         TimeUtil.format(TimeUtil.fromToDate(myObject.getString("buyTime")), "yyyyMMddHHmmss")) > 0) {
							flag = "1";
						}
						myObject.put("isExpires", flag);
					}
				}
			}
		}

		model.addAttribute("list", jsonObject);
		model.addAttribute("type", type);
		model.addAttribute("webRoot", appConfig.getWebRoot());
//		model.addAttribute("unpay", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_PAY), startTime, endTime,name, flag, isdelivery,orderTypes));
//		model.addAttribute("sent", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_RECEIPT), startTime, endTime,name, flag, isdelivery,orderTypes));
		String select_flag=request.getParameter("select_flag");
		select_flag=StringUtil.isEmpty(select_flag)?"0":select_flag;
		model.addAttribute("select_flag",select_flag);
		return "center/myorders";
	}

	@RequestMapping(value = "/center/gCheapDeposit")
	public String getgCheapDeposits(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonObject = new JSONArray();
		LoginUserBean userinfo = null;
		userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		String orderJson = centerService.getgCheapDeposit(userinfo.getId());
		if (!StringUtils.isEmpty(orderJson)) {
			if (orderJson.indexOf("FLAG") <= 0) {
				jsonObject = JSONArray.fromObject(orderJson);
			}
		}
		model.addAttribute("list", jsonObject);
		return "center/gCheapDeposits";
	}

	/**
	 * 查看订单详情
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/orderView", method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request, HttpServletResponse response, @RequestParam("orderId") String orderId, Model model) {
	
		ActOrderInfo info = new ActOrderInfo();
		List<PayOrderInfo> payOrderInfos = new ArrayList<PayOrderInfo>();
		String amount = "";
		String payment = "";
		String express = "";
		String isXuNi = "";
		int status = 1;
		List<Object[]> codes = new ArrayList<Object[]>();
		List<Map<String, Object>> goodses = new ArrayList<Map<String, Object>>();
		try {
			SessionUser userinfo = SessionUser.getSessionUser(response);
			info = client.getActOrderInfo(Long.valueOf(orderId));

			if (userinfo != null && info != null && userinfo.getId().equals(info.getUserId())) {

				logger.info("orderView=info=========================" + info);
				// 查询支付信息
				payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(Long.valueOf(orderId));
				logger.info("orderView=payOrderInfos=========================" + payOrderInfos);
				int goodAmount = 0;
				int discount = 0;
				StringBuffer ids = new StringBuffer();

				// 当前数值只适用于限时抢购商品
				BigDecimal marketPriceAount = new BigDecimal(0);

				for (int i = 0; i < info.getGoodsInfos().size(); i++) {

					ActOrderGoodsInfo good = info.getGoodsInfos().get(i);
					// 单个商品的清单信息jiaojian
					Map<String, Object> map = new HashMap<String, Object>();
					map = centerService.getGoodsDetail(good.getGoodsId());
					List<Map<String, Object>> parames = centerService.getGoodsParam(good.getGoodsId());
					map.put("parames", parames);
					map.put("name", good.getGoodsSubject());
					map.put("price", CommonUtils.toYuan(good.getPayPrice()));
					map.put("count", good.getCount());
					map.put("versionId", good.getGoodsVersion());
					goodses.add(map);
					goodAmount += good.getCount() * good.getPayPrice();
					// 判断是否是限时抢购商品
					if (centerService.checkIsPromptOrder(info.getOrderType())) {
						ItemSaleDataDTO itemSaleDataDTO = thirdInterDao.getItemById(String.valueOf(good.getGoodsId()));
						marketPriceAount = marketPriceAount.add(centerService.getMarketPriceAmount(marketPriceAount, good, itemSaleDataDTO));
					}

					discount += good.getDiscount();
					if (i > 0) {
						ids.append(",");
					}
					ids.append(good.getGoodsId());
					if (Constants.ORDER_TYPE_PERFORM_YONGLE == info.getOrderType() || Constants.ORDER_TYPE_PERFORM_STORE == info.getOrderType()) {
						Map<String, String> ticketInfo = performService.getPerformTicketInfo(Long.toString(good.getGoodsId()));
						if (ticketInfo != null) {
							model.addAttribute("url", ticketInfo.get("url"));
							model.addAttribute("imgPath", ticketInfo.get("img_path"));
							model.addAttribute("ticketTime", ticketInfo.get("ticket_time"));
						}

					}
				}
				express = centerService.getExpress(ids.toString());
				isXuNi = centerService.getIsXuNi(ids.toString());
				logger.info("orderView=isXuNi=========================" + isXuNi);
				amount = centerService.getTotal(info, goodAmount, discount, isXuNi, marketPriceAount);
				status = centerService.getStatus(isXuNi, info);
				logger.info("orderView=status=========================" + status);
				int i = 0;
				for (ActOrderPaymentInfo pay : info.getPaymentInfos()) {
					if (i > 0) {
						payment += "+";
					}
					if (StringUtils.equals(pay.getCurrency(), "cash")) {
						payment += "现金";
					} else if (StringUtils.equals(pay.getCurrency(), "coin")) {
						payment += "商城币";
					} else if (StringUtils.equals(pay.getCurrency(), "score")) {
						payment += "积分";
					} else if (StringUtils.equals(pay.getCurrency(), "balance")) {
						payment += "话费";
					}
				}
				// 拉手的code需要解密
				if (StringUtils.equals("1", isXuNi)) {
					codes = centerService.getVerifyCode(info.getId());
					if (codes != null && codes.size() > 0) {
						for (int j = 0; j < codes.size(); j++) {
							Object[] codeObj = codes.get(j);
							if (info.getOrderType() != null && info.getOrderType() == 20) {
								String num2pwd = Decrypt.decrypt(codeObj[1].toString());
								if (num2pwd.indexOf("@@@") > 0) {
									String[] str = num2pwd.split("@@@");
									codeObj[1] = str[0];
									codeObj[3] = str[1];
								}
							}
						}
					}
				}
				model.addAttribute("flag", 1);
			} else {
				model.addAttribute("flag", 0);
			}
		}
		catch (NumberFormatException e) {
			logger.error("查看订单", e);
		}
		catch (Exception e) {
			logger.error("查看订单", e);
		}
		model.addAttribute("info", info);
		if (null != info && null != info.getExpressInfo()) {
			model.addAttribute("number", info.getExpressInfo().getExpressCompanyName());
			model.addAttribute("name", info.getExpressInfo().getExpressNo());
		} else {
			model.addAttribute("number", "");
			model.addAttribute("name", "");
		}
		model.addAttribute("amount", amount.toString());
		model.addAttribute("payOrderInfos", payOrderInfos);
		model.addAttribute("payment", payment);
		model.addAttribute("express", express);
		model.addAttribute("isXuNi", isXuNi);
		model.addAttribute("status", status);
		model.addAttribute("codes", codes);
		model.addAttribute("goodsDetail", goodses);
		String web_url=request.getParameter("web_url");
		if("myTicket".equals(web_url)){
			model.addAttribute("web_url","我的票务");
		}else{
			model.addAttribute("web_url","我的订单");
		}

		// 对应的菜单编号
		String menucode = request.getParameter("menucode");
		// 菜单对应的相关参数
		String menuValue = menuCodeUtil.getMenuValue(menucode);
		model.addAttribute("menuValue", menuValue);

		return "center/viewOrder";
	}

	/**
	 * 查看退款单
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/refund", method = RequestMethod.GET)
	public String getRefund(HttpServletRequest request, @RequestParam("orderId") String orderId, Model model,
	        @RequestParam("refundId") String refundId) {
		ActOrderInfo info = new ActOrderInfo();
		List<TOrderRefund> refunds = new ArrayList<TOrderRefund>();
		try {
			info = client.getActOrderInfo(Long.valueOf(orderId));
			refunds = centerService.getRefunds(orderId, info);
		}
		catch (NumberFormatException e) {
			logger.error("查看退款订单", e);
		}
		catch (Exception e) {
			logger.error("查看退款订单", e);
		}
		model.addAttribute("list", refunds);
		model.addAttribute("refundId", refundId);
		return "center/refund";
	}

	/**
	 * 获取支付
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/center/getPayInfo", method = RequestMethod.GET)
	public void getPayInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
		response.setContentType("text/json; charset=GBK");
		ActOrderInfo info = new ActOrderInfo();
		try {
			info = client.getActOrderInfo(Long.valueOf(id));
		}
		catch (Exception e) {
			logger.error("获取支付", e);
		}
		Map<String, String> map = centerService.getPayInfo(info);
		DecimalFormat fnum = new DecimalFormat("##0.00");
		map.put("payCash", fnum.format(Float.valueOf(map.get("cash")) / 100));
		map.put("payCoin", String.valueOf(Integer.valueOf(map.get("coin")) / 100));
		map.put("payScore", fnum.format(Float.valueOf(map.get("score")) / 100));
		map.put("payBalance", fnum.format(Float.valueOf(map.get("balance")) / 100));
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			logger.error("订单获取支付，打印信息", e);
		}
	}

	/**
	 * 确认收货
	 * 
	 * @param request
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/delivery", method = RequestMethod.GET)
	public String delivery(HttpServletRequest request, @RequestParam("orderId") String orderId, Model model) {
		ActOrderInfo info = new ActOrderInfo();
		try {
			info = client.getActOrderInfo(Long.valueOf(orderId));
		}
		catch (Exception e) {
			logger.error("确认收货", e);
		}
		model.addAttribute("info", info);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		return "center/orderDelivery";
	}

	/**
	 * 确认收货
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/orderDelivery", method = RequestMethod.POST)
	public String orderDelivery(HttpServletRequest request, HttpServletResponse response, Model model) {
		String orderId = request.getParameter("orderId");
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		StringBuilder saleIds = new StringBuilder();
		saleIds.setLength(0);
		try {
			ActOrderInfo info = client.getActOrderInfo(Long.valueOf(orderId));
			int i = 0;
			List<ActOrderGoodsInfo> goods = info.getGoodsInfos();
			for (ActOrderGoodsInfo good : goods) {
				if (i > 0) {
					saleIds.append(",");
				}
				saleIds.append(good.getGoodsId());
				i++;
			}
			info.getExpressInfo().setStatus(ActOrderStatus.EXPRESS_STATUS_RECEIVED);
			client.updateExpressInfo(Long.valueOf(orderId), info.getExpressInfo(), "");
			model.addAttribute("storeId", info.getShopId());

			// 判断用户是否绑定手机号
			if (null != sessionUser && StringUtils.isNotBlank(sessionUser.getTerminalId())) {
				String message = MessageFormat.format(appConfig.getSmsReceiptConfirm(), orderId);
				try {
					jmsMessageService.sendSms(message, sessionUser.getTerminalId());
				}
				catch (Exception e) {
					logger.error("会员确认收货短信发送失败", e);
				}
			} else {
				logger.info("客户确认收货时，用户未绑定手机号");
			}
		}
		catch (Exception e) {
			logger.error("订单用户确认收货", e);
		}
		model.addAttribute("saleIds", saleIds.toString());
		model.addAttribute("orderId", orderId);
		return "center/confirmDelivery";
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param orderId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/center/cancelOrder")
	@ResponseBody
	public Object cancelOrder(HttpServletRequest request, HttpServletResponse response, String orderIdText,
	        String selectValueText,Model model) throws Exception {
		try {
			Long actOrderId = Long.valueOf(orderIdText);
			// 获取当前订单信息
			ActOrderInfo actOrderInfo = orderService2.getOrderInfo(actOrderId);
	
			/**
			 * 验证订单是否有增加退款单权限（防止多个用户同时添加退款单以及对于同一个订单对款次数过多） true 可增加退款申请 false
			 * 不需增加退款申请操作
			 */
			Integer isCanRefund = payOrderService.getOderIsCanRefund(actOrderInfo, TOrderRefund.REFUND_SOURCE_MALL_CANCEL);
	
			if (Constants.ALLOW_REFUND.equals(isCanRefund)) { 
				// 取消订单前操作
				boolean flag = payOrderService.beforeCancelToRefund(actOrderId, response);
				if (!flag) {
					logger.error("申请退款，并且取消失败");
				}
			} else {
				logger.info("只执行取消订单操作");
				closeRequestService.close(actOrderId);
			}
			return true;
  
        }
        catch (Exception e) {
        	logger.error("取消单据异常："+e.getMessage());
        	return null;
        }

	}
	

	/**
	 * 公用取消订单
	 * 
	 * @param request
	 * @param response
	 * @param orderIdText
	 * @param selectValueText
	 * @param url
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/commonOrder")
	@ResponseBody
	public Object commonOrder(HttpServletRequest request, HttpServletResponse response, @RequestParam("orderIdText") String orderIdText,
	        @RequestParam("selectValueText") String selectValueText) {
		try {
			CloseRequest closeRequest = new CloseRequest();
			closeRequest.setActOrderId(Long.valueOf(orderIdText));
			closeRequest.setCloseDescription("用户取消");
			closeRequest.setRollbackInventory(true);
			CloseResponse closeResponse = actBusinessClient.close(closeRequest);
			if (closeResponse.getStatus() != PayResponse.STATUS_OK) {
				logger.error("用户取消订单失败");
			}
			logger.info("common cancel order success!");
			return JsonRespWrapper.success();
		}
		catch (Exception e) {
			logger.error("公共取消订单", e);
			return JsonRespWrapper.failure();
		}
		
	}

	/**
	 * 删除订单
	 * 
	 * @param request
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/delOrder")
	@ResponseBody
	public Object deleteOrder(HttpServletRequest request, HttpServletResponse response, @RequestParam("orderId") String orderId) {
		try {
			SessionUser userinfo = SessionUser.getSessionUser(response);
			try {
				client.deleteActOrder(Long.valueOf(orderId), userinfo.getId(), ActOrderInfo.DELETE_YES);
			}
			catch (Exception e) {
				logger.error("删除订单", e);
			}
			return JsonRespWrapper.success();
		}
		catch (Exception e) {
			logger.error("删除订单异常:" + e.getMessage());
			return JsonRespWrapper.failure();
		}
	}

	/**
	 * 获取我的收藏
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/favGoods")
	public String getMyFavorite(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		pageInfo.setPageRows(12);
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = "";
		if (null != userinfo && null != userinfo.getId()) {
			userId = userinfo.getId().toString();
		}

		model.addAttribute("list", centerService.getFavoriteGoods(userId, pageInfo));
		model.addAttribute("pageInfos", centerService.getFavoriteGoodsScript(pageInfo, userId));

		if (userinfo != null && StringUtils.isNotBlank(userinfo.getAreaCode())) {
			String provRegionCode = shopService.getProvinceCodeByRegionCode(userinfo.getAreaCode(), null);
			if (StringUtils.isNotBlank(provRegionCode) && Constants.PROV_HENAN_REGION_CODE.equals(provRegionCode)) {
				return "center/hn-myfavorite";
			}
		}
		return "center/myfavorite";
	}

	/**
	 * 删除我的收藏
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/delFavorite.chtml", method = RequestMethod.POST)
	public String delFavorite(HttpServletRequest request, HttpServletResponse response, Model model) {
		String favoriteIds = request.getParameter("items");
		String type = request.getParameter("type");
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		centerService.delFavorite(favoriteIds, userId, type);
		if (StringUtils.equals(type, "1")) {
			return "redirect:./favGoods.chtml";
		} else {
			return "redirect:./favStore.chtml";
		}
	}

	/**
	 * 获取我的收藏
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/favStore")
	public String getMyFavoriteStores(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		model.addAttribute("list", centerService.getFavoriteStores(userId, pageInfo));
		model.addAttribute("pageInfos", centerService.getFavoriteStoresScript(pageInfo, userId));

		return "center/favStore";
	}

	/**
	 * G实惠订单，验证是否过期
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("center/checkOutTime")
	@ResponseBody
	public Object checkOutTime(HttpServletRequest req, HttpServletResponse res) {
		String businessId = req.getParameter("businessId");
		String type = req.getParameter("type");

		Date createTime = centerService.getGCheapCreateTime(businessId, type);
		String time = "";
		if (StringUtils.isNotBlank(type)) {
			if (type.equals("1")) {
				time = TimeUtil.addMinutesTim(TimeUtil.formartString(createTime), appConfig.getgCheapAuctionTime());
			} else if (type.equals("2")) {
				time = TimeUtil.addMinutesTim(TimeUtil.formartString(createTime), appConfig.getgCheapSeckillTime());
			}
		}
		boolean flag = false;
		if (TimeUtil.compareTime(TimeUtil.now(), TimeUtil.format(TimeUtil.fromToDate(time), "yyyyMMddHHmmss")) > 0) {
			flag = true;
		}
		Map map = new HashMap();
		map.put("type", "success");
		map.put("flag", flag);
		logger.info("个人中心G实惠类型=" + type + "，businessId=" + businessId + "返回结果是：" + flag);
		return map;
	}

	/**
	 * 营销平台未购买保证金，保证金商品跳转
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/goBuyDeposit")
	public String goBuyDeposit(HttpServletRequest request, HttpServletResponse response, Model model) {
		String itemId = request.getParameter("itemId");
		if (!StringUtils.isEmpty(itemId)) {
			model.addAttribute("itemId", Integer.parseInt(itemId));
		}
		return "center/goBuyDeposit";
	}

	@RequestMapping(value = "/center/openRedDiamond")
	@ResponseBody
	public Object openRedDiamond(HttpServletResponse response) {
		SessionUser user = SessionUser.getSessionUser(response);
		// 增加用户是否绑定手机号判断
		if (StringUtils.isBlank(user.getTerminalId())) {
			return JsonRespWrapper.failure().json("flag", "-1").json("msg", "未绑定移动手机号码，请前往“个人中心>基本资料”进行绑定");
		}
		String result = centerService.requestOpenRedDiamond(user.getTerminalId());
		if (StringUtils.isBlank(result)) {
			return JsonRespWrapper.failure().json("flag", "-1").json("msg", "接口链接异常");
		}

		logger.debug("用户手机号：" + user.getTerminalId() + "================请求开通红钻result=============" + result);

		JSONObject json = JSONObject.fromObject(result);

		return JsonRespWrapper.success("type", "success").json("flag", json.getString("RESULT")).json("msg", json.getString("DESC"));
	}

	/**
	 * 老商城订单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/szMallOrder")
	public String getSZMallOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		List<SzMallOrderHistory> list = centerService.getSZMallOrders(userId, name, pageInfo, startTime, endTime, status);
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("pageInfos", centerService.getSZMallOrdersScript(userId, name, pageInfo, startTime, endTime, status));

		return "center/szMallOrders";
	}

	/**
	 * 我的礼金券
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/cardGifts")
	public String getCardGifts(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));

		SessionUser userinfo = SessionUser.getSessionUser(response);
		String phone = "";
		if (userinfo != null && StringUtils.isNotBlank(userinfo.getTerminalId())) {
			phone = userinfo.getTerminalId();
		}
		List<CardGiftDetails> list = centerService.getCardGifts(phone, pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("pageInfos", centerService.getCardGiftsScript(phone, pageInfo));

		return "center/cardGifts";
	}

	/**
	 * 我的 礼品卡订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/gifts")
	public String getGiftOrders(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		List<TActOrder> list = centerService.getGiftOrders(userId, name, pageInfo, startTime, endTime, status);
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("webRoot", appConfig.getWebRoot());
//		model.addAttribute("unpay", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_PAY), startTime, endTime,name, flag, isdelivery,orderTypes));
//		model.addAttribute("sent", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_RECEIPT), startTime, endTime,name, flag, isdelivery,orderTypes));
		model.addAttribute("pageInfos", centerService.getGiftOrdersScript(userId, name, pageInfo, startTime, endTime, status));
		return "center/giftOrders";
	}

	/**
	 * 商盟融合页面跳转title显示
	 */
	public final static Map<String, String> UNION_MAP = new HashMap<String, String>();
	static {
		UNION_MAP.put("1", "我的团购");
		UNION_MAP.put("2", "我的代金券");
		UNION_MAP.put("3", "我的电影票");
		UNION_MAP.put("4", "我的演出票");
		UNION_MAP.put("5", "我的汽车票");
		UNION_MAP.put("6", "消费过的商户");
		UNION_MAP.put("7", "我的优惠券");
		UNION_MAP.put("8", "我的景点门票");
		UNION_MAP.put("9", "我的订餐");
		UNION_MAP.put("10", "我的点菜");
		UNION_MAP.put("11", "我的外卖");
		UNION_MAP.put("12", "我的榜单");
		UNION_MAP.put("13", "基本资料");
		UNION_MAP.put("14", "账号管理");
		UNION_MAP.put("15", "我的礼金券");
		UNION_MAP.put("16", "我参与的活动");

		UNION_MAP.put("17", "邀请好友");

	}

	/**
	 * 个人中心商盟融合功能
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/center/unions")
	public String goUnionPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		String urlString = request.getParameter("url");
		if (urlString.indexOf(",") > 0) {
			String obj[] = urlString.split(",");
			model.addAttribute("name", obj[0]);
			logger.info("==================="+(obj[0].toString()));
			//页面展示导航部分变色
			if("6".equals(obj[0].toString())){
				model.addAttribute("web_url","消费过的商户");
			}else if("7".equals(obj[0].toString())){
				model.addAttribute("web_url","我的优惠券");
			}else if("9".equals(obj[0].toString())){
				model.addAttribute("web_url","订餐点菜");
			}else if("12".equals(obj[0].toString())){
				model.addAttribute("web_url","我的榜单");
			}else if("16".equals(obj[0].toString())){
				model.addAttribute("web_url","参与的活动");
			}		
			model.addAttribute("chname", UNION_MAP.get(obj[0] + ""));
			model.addAttribute("url", URLDecoder.decode(obj[1]));
		}
		return "center/unions";
	}

	/**
	 * 红包余额查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/center/queryRedPackage")
	public void queryRedPackage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/json; charset=GBK");
		LoginUserBean userinfo = new LoginUserBean();
		PrintWriter out = null;
		userinfo.setId(0L);
		userinfo.setTerminalId("18362637354");
		// 判断该用户手机号是否有红包的查看
		if (userinfo != null && !StringUtils.isBlank(userinfo.getTerminalId())) {
			try {
				QueryWelfareResp welfareResp = welfareOrderService.queryWelfareRequest(userinfo.getTerminalId() + "");
				if (null != welfareResp && welfareResp.getStatusCode() == 0) {
					out = response.getWriter();
					DecimalFormat fnum = new DecimalFormat("##0.00");
					out.print(fnum.format(Float.parseFloat(welfareResp.getAmt() + "") / 100) + "");
					out.flush();
				}
			}
			catch (Exception e) {
				logger.error("调用红包余额接口错误");
				out = response.getWriter();
				out.print("nomoney");
				out.flush();
			}
			finally {
				if (null != out) {
					out.close();
				}
			}
		}
	}

	/**
	 * 我的团购
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/groupBuys")
	public String getGroupBuyOrders(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		LoginUserBean userinfo = null;
		userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		// String userId = "4044133";// userinfo.getId().toString();
		String userId = userinfo.getId().toString();
		List<TActOrder> list = centerService.getGroupBuyOrders(userId, name, pageInfo, startTime, endTime, status, null);
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("pageInfos", centerService.getGroupBuyOrdersScript(userId, name, pageInfo, startTime, endTime, status, null));

		return "center/myGroupBuy";
	}

	/**
	 * 我的代金券页面查询
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/center/goodsOrderList")
	public String getGoodsOrderList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("key"); // 关键字
		String startTime = request.getParameter("startTime");// 查询开始时间;yyyyMMdd
		String endTime = request.getParameter("endTime"); // 查询结束时间
		// 如果startTime和endTime没有值，就设置检索今日前一个月
		if (StringUtils.isBlank(startTime)) {
			Calendar calendar = Calendar.getInstance();// 日历对象
			calendar.setTime(new Date());// 设置当前日期
			calendar.add(Calendar.MONTH, -1);// 月份减一
			startTime = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		}
		if (StringUtils.isBlank(endTime)) {
			endTime = new SimpleDateFormat("yyyyMMdd").format(new Date()); // 今天
		}
		String source = request.getParameter("djq_source_flag"); // 数据开源：新商城，商盟或者联动
		// 初始页面source为空,(1,2,3)
		if (StringUtils.isBlank(source)) {
			source = "0";
		}
		if (!"1".equals(source) && !"2".equals(source) && !"3".equals(source)) {
			source = "0";
		}
		String status = request.getParameter("status"); // 订单状态：待付款，已付款，待收货，已完成，已取消
		String flag = request.getParameter("flag");
		SessionUser userinfo = SessionUser.getSessionUser(response); // 本地测试需要注释掉
		if (userinfo == null) {
			logger.error("没有userInfo信息");
			model.addAttribute("list", null);
			model.addAttribute("status", status);
			model.addAttribute("source", source);
			model.addAttribute("webRoot", appConfig.getWebRoot());
			return "center/goodsOrderList";
		}
		String userId = userinfo.getId().toString();
		List<TActOrder> listAll = new ArrayList<TActOrder>(); // 所有数据来源

		/**
		 * 处理商城数据 source = 0(全部） 1 （商城） userId, name, startTime, endTime, status
		 */
		if ("0".equals(source) || "1".equals(source)) {
			String userIdLife = userId; // 可以hardcode进行测试
			String nameLife = name;
			String startTimeLife = startTime;
			String endTimeLife = endTime;
			String statusLife = null;// 为空，不做处理
			try {
				List<TActOrder> listLift = centerService.getGoodsOrders(userIdLife, nameLife, startTimeLife, endTimeLife, statusLife);
				if (listLift != null && listLift.size() > 0) {
					listAll.addAll(listLift);
				}
			}
			catch (Exception e) {
				logger.error("查询商城数据出错,userId=" + userIdLife);
				logger.error(e.getMessage());
			}
		}

		/**
		 * 处理商盟数据 source = 0(全部） 2 （商盟）
		 */
		if ("0".equals(source) || "2".equals(source)) {
			String mobile = userinfo.getTerminalId(); // 可以hardcode进行测试
			// String mobile = "13771897717";
			String name777 = name;
			String startTime777 = startTime;
			String endTime777 = endTime;
			String status777 = null;// 为空，不做处理
			try {
				List<TActOrder> list777 = centerService.getGoodsOrders777(mobile, name777, startTime777, endTime777, status777);
				if (list777 != null && list777.size() > 0) {
					listAll.addAll(list777);
				}
			}
			catch (Exception e) {
				logger.error("查询商盟数据出错,mobile=" + mobile);
				logger.error(e.getMessage());
			}
		}

		/**
		 * 处理联动数据 source = 0(全部） 3 （联动）
		 */
		if ("0".equals(source) || "3".equals(source)) {
			String mobileNO = userinfo.getTerminalId();
			// String mobileNO = "13771897717"; // 联动测试数据 - 姜微微
			if (StringUtils.isBlank(mobileNO)) {
				logger.error("查询联动数据出错,mobileNO为空");
			} else {
				String startTimeUMP = startTime;
				String endTimeUMP = endTime;
				String nameUMP = name;
				String payStatusUMP = "99";
				String orderStatusUMP = "99";
				String orderTypeUMP = "99";
				try {
					List<TActOrder> listUMP = umpInterfaceService.getOrders(mobileNO, startTimeUMP, endTimeUMP, payStatusUMP, orderStatusUMP,
					                                                        orderTypeUMP, nameUMP);
					if (listUMP != null && listUMP.size() > 0) {
						listAll.addAll(listUMP);
					}
				}
				catch (Exception e) {
					logger.error("查询联动数据出错,mobileNO=" + mobileNO);
					logger.error(e.getMessage());
				}
			}
		}
		/**
		 * 返回参数对订单列表排序
		 */
		sortClass sort = new sortClass();
		Collections.sort(listAll, sort);
		model.addAttribute("list", listAll);
		model.addAttribute("status", status);
		model.addAttribute("source", source);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("goodsType", "代金券");
		String select_flag=request.getParameter("select_flag");
		select_flag=StringUtil.isEmpty(select_flag)?"0":select_flag;
		model.addAttribute("select_flag",select_flag);
		model.addAttribute("djq_source_flag",
		                   StringUtil.isEmpty(request.getParameter("djq_source_flag")) ? "0" : request.getParameter("djq_source_flag"));
		if (listAll != null && listAll.size() > 0) {
			model.addAttribute("size", listAll.size());
		}
		if (startTime.length() > 8) {
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
		} else {
			model.addAttribute("startTime", startTime.substring(0, 4) + "-" + startTime.substring(4, 6) + "-" + startTime.substring(6, 8));
			model.addAttribute("endTime", endTime.substring(0, 4) + "-" + endTime.substring(4, 6) + "-" + endTime.substring(6, 8));
		}

		return "center/myorders";
	}

	/**
	 * 我的演出票
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/getPerformOrders")
	public String getPerformOrders(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		List<TActOrder> list = centerService.getPerformOrders(userId, name, pageInfo, startTime, endTime, status);
		model.addAttribute("list", list);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("pageInfos", centerService.getPerformOrdersScript(userId, name, pageInfo, startTime, endTime, status));

		return "center/myPerform";
	}


	/**
	 * 标题、简要说明. <br>
	 * 集合内部排序
	 * <p>
	 * Copyright: Copyright (c) Jan 14, 2014 10:56:15 AM
	 * <p>
	 * Company: 宽连信息(苏州)技术有限公司
	 * <p>
	 * 
	 * @author wangtaob@c-platform.com
	 * @version 1.0.0
	 */
	@SuppressWarnings("rawtypes")
	class sortClass implements Comparator {

		@Override
		public int compare(Object arg0, Object arg1) {
			TActOrder order0 = (TActOrder) arg0;
			TActOrder order1 = (TActOrder) arg1;
			int flag = order1.getCreateTime().compareTo(order0.getCreateTime());
			return flag;
		}
	}

	/**
	 * 商盟代金券订单补发
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/sendSMSCode")
	@ResponseBody
	public Object sendSMSCode(HttpServletRequest request, HttpServletResponse response, Model model) {

		String msg = "手机短信发送失败，错误码：500";

		String orderId = request.getParameter("orderId");
		String mobile = request.getParameter("mobile");
		String good_id = request.getParameter("id");
		// 根据o2o订单号获取验证码信息
		List<String[]> list = centerService.getVerifyInfo(orderId, 1);
		if (list != null && list.size() > 0) {
			String[] str = list.get(0);
			if ("founder".equals(str[6])) {// 方正码
				String[] sendlog = centerService.getSendLog(orderId, "");
				if (sendlog != null && Integer.parseInt(sendlog[0]) >= 3) {
					msg = "短信补发最多3次";

					return msg;
				}
				if (sendlog == null) {// 补发日志为空，插一条数据，补发成功后补发次数加1
					centerService.inserSendLog(orderId, good_id, 4044133l, mobile, "");
				}
				VerifyServiceClient client = VerifyServiceClient.getInstance(appConfig.getResendAddress());
				CodeType codeType = CodeType.OrderNo;// 根据订单号发码
				ResendRequest resendRequest = new ResendRequest();
				resendRequest.setCodeType(codeType);
				resendRequest.setCode(str[0].substring(0, str[0].length() - 2));

				try {
					ResendResponse resendResponse = client.resend(resendRequest);
					if (ResendResponse.STATUS_CODE_OK == resendResponse.getStatusCode()) {// 调用方正码接口成功
						centerService.updateSendLog(orderId, response.toString(), "");// 记录补发次数
						msg = "手机短信发送成功，请及时查收";
					} else {
						msg = "验证码补发失败，可能由于已使用、已过期";
					}
				}
				catch (Exception e) {
					logger.error("商盟方正码补发异常", e);
				}

			} else {
				for (int i = 0; i < list.size(); i++) {
					String[] code = list.get(i);
					String[] sendlog = centerService.getSendLog(orderId, code[0]);
					if (sendlog != null && Integer.parseInt(sendlog[0]) >= 3) {
						msg = "短信补发最多3次";

						return msg;
					}
					if (sendlog == null) {// 补发日志为空，插一条数据，补发成功后补发次数加1
						centerService.inserSendLog(orderId, good_id, 4044133l, mobile, code[0]);
					}
					msg = centerService.resendCode(code[0], mobile, orderId, good_id, code[7]);

				}
			}
			return msg;
		} else {
			msg = "补发失败，验证码可能已使用或已过期，详情请拨打12580";
			return msg;
		}

	}

	/**
	 * 个人中心我的mo生活
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/orderManager")
	public String getNewOrders(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String select_flag=request.getParameter("select_flag");
			select_flag=StringUtil.isEmpty(select_flag)?"0":select_flag;
			model.addAttribute("date_flag",request.getParameter("date_flag"));
			//跳转到竞拍秒杀页面
			if("3".equals(select_flag)||"4".equals(select_flag)){
				return "forward:./gCheapOrder.chtml";
			}
			//跳转到代金券
			if("6".equals(select_flag)){
				return "forward:./goodsOrderList.chtml";
			}
			SessionUser userinfo = SessionUser.getSessionUser(response);
			// 判断是否河南商品
			if (userinfo != null && StringUtils.isNotBlank(userinfo.getAreaCode())) {
				String provRegionCode = shopService.getProvinceCodeByRegionCode(userinfo.getAreaCode(), null);
				if (StringUtils.isNotBlank(provRegionCode) && Constants.PROV_HENAN_REGION_CODE.equals(provRegionCode)) {
					return memberHnCenterController.getHnOrders(request, response, model);
				}
			}
			String userId = userinfo.getId().toString();
			String curpage = request.getParameter("curpage");
			if (StringUtils.isEmpty(curpage)) {
				curpage = "1";
			}
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(Integer.valueOf(curpage));
			String name = request.getParameter("key");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String status = request.getParameter("status");
			String isdelivery = request.getParameter("isdelivery");
			String flag = request.getParameter("flag");
			String orderTypesStr=StringUtil.getString(request.getParameter("orderTypes"));
			Long[] orderTypes=ArrayUtil.toLongArr(StringUtil.isEmpty(orderTypesStr)?null:orderTypesStr.split(","));
			
			List<TActOrderNew> orderlist = centerService.getNewOrders(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,orderTypes);
			model.addAttribute("orderlist", orderlist);
			model.addAttribute("status", status);
			model.addAttribute("flag",flag);
			model.addAttribute("webRoot", appConfig.getWebRoot());
			model.addAttribute("unpay", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_PAY), startTime, endTime,name, flag, isdelivery,orderTypes));
			model.addAttribute("sent", centerService.countOrdersByStatus(userId,String.valueOf(OrderConstant.UN_RECEIPT), startTime, endTime,name, flag, isdelivery,orderTypes));
			model.addAttribute("delivery", centerService.getOrdersByDelivery(userId, "1"));
			model.addAttribute("isdelivery", isdelivery);
			model.addAttribute("pageInfos", centerService.getNewOrdersScript(userId, name, pageInfo, startTime, endTime, status, flag, isdelivery,orderTypes));
			model.addAttribute("select_flag",select_flag);
			model.addAttribute("groupbuy_source_flag",
			                   StringUtil.isEmpty(request.getParameter("groupbuy_source_flag")) ? "0" : request.getParameter("groupbuy_source_flag"));
			model.addAttribute("userName",userinfo.getNickName());
			

		}
		catch (Exception ex) {
			logger.error("个人中心查询报错", ex);
			return "center/error/error";
		}
		return "center/myorders";
	}

	/**
	 * 跳转退款页面
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/center/applyRefund", method = RequestMethod.GET)
	@ResponseBody
	public Object checkApplyRefund(@RequestParam("orderId") String orderId, HttpServletResponse response) throws Exception {
		if (!org.apache.commons.lang3.StringUtils.isNotBlank(orderId)) {
			logger.error("订单号不能为空：" + orderId + " (订单编号");
			return JsonRespWrapper.failure(Constants.APPLY_REFUND_NO_ORDER);
		}
		Long actOrderId = Long.parseLong(orderId);
		// 获取当前订单信息
		ActOrderInfo actOrderInfo = orderService2.getOrderInfo(actOrderId);

		// 判断当前订单是否该登录用户所有
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		boolean order_user_check = orderService2.isCurrentUserOrder(actOrderInfo.getUserId(), sessionUser.getId());
		if (!order_user_check) {
			logger.error("订单编号不是该用户所有：" + "订单号：" + actOrderId + "----- 当前用户：" + sessionUser.getId());
			return JsonRespWrapper.failure(Constants.APPLY_REFUND_NOT_USER);
		}
		return JsonRespWrapper.success().json("url", "/center/apply_refund.chtml?orderId=" + actOrderId);
	}

	@RequestMapping("/center/apply_refund")
	public String toApplyRefund(@RequestParam("orderId") String orderId, HttpServletResponse response, Model model) throws Exception {

		Long actOrderId = Long.parseLong(orderId);
		// 获取当前订单信息
		ActOrderInfo actOrderInfo = orderService2.getOrderInfo(actOrderId);
		model.addAttribute("actOrderInfo", actOrderInfo);

		// 查询订单支付信息
		List<PayOrderInfo> payOrderInfos = payServiceClient.getPayOrderInfosByActOrderId(actOrderId);

		// 设置待退款商品，返回商品类型，0实物，1虚拟物
		Map<String, Object> mapPayMent = payOrderService.getPayMent(payOrderInfos, actOrderInfo);

		model.addAttribute("mapPayMent", mapPayMent);

		// 获取订单退款类型
		Long refundType = payOrderService.setRefundType(actOrderInfo);
		model.addAttribute("refundType", refundType);

		Long itemMode = null;
		if (null != mapPayMent && !mapPayMent.isEmpty()) {
			itemMode = (Long) mapPayMent.get("itemMode");
		}

		if (null != itemMode && itemMode == 1L) {
			// 虚拟商品
			if (null != refundType && refundType == 2L) {// 拉手退款单
				return "order/order_refund_add_virtual_lashou";
			} else if (StringUtils.isNotBlank(String.valueOf(refundType)) && Long.valueOf(TOrderRefund.REFUND_TYPE_4).equals(refundType)) {// 河南积分
				return "center/order_refund_add_virtual_henan";
			} else {// 其他商品-虚拟
				return "center/order_refund_add_virtual";
			}
		}
		// 实物商品
		return "center/order_refund_add";
	}

	/**
	 * 处理订单退款业务
	 * 
	 * @param tOrderRefund
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/center/applyRefund", method = RequestMethod.POST)
	@ResponseBody
	public Object doApplyRefund(@CookieValue("backLeftHref") String backLeftHref, TOrderRefund tOrderRefund, String refundStrategy,
	        HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == tOrderRefund.getCashFee() && null == tOrderRefund.getCoinFee() && null == tOrderRefund.getScoreFee()
		        && null == tOrderRefund.getPhoneFee() && null == tOrderRefund.getRedpackageFee()) {
			return JsonRespWrapper.failure().json("msg", "退款单总金额不能为0");
		}

		// 获取订单详情
		ActOrderInfo actOrderInfo = orderService2.getOrderInfo(tOrderRefund.getOrderId());

		// 验证订单是否有增加退款单权限（防止多个用户同时添加退款单以及对于同一个订单对款次数过多）
		Integer isCanRefund = payOrderService.getOderIsCanRefund(actOrderInfo, TOrderRefund.REFUND_SOURCE_MALL_APPLY);

		if (isCanRefund == Constants.UNALLOW_REFUND_HAVA) {
			return JsonRespWrapper.failure().json("msg", "请联系客服，申请再次退款。");
		} else if (isCanRefund == Constants.UNALLOW_REFUND_UNPAY) {
			return JsonRespWrapper.failure().json("msg", "未付款，不能申请退款。");
		}

		SessionUser sessionUser = SessionUser.getSessionUser(response);
		tOrderRefund.setCreateTime(TimeStamp.getTime(14));
		tOrderRefund.setUserId(Integer.parseInt(sessionUser.getId().toString()));

		// 获取参数
		Long itemMode = Long.valueOf(request.getParameter("itemMode"));
		String[] orderRefundGoodsInfos = request.getParameterValues("orderRefundGoods");

		logger.info("申请退款-获取订单退款状态");
		logger.info("申请退款-itemMode：" + itemMode);

		// 前台提交退款单，添加验证
		Map<String, Object> refundMap = payOrderService.getRefundPayAount(actOrderInfo);
		boolean checkRefundAmount = payOrderService.isCheckRefundAmount(refundMap, tOrderRefund, itemMode);
		if (!checkRefundAmount) {
			return JsonRespWrapper.failure().json("msg", "退款金额有误");
		}

		// 设置退款单状态
		payOrderService.setOrderRefundStatus(tOrderRefund, itemMode, request);
		logger.info("申请退款-获取订单退款状态");

		// 设置各币种退款金额，金额设置为“分”单位
		logger.info("申请退款-设置货比支付");
		payOrderService.setPayFee(tOrderRefund);

		try {
			// 返回来源地址
			String backUrl = "/center/" + backLeftHref;
			// 虚拟商品要先退码，然后保存退款单
			if (itemMode == 1L) {
				Long successCode = 0L;
				String[] orderIds = request.getParameterValues("actOrderGoodsCheck");

				if (tOrderRefund.getRefundType() == TOrderRefund.REFUND_TYPE_4) {// 河南积分退码
					// 发送退码请求
					thirdInterDao.refundVerifyCodeHN(tOrderRefund.getOrderId());
				} else if (tOrderRefund.getRefundType() == 2L) {// 拉手订单退码
					List<CodeInfo> codeInfos = codeInfoService.listOrderCodesByActOrderId(tOrderRefund.getOrderId());
					if (codeInfos != null && codeInfos.size() > 0) {
						if (StringUtils.isNotBlank(refundStrategy)) {
							return JsonRespWrapper.failure().json("msg", "商品不支持退款！");
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
							tOrderRefund = tOrderRefundGoodsService.saveOrderRefund(tOrderRefund, TOrderRefund.REFUND_SOURCE_MALL_APPLY,
							                                                        orderRefundGoodsInfos);
							logger.info("订单管理：增加退款单,退款单ID：" + tOrderRefund.getId());
							return JsonRespWrapper.redirectSuccess("退款单添加成功", backUrl);
						} else {// 非卡密商品，需要退码
							List<CodeInfo> codeInfos = codeInfoService.listValidCodesByActOrderId(tOrderRefund.getOrderId());// 查询订单中是否存在有效码
							if (null != codeInfos && codeInfos.size() > 0) {
								if (null == orderIds) {
									return JsonRespWrapper.failure().json("msg", "请选择退款商品码！");
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
				tOrderRefund = tOrderRefundGoodsService.saveOrderRefund(tOrderRefund, TOrderRefund.REFUND_SOURCE_MALL_APPLY, orderRefundGoodsInfos);
				logger.info("订单管理：增加退款单,退款单ID：" + tOrderRefund.getId());
				if (tOrderRefund.getRefundType() == 2L || tOrderRefund.getRefundType() == 4L) {
					return JsonRespWrapper.redirectSuccess("退款单添加成功", backUrl);
				}
				return JsonRespWrapper.redirectSuccess("退款单添加成功,退码成功" + successCode + "个,失败" + (tOrderRefund.getTotalCode() - successCode) + "个",
				                                       backUrl);
			} else {// 实物商品退款
				tOrderRefund = tOrderRefundGoodsService.saveOrderRefund(tOrderRefund, TOrderRefund.REFUND_SOURCE_MALL_APPLY, orderRefundGoodsInfos);
				logger.info("订单管理：增加退款单,退款单ID：" + tOrderRefund.getId());
				return JsonRespWrapper.redirectSuccess("添加成功", backUrl);
			}

		}
		catch (Exception ex) {
			logger.error(ex);
			return JsonRespWrapper.failure("退款异常，请重新再试！");
		}
	}

	/**
	 * 获取商盟代金券订单验证码详情信息
	 * 
	 * @param request
	 * @param response
	 * @param orderIdText
	 * @param selectValueText
	 * @param url
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/verifyCodeDetail", method = RequestMethod.GET)
	public String smO2OOrderCode(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String orderId = request.getParameter("orderId");
			String goodId = request.getParameter("goodId");
			String goodName = request.getParameter("goodName");
			goodName = URLDecoder.decode(goodName, "utf-8");
			List<String[]> arrList = centerService.getVerifyCodeDetail(orderId);
			boolean flag = centerService.isTpVerifxyCodeGood(goodId);
			model.addAttribute("arrList", arrList);
			model.addAttribute("flag", flag);
			model.addAttribute("goodName", goodName);
		}
		catch (Exception e) {
			logger.error("获取商盟代金券订单验证码详情信息异常", e);
		}

		return "center/verifyCodeDetail";
	}

	/**
	 * 我的电影票
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/getMovieOrders")
	public String getMovieOrders(HttpServletRequest request, HttpServletResponse response, Model model) {
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		String name = request.getParameter("key");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userId = userinfo.getId().toString();
		List<TActOrder> list = centerService.getGroupBuyOrders(userId, name, pageInfo, startTime, endTime, status, "movie");
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("webRoot", appConfig.getWebRoot());
		model.addAttribute("pageInfos", centerService.getGroupBuyOrdersScript(userId, name, pageInfo, startTime, endTime, status, "movie"));

		return "center/myMovies";
	}

	/**
	 * 我的机票订单
	 * 
	 * @return
	 */
	@RequestMapping("/center/airTicketOrder")
	public String airTicketOrder() {
		return "center/airline";
	}

	/**
	 * 我的酒店订单
	 * 
	 * @return
	 */
	@RequestMapping("/center/wineshopOrder")
	public String wineshopOrder() {
		return "center/wineshop";
	}

	/**
	 * 我的中奖信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException
	 *             日期转换异常
	 */
	@RequestMapping(value = "/center/lotteryInfo")
	public String getLotteryList(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
		// 设置curpage类初始化
		String curpage = request.getParameter("curpage");
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		// 获取当前登陆的用户信息
		SessionUser userinfo = SessionUser.getSessionUser(response);
		String userTel = StringUtil.getString(userinfo.getTerminalId());
		// 判定用户是否保存手机号
		if (!StringUtil.isEmpty(userTel)) {
			List<ItemLottery> list = centerService.getLotteryList(pageInfo, userTel);
			if (list.size() == 0) {// 没有参加抽奖的页面显示
				model.addAttribute("msg", "对不起,没有获取到您的相关中奖信息!");
			} else {
				model.addAttribute("pageInfos", centerService.getLotteryScript(pageInfo, userTel));
				model.addAttribute("list", list);
			}
		} else {// 手机号不存在时候返回页面的提示信息
			model.addAttribute("msg", "对不起,没有您的相关中奖信息,请确认您是否绑定手机号并参与抽奖");
		}
		return "center/lottery_info";
	}
	
	/**
	 * 我的旅行
	 * @param request
	 * @param response
	 * @param model
	 * @param travel_flag 页面要变色的按钮  1-汽车票 2-景点门票 3-机票 4-酒店
	 * @return
	 */
	@RequestMapping(value = "/center/getMyTravel")
	public String getMyTravel(HttpServletRequest request, HttpServletResponse response, Model model,
			 @RequestParam(value = "travel_flag", required = false, defaultValue = "1") String travel_flag){
		model.addAttribute("webRoot", appConfig.getWebRoot());
		if(Integer.valueOf(travel_flag)<=0||Integer.valueOf(travel_flag)>4){
			travel_flag = "1";
		}
		model.addAttribute("travel_flag", travel_flag);
		return "center/myTravel";
	}
	
	/**
	 * 获取我的票务
	 * @param request
	 * @param response
	 * @param model
	 * @param curpage      当前页码
	 * @param select_flag  选中的信息  7-我的电影票  8-我的演出票  9-我的彩票  10-全部
	 * @param select_status 选中的状态信息   
	 * @param select_time  时间检索条件
	 * @param isdelivery   
	 * @param flag
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/center/getMyTicket")
	public String getMyTicket(HttpServletRequest request, HttpServletResponse response, Model model,
			 @RequestParam(value = "curpage", required = false, defaultValue = "1") int  curpage, //分页信息
			 @RequestParam(value = "select_flag", required = false, defaultValue = "10") int  select_flag,
			 @RequestParam(value = "select_status", required = false, defaultValue = "") String  select_status,
			 @RequestParam(value = "select_time", required = false, defaultValue = "") String  select_time,
			 @RequestParam(value = "isdelivery", required = false, defaultValue = "") String  isdelivery,
			 @RequestParam(value = "flag", required = false, defaultValue = "") String  flag,
			 @RequestParam(value = "key", required = false, defaultValue = "") String  key
			){
		try{
			//获取当前时间
			Calendar calendar  = Calendar.getInstance();
			//格式化要做验证的时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			//格式化要在页面显示的时间
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			//获取当前用户的登录信息
			SessionUser userinfo = SessionUser.getSessionUser(response);
			String userId = userinfo.getId().toString();
			//设置分页信息
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(curpage);
			//设置查询类型集合 orderType
			String orderTypesStr=null;
			//开始时间和结束时间
			String startTime = "";
			String endTime = "";	
			//对票务类型的判断
			if(select_flag==7){
				orderTypesStr = "40";
			}else if(select_flag==8){
				orderTypesStr = "30,31";
			}else if(select_flag==10){
				orderTypesStr = "30,31,40";
			}	
			//对时间的判断
			if(StringUtils.isNotEmpty(select_time)){
				if("3".equals(select_time)){  //调用了时间控件的
					endTime = request.getParameter("endTime");
					startTime = request.getParameter("startTime");
					model.addAttribute("startTime",request.getParameter("inputStartTime"));
					model.addAttribute("endTime",request.getParameter("inputEndTime"));
				}else{               //未调用时间空间的
					model.addAttribute("endTime",sdf1.format(calendar.getTime()));
					endTime = sdf.format(calendar.getTime());//设置结束时间
					if("0".equals(select_time)){   //3天以内
						calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-2);
					}else if("1".equals(select_time)){   //一周以内
						calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-6);
					}else if("2".equals(select_time)){   //三个月以内
						calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-3);
					}
					model.addAttribute("startTime",sdf1.format(calendar.getTime()));
					startTime = sdf.format(calendar.getTime()); //设置开始时间
				}	
			}
			Long[] orderTypes=ArrayUtil.toLongArr(StringUtil.isEmpty(orderTypesStr)?null:orderTypesStr.split(","));
			List<TActOrderNew> orderlist = centerService.getNewOrders(userId, key, pageInfo, startTime, endTime, select_status, flag, isdelivery,orderTypes);
			model.addAttribute("curpage",curpage);
			model.addAttribute("orderlist", orderlist);
			model.addAttribute("webRoot", appConfig.getWebRoot());
			model.addAttribute("unpay", centerService.countOrdersByStatus(userId,"1",startTime,endTime, key,flag,isdelivery,orderTypes));
			model.addAttribute("sent", centerService.countOrdersByStatus(userId,"3",startTime,endTime, key,flag,isdelivery,orderTypes));
			//model.addAttribute("delivery", centerService.getOrdersByDelivery(userId, "1"));
			model.addAttribute("isdelivery", isdelivery);
			model.addAttribute("pageInfos", centerService.getNewOrdersScript(userId, key, pageInfo, startTime, endTime, select_status, flag, isdelivery,orderTypes));
			model.addAttribute("select_flag",select_flag);
			model.addAttribute("select_status",select_status);
			model.addAttribute("select_time",select_time);
		}
		catch (Exception ex) {
			logger.error("个人中心查询报错", ex); 
			return "center/error/error";
		}
		return "center/myTicket";
	}
	
	@RequestMapping(value = "/center/logUserCenterVisit")
	@ResponseBody
	public Object logUserCenterVisit(HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			logger.info("ready to log user visit log");
			SessionUser userinfo = SessionUser.getSessionUser(response);
			Long userId = userinfo.getId();
			return centerService.logUserCenterVisit(userId);
        }
        catch (Exception e) {
        	logger.error("获取用户向导记录异常！"+e.getMessage());
        	return false;
        }
		
	}
	
	
}
