package com.cplatform.b2c.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.cache.CachedObjectType;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.service.ItemSaleService;
import com.cplatform.b2c.service.JmsMessageService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.TMemberService;
import com.cplatform.b2c.service.VerifyCodeService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.soa.muc.SoaMemberService;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * 绑定手机号. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-4-8 下午2:53:25
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author luodw@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/bindMobile")
public class BindMobileController {

	private final Logger logger = Logger.getLogger(BindMobileController.class);

	@Autowired
	private TMemberService tMemberService;

	@Autowired
	private VerifyCodeService verifyCodeService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private JmsMessageService jmsMessageService;

	@Autowired
	private SoaMemberService soaMemberService;

	@Autowired
	private ItemSaleService itemSaleService;

	@Autowired
	private OrderService orderService;

	/**
	 * 判断是否绑定手机号
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/isBind", method = RequestMethod.GET)
	@ResponseBody
	public String isBindMobile(HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (null == userinfo) {
			// 未登录
			return jsonpResponse(JsonRespWrapper.success("flag", "0"));
		}
		if (StringUtils.isNotBlank(userinfo.getTerminalId())) {
			// 用户已经绑定手机号 跳过绑定界面
			return jsonpResponse(JsonRespWrapper.success("flag", "1"));
		}
		try {
			String pdtId = request.getParameter("pdtId");
			if (StringUtils.isNotBlank(pdtId)) {
				ItemSaleDataDTO itemSaleInfo = itemSaleService.getItemSaleById(pdtId);
				if (null == itemSaleInfo || null == itemSaleInfo.getItem()) {
					// 报错信息
					List<String> keys = new ArrayList<String>();
					List<Object> msgs = new ArrayList<Object>();
					keys.add("flag");
					msgs.add("3");
					keys.add("msg");
					msgs.add(URLEncoder.encode("没有找到相关商品信息", "UTF-8"));
					return jsonpResponse(JsonRespWrapper.failure(keys, msgs));
				}
				int item_mode = itemSaleInfo.getItem().getItemMode();
				// 绑定虚拟商品
				if (item_mode == 1) {
					// 弹出绑定
					return jsonpResponse(JsonRespWrapper.success("flag", "2"));
				} else if (item_mode == 0) {// 实物商品
					boolean isVipGood = orderService.isVipGoods(itemSaleInfo);
					if (isVipGood) {
						// 弹出绑定
						return jsonpResponse(JsonRespWrapper.success("flag", "2"));
					} else {
						// 跳过绑定界面
						return jsonpResponse(JsonRespWrapper.success("flag", "1"));
					}
				} else {
					// 报错信息
					List<String> keys = new ArrayList<String>();
					List<Object> msgs = new ArrayList<Object>();
					keys.add("flag");
					msgs.add("3");
					keys.add("msg");
					msgs.add(URLEncoder.encode("商品类型有误", "UTF-8"));
					return jsonpResponse(JsonRespWrapper.failure(keys, msgs));
				}
			}
		}
		catch (Exception e) {
			logger.error("弹出绑定手机号界面抛出异常", e);
			return jsonpResponse(JsonRespWrapper.success("error"));
		}
		// String type = request.getParameter("type");
		// 弹出绑定
		return jsonpResponse(JsonRespWrapper.success("flag", "2"));
	}

	/**
	 * 绑定手机号动作
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/doBind", method = RequestMethod.GET)
	@ResponseBody
	public Object doBindMobile(@RequestParam("validCode") String validCode, @RequestParam("mobile") String mobile, HttpServletRequest request,
	        HttpServletResponse response) {
		return operateMobile(validCode, mobile, CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix(), request, response);

	}

	/**
	 * 区域判断
	 * 
	 * @param regionCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/regionJuge", method = RequestMethod.POST)
	@ResponseBody
	public String regionJudeMobile(@RequestParam("regionCode") String regionCode, HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (null == userinfo) {
			return jsonpResponse(JsonRespWrapper.success("用户未登录"));
		}
		String uregionCode = userinfo.getAreaCode();
		if (StringUtils.isBlank(uregionCode)) {
			return jsonpResponse(JsonRespWrapper.success("用户没有归属区域"));
		}
		uregionCode = uregionCode.substring(0, 2) + "0000";
		if (!regionCode.equals(uregionCode)) {
			return jsonpResponse(JsonRespWrapper.success("请输入江苏移动手机号码"));
		}
		return jsonpResponse(JsonRespWrapper.success("isRegion", "1"));
	}

	/**
	 * 手机绑定 发校验码 动作
	 * 
	 * @param res
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/sendCode")
	@ResponseBody
	public String sendMessage(HttpServletResponse res, @RequestParam("mobile") String mobile) {

		if (StringUtils.isNotBlank(mobile)) {
			SessionUser userinfo = SessionUser.getSessionUser(res);
			logger.info("开始发校验码");

			String verifyCode = verifyCodeService.generateVerifyCode(CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix() + userinfo.getId());
			String message = MessageFormat.format(appConfig.getSmsBindMobile(), verifyCode);

			try {
				logger.info("绑定手机号下发验证码：====手机号：" + mobile.trim() + "==下发信息：" + message);
				jmsMessageService.sendSms(message, mobile.trim());
			}
			catch (Exception ex) {
				logger.warn("发送验证码消息失败，验证码：" + verifyCode, ex);
			}
			return jsonpResponse(JsonRespWrapper.success("sendFlag", "1"));

		} else {
			// 手机号为空
			logger.error("绑定手机号为空");
			return jsonpResponse(JsonRespWrapper.success("sendFlag", "0"));
		}
	}

	/**
	 * jsonp回调
	 * 
	 * @param obj
	 * @return
	 */
	private String jsonpResponse(JsonRespWrapper obj) {
		return "bindJsonpCallback(" + obj.toString() + ")";
	}

