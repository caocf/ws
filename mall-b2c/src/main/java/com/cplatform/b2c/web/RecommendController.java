package com.cplatform.b2c.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.service.RecommendService;

/**
 * 智能推荐controller. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-17 上午10:44:38
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangmeng@c-platform.com
 * @version 1.0.0
 */
@Controller
public class RecommendController {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(RecommendController.class);

	@Autowired
	private RecommendService recommendService;

	/** 用户未登录时默认手机号 */
	private static final String DEFAULT_MOBILE = "18688888888";

	/** 取前3条推荐结果 */
	private static final String TOP_NUMBER = "3";

	/** cookie中未找到areaCode时，取默认值0512(苏州) */
	private static final String DEFAULT_AREA_CODE = "0512";

	/**
	 * 个人中心代金券推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recommend/zxdj")
	@ResponseBody
	public Object getUserCenterRecommendQuan(HttpServletRequest request, HttpServletResponse response) {
		return getRecommendResp(request, response, "zxdj");
	}

	/**
	 * 个人中心优惠券推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recommend/zxyh")
	@ResponseBody
	public Object getUserCenterRecommendCoupon(HttpServletRequest request, HttpServletResponse response) {
		return getRecommendResp(request, response, "zxyh");
	}

	/**
	 * 个人中心团购页推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recommend/zxtg")
	@ResponseBody
	public Object getUserCenterRecommendTuan(HttpServletRequest request, HttpServletResponse response) {
		return getRecommendResp(request, response, "zxtg");
	}

	/**
	 * 团购搜索结果推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recommend/sstg")
	@ResponseBody
	public Object getRecommendTuanSearchList(HttpServletRequest request, HttpServletResponse response) {
		return getRecommendResp(request, response, "sstg");
	}

	/**
	 * 代金券详情页推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recommend/xqdj")
	@ResponseBody
	public Object getRecommendQuanDetailList(HttpServletRequest request, HttpServletResponse response) {
		return getRecommendResp(request, response, "xqdj");
	}

	/**
	 * 团购详情页推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recommend/xqtg")
	@ResponseBody
	public Object getRecommendTuanDetailList(HttpServletRequest request, HttpServletResponse response) {
		return getRecommendResp(request, response, "xqtg");
	}

	/**
	 * 订单成功推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recommend/ddcg")
	@ResponseBody
	public Object getPaySuccessRecommendQuanList(HttpServletRequest request, HttpServletResponse response) {
		return getRecommendResp(request, response, "ddcg");
	}

	private Object getRecommendResp(HttpServletRequest request, HttpServletResponse response, String flag) {
		logger.info("推荐标识：" + flag);
		String mobile = null; // 用户手机号
		String areaCode = null; // 地区编码
		boolean isLogin = false; // 是否登录
		String userId = ""; // 用户ID
		String remoteAddr = ""; // 请求端ip
		SessionUser sessionUser = SessionUser.getSessionUser(response);
		if (sessionUser != null) {
			mobile = sessionUser.getTerminalId();
			userId = sessionUser.getId().toString();
			isLogin = true;
			logger.info("sessionUser.TerminalId:" + sessionUser.getTerminalId());
		} else {
			mobile = DEFAULT_MOBILE;
			remoteAddr = request.getRemoteAddr();
		}

		// 从cookie中获取areaCode
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isNotEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				if (StringUtils.equals("areaCode", cookie.getName())) {
					areaCode = cookie.getValue();
				}
			}
		}
		if (StringUtils.isEmpty(areaCode)) {
			areaCode = DEFAULT_AREA_CODE;
		}

		Object obj = null;
		if ("sstg".equals(flag)) {
			obj = recommendService.getRecommendTuanSearchList(mobile, TOP_NUMBER, areaCode, userId, remoteAddr, isLogin);
		} else if ("xqdj".equals(flag)) {
			obj = recommendService.getRecommendQuanDetailList(mobile, TOP_NUMBER, areaCode, userId, remoteAddr, isLogin);
		} else if ("xqtg".equals(flag)) {
			obj = recommendService.getRecommendTuanDetailList(mobile, TOP_NUMBER, areaCode, userId, remoteAddr, isLogin);
		} else if ("zxtg".equals(flag)) {
			obj = recommendService.getUserCenterRecommendTuanList(mobile, TOP_NUMBER, areaCode, userId, remoteAddr, isLogin);
		} else if ("zxdj".equals(flag)) {
			obj = recommendService.getUserCenterRecommendQuanList(mobile, TOP_NUMBER, areaCode, userId, remoteAddr, isLogin);
		} else if ("zxyh".equals(flag)) {
			obj = recommendService.getUserCenterRecommendCouponList(mobile, TOP_NUMBER, areaCode, userId, remoteAddr, isLogin);
		} else if ("ddcg".equals(flag)) {
			obj = recommendService.getPaySuccessRecommendQuanList(mobile, TOP_NUMBER, areaCode, userId, remoteAddr, isLogin);
		}
		return obj == null ? "{}" : JSONSerializer.toJSON(obj).toString();
	}
}
