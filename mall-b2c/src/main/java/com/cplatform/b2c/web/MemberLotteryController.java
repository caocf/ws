package com.cplatform.b2c.web;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.b2c.dto.LotteryDTO;
import com.cplatform.b2c.model.LotteryBean;
import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CTime;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.ThreeDES;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * 彩票. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-8-28 下午03:53:54
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Controller
public class MemberLotteryController {

	private final Logger logger = Logger.getLogger(MemberLotteryController.class);

	/**
	 * 彩票列表每页记录数
	 */
	private static Integer pageRows = 20;

	@Autowired
	private AppConfig appConfig;

	/**
	 * 我的订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/lottery")
	public String getOrders(HttpServletRequest request, HttpServletResponse response,
	        @RequestParam(value = "curpage", required = false, defaultValue = "1") String curpage, Model model) {
		if (StringUtils.isEmpty(curpage)) {
			curpage = "1";
		}
		model.addAttribute("select_flag", "9");
		LoginUserBean userInfo = null;
		userInfo = new SSOAgent(request, response).loginUserInfo("mall");
		String terminalId = userInfo.getTerminalId();
		StringBuffer paramBuf = new StringBuffer();
		paramBuf.append("SRC=");
		paramBuf.append(appConfig.getLotteryOrderSrc());
		paramBuf.append("&MOBILE=");
		paramBuf.append(terminalId);
		paramBuf.append("&STATUS=");
		paramBuf.append("all");
		paramBuf.append("&PAGE_NO=");
		paramBuf.append(curpage);
		paramBuf.append("&PAGE_SIZE=");
		paramBuf.append(this.pageRows);

		logger.debug("[彩票订单查询]" + appConfig.getLotteryOrderUrl());
		logger.debug("[彩票订单查询]" + paramBuf.toString());

		Integer totalCount = 0;
		LotteryDTO retObject;
		try {
			String ret = HttpClientUtils.httpGet(appConfig.getLotteryOrderUrl(), paramBuf.toString(), 0, 0);
			JSONObject jsonObject = JSONObject.fromObject(ret);

			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("DATA", LotteryBean.class);

			retObject = (LotteryDTO) JSONObject.toBean(jsonObject, LotteryDTO.class, classMap);
			totalCount = retObject.getTOTAL_ROW();

			PageInfo pageInfo = new PageInfo(Integer.valueOf(curpage), totalCount, this.pageRows);
			model.addAttribute("pageInfos", pageInfo.getScript());
			model.addAttribute("list", retObject.getDATA());
			model.addAttribute("webroot", appConfig.getWebRoot());
		}
		catch (Exception e) {
			logger.error("[彩票订单查询]" + e.getMessage());
		}
		return "center/myTicket";
	}

	/**
	 * 获取订单详情
	 * @param request
	 * @param response
	 * @param orderId 彩票订单Id
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/center/lottery_detail")
	public String getOrderDetail(HttpServletRequest request, HttpServletResponse response,
	        @RequestParam(value = "orderId", required = true) String orderId, Model model) {

		StringBuffer paramBuf = new StringBuffer();
		paramBuf.append("SRC=");
		paramBuf.append(appConfig.getLotteryOrderSrc());
		paramBuf.append("&ORDERNO=");
		paramBuf.append(orderId);

		logger.debug("[彩票订单明细]" + appConfig.getLotteryOrderUrl());
		logger.debug("[彩票订单明细]" + paramBuf.toString());

		/**
		 * <?xml version='1.0' encoding='UTF-8'?><ROOT> <ORDERNO>订单编号</ORDERNO>
		 * <CREATEDATE>创建时间</CREATEDATE> <MOBILENO>手机号码</MOBILENO>
		 * <TICKETINFO>票ID@期号@彩种@票信息@单票注数@单票金额@票状态@出票时间^订单编号</ORDERNO>
		 * <CREATEDATE>创建时间</CREATEDATE> <MOBILENO>手机号码</MOBILENO>
		 * <TICKETINFO>票ID@期号@彩种@票信息@单票注数@单票金额@票状态@出票时间@中奖金额</TICKETINFO>
		 * </ROOT>
		 */
		try {
			String ret = HttpClientUtils.httpGet(appConfig.getLotteryOrderDetailUrl(), paramBuf.toString(), 0, 0);
			JSONObject retObj = JSONObject.fromObject(ret);
			if (retObj.get("FLAG") != null && "0".equals(retObj.get("FLAG").toString())) {
				String data = ThreeDES.decryptThreeDESECB(retObj.get("DATA").toString(), "00n70egomeng0g66ygzp39vl");
				logger.debug("[彩票订单明细]" + data);
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(new StringReader(data));

				Element root = doc.getRootElement();

				List<Element> ticketList = root.getChildren("TICKETINFO");
				List<String[]> infoList = new ArrayList<String[]>();
				String[] ticketStrings = StringUtils.split(ticketList.get(0).getText(), "^");
				if (ticketStrings != null && ticketStrings.length > 0) {
					for (int j = 0; j < ticketStrings.length; j++) {
						infoList.add(ticketStrings[j].split("@"));
					}
				}
				model.addAttribute("orderNo", root.getChildText("ORDERNO"));
				model.addAttribute("createTime", CTime.transDate(root.getChildText("CREATEDATE")));
				model.addAttribute("infoList", infoList);
			}

		}
		catch (JDOMException e) {
			logger.error("[彩票订单明细]解析错误" + e.getMessage());
		}
		catch (Exception e) {
			logger.error("[彩票订单明细]" + e.getMessage());
		}
		return "center/lottery_detail";
	}
}