	/**
	 * 操作手机：诸如绑定手机号；修改手机号；
	 * 
	 * @param validCode
	 *            验证码
	 * @param validCodeType
	 *            发码方式
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	private Object operateMobile(String validCode, String mobile, String validCodeType, HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		try {
			if (null == userinfo) {
				return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("用户未登录", "UTF-8")));
			}

			// 判断是否绑定手机号
			if (CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix().equals(validCodeType)) {
				if (StringUtils.isNotBlank(userinfo.getTerminalId())) {
					return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("您已经绑定手机号", "UTF-8")));
				}
				if (StringUtils.isNotBlank(validCode)) {
					String verifyCode = verifyCodeService.getVerifyCode(validCodeType + userinfo.getId());
					if (verifyCode == null) {
						return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("校验码有误或已过期", "UTF-8")));
					}

					if (!verifyCode.equals(validCode)) {
						return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("校验码有误，请重新输入", "UTF-8")));
					}

					if (tMemberService.isExistMobile(mobile.trim())) {
						return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("手机号已经被绑定", "UTF-8")));
					}
					logger.info("准备操作数据库对mobile字段进行修改");
					return jsonpResponse(updateMobile(mobile, request, response, userinfo));
				}
			}
			return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("异常错误", "UTF-8")));
		}
		catch (Exception e) {
			logger.error("bind-mobile-exception", e);
			return jsonpResponse(JsonRespWrapper.success("error"));
		}

	}

	/**
	 * 操作数据库字段Mobile
	 * 
	 * @param mobile
	 * @param request
	 * @param response
	 * @param userinfo
	 * @return
	 */
	private JsonRespWrapper updateMobile(String mobile, HttpServletRequest request, HttpServletResponse response, LoginUserBean userinfo) {
		// 修改用户手机信息
		soaMemberService.updateMobile(userinfo.getId(), mobile);
		SSOAgent agent = new SSOAgent(request, response);
		agent.refreshUserInfo(userinfo.getId().toString(), "-1", "www");

		return JsonRespWrapper.success("bindStatus", "1");
	}

	/**
	 * 失去焦点校验手机号
	 * 
	 * @param mobile
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/blurMobile")
	@ResponseBody
	public String blurMobile(@RequestParam("mobile") String mobile, HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		try {
			if (null == userinfo) {
				return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("用户未登录", "UTF-8")));
			}
			if (StringUtils.isNotBlank(userinfo.getTerminalId())) {
				return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("您已经绑定手机号", "UTF-8")));
			}
			if (tMemberService.isExistMobile(mobile.trim())) {
				return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("手机号已经被绑定", "UTF-8")));
			} else {
				return jsonpResponse(JsonRespWrapper.success("flag", 1));
			}

		}
		catch (Exception e) {
			logger.error("绑定手机号抛出异常", e);
			return jsonpResponse(JsonRespWrapper.success("error"));
		}
	}

	/**
	 * 失去焦点校验校验码
	 * 
	 * @param validCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/blurVerifyCode")
	@ResponseBody
	public String blurVerifyCode(@RequestParam("validCode") String validCode, HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		try {
			if (null == userinfo) {
				return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("用户未登录", "UTF-8")));
			}
			String validCodeType = CachedObjectType.VERIFY_USERCENTER_BIND.getPrefix();

			if (StringUtils.isNotBlank(validCode)) {
				String verifyCode = verifyCodeService.getVerifyCode(validCodeType + userinfo.getId());
				if (verifyCode == null) {
					return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("校验码有误或已过期", "UTF-8")));
				}

				if (!verifyCode.equals(validCode)) {
					return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("校验码有误，请重新输入", "UTF-8")));
				} else {
					return jsonpResponse(JsonRespWrapper.success("flag", 1));
				}
			}

			return jsonpResponse(JsonRespWrapper.success(URLEncoder.encode("异常错误", "UTF-8")));
		}
		catch (Exception e) {
			logger.error("绑定手机号抛出异常", e);
			return jsonpResponse(JsonRespWrapper.success("error"));
		}
	}
}
