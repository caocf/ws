package com.cplatform.b2c.web;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cplatform.b2c.model.OrderRefund;
import com.cplatform.b2c.service.OrderRefundService;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

@Controller
public class OrderRefundController {

	@Autowired
	OrderRefundService orderRefundService;

	private final Logger logger = Logger.getLogger(OrderRefundController.class);

	/**
	 * 退款订单列表
	 * 
	 * @param orderRefund
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/center/refundList", method = RequestMethod.GET)
	public String orderRefundQuery(OrderRefund orderRefund, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	        Model model, HttpServletRequest request, HttpServletResponse response) {

		try {
			LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
			// LoginUserBean userinfo = new LoginUserBean();
			// userinfo.setId(151020773L);
			Long userId = null;
			if (userinfo != null) {
				userId = userinfo.getId();
			}
			ListPage<Map<String, Object>> pageData = orderRefundService.getRefundList(orderRefund, page, 10, userId);
			for (Map<String, Object> map : pageData.getData()) {
				BigDecimal fee = (BigDecimal) map.get("REFUND_FEE");
				map.put("REFUND_FEE", CommonUtils.toYuan(fee));
			}

			model.addAttribute("pageData", pageData);
		}
		catch (Exception e) {
			logger.error("进入退款订单列表页面出错:" + e.getMessage());
			e.printStackTrace();
		}
		return "center/orderRefund";
	}

}
