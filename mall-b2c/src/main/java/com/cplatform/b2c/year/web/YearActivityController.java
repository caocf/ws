package com.cplatform.b2c.year.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.year.service.YearActivityService;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * 年货活动. <br>
 * <p>
 * Copyright: Copyright (c) 2014-1-7 上午10:51:10
 * <p>
 * Company:宽连信息（苏州）技术有限公司
 * <p>
 * 
 * @author jiaojian-ca@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/yearActivity ")
public class YearActivityController {

	@Autowired
	YearActivityService yearActivityService;

	private final Logger logger = Logger.getLogger(YearActivityController.class);

	private static Map<String, String> areaMap = null;
	static {
		areaMap = new HashMap<String, String>();
		// 南京（025）、无锡（0510）、镇江（0511）、苏州（0512）、南通（0513）、扬州（0514）、盐城（0515）、徐州（0516）、淮安（0517）、连云港（0518）、常州（0519）、泰州（0523）、宿迁（0527）
		// 320100 南京市、 320200 无锡市、 320300 徐州市、 320400 常州市、 320500 苏州市、320600
		// 南通市、320700 连云港市、320800 淮安市、320900 盐城市、321000 扬州市、321100 镇江市、321200
		// 泰州市、321300 宿迁市
		areaMap.put("025", "320100");// 320100 南京市、
		areaMap.put("0510", "320200");// 320200 无锡市、
		areaMap.put("0511", "321100");// 321100 镇江市、
		areaMap.put("0512", "320500");// 320500 苏州市、
		areaMap.put("0513", "320600");// 320600 南通市、
		areaMap.put("0514", "321000");// 321000 扬州市、
		areaMap.put("0515", "320900");// 320900 盐城市
		areaMap.put("0516", "320300");// 320300 徐州市、
		areaMap.put("0517", "320800");// 320800 淮安市、
		areaMap.put("0518", "320700");// 320700 连云港市、
		areaMap.put("0519", "320400");// 320400 常州市、
		areaMap.put("0523", "321200");// 321200 泰州市、
		areaMap.put("0527", "321300");// 321300 宿迁市
	}

	/**
	 * 年货列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String getYearList(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model,
	        HttpServletResponse response, HttpServletRequest requet, @CookieValue(value = "areaCode", defaultValue = "0512") String areaCode) {
		StringBuffer buff = new StringBuffer();
		try {
			LoginUserBean userinfo = new SSOAgent(requet, response).loginUserInfo("mall");
			String ac = areaMap.get(areaCode);
			if (userinfo == null) {
				ac = null;
			}

			ListPage<Map<String, Object>> pageData = yearActivityService.findYearActivity(page, 12, ac);

			if (pageData.getData().size() != 0) {
				// 以下为分页
				buff.append(" <div class=\"cb\" id=\"pagenav\">");
				// 总共页数
				int totalPage = pageData.getPageCount();
				showPageNum(totalPage, pageData.getCurPage(), buff, areaCode);
				buff.append("<br>共" + totalPage + "页,每页 12 条");
				buff.append("</div>");
				buff.append("<input type=\"hidden\" id=\"totalpage\" value=\"" + totalPage + "\"/>");
			}

			model.addAttribute("pageHtml", buff.toString());
			model.addAttribute("pageData", pageData);
		}
		catch (Exception e) {
			logger.error("进入年货列表页面出错:" + e.getMessage());
			e.printStackTrace();
		}

		return "year/year-activity2";
	}

	/**
	 * 判断用户是否登录
	 * 
	 * @param response
	 * @param requet
	 * @return
	 */
	@RequestMapping(value = "checkLogin")
	@ResponseBody
	public String checkLogin(HttpServletResponse response, HttpServletRequest requet) {
		try {
			String res = "";
			LoginUserBean userinfo = new SSOAgent(requet, response).loginUserInfo("mall");
			if (userinfo != null) {
				res = "login";
			} else {
				res = "unlogin";
			}
			return res;
		}
		catch (Exception e) {
			logger.error("判断用户是否登录:" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 分页HTML
	 * 
	 * @param totalPage
	 *            总页数
	 * @param current
	 *            当前页
	 * @param buff
	 *            html
	 * @param status
	 */
	private void showPageNum(int totalPage, int current, StringBuffer buff, String status) {
		int startTemp;// 开始页码
		int endTemp;// 结束页码
		if (current != 1) {
			buff.append("<a href=\"javascript:goNext(1)\">首页</a>");
			buff.append("<a href=\"javascript:goNext('" + (current - 1) + "')\">上一页</a>");
		}
		if (current >= 1 && current < 10) {
			startTemp = 1;
			endTemp = totalPage > 10 ? 10 : totalPage;
		} else {
			startTemp = current - 4;
			endTemp = current + 5;
			if (startTemp < 1) {
				startTemp = 1;
			}
			if (endTemp > totalPage) {
				endTemp = totalPage;
			}
		}
		for (int i = startTemp; i <= endTemp; i++) {
			if (i == current) {
				buff.append("<a class=\"hover\">" + i + "</a>");
			} else {
				buff.append("<a href=\"javascript:goNext('" + i + "')\">" + i + "</a>");
			}
		}
		if (current != totalPage) {
			buff.append("<a href=\"javascript:goNext('" + (current + 1) + "')\">下一页</a>");
			buff.append("<a href=\"javascript:goNext('" + totalPage + "')\">末页</a>");
		}
	}

}
