package com.cplatform.b2c.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.model.TSysTypeDTO;
import com.cplatform.b2c.service.SearchService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.JsonRespWrapper;
import com.cplatform.b2c.util.SysTypeUtil;
import com.cplatform.chead.MallHeader;

/**
 * 搜索相关控制方法 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-9 上午9:34:00
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 */
@Controller
public class SearchController {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SearchService searchService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private SysTypeUtil sysTypeUtil;

	// 按照搜索条件搜索内容
	@RequestMapping(value = "/search/gotoSearch")
	public String getSearch(HttpServletRequest requet, HttpServletResponse response, Model model) throws IOException {

		// 搜索关键字
		String searchKey = requet.getParameter("keyword");
		if (searchKey == null || searchKey.equals("")) {
			searchKey = "";
		} else {
			searchKey = StringFilter(searchKey);
		}

		// 商户id
		String store_id = requet.getParameter("store_id");
		if (store_id == null || store_id.equals("")) {
			store_id = "";
		}

		// 地市搜索
		String region_code = requet.getParameter("region_code");
		if (StringUtils.isNotBlank(region_code) && StringUtils.isNumeric(region_code) && 6 <= region_code.length()
		        && Integer.parseInt(region_code) > 0) {
		} else {
			region_code = "";
		}

		// 品牌搜索
		String brand = requet.getParameter("brand");
		if (brand == null || brand.equals("")) {
			brand = "";
		}

		// 排序：0：默认排序，按搜索结果与关键词匹配度排序,1：人气倒序,2：价格升序，3：价格倒序，4：上架时间倒序,5：更新时间排序
		String sort = requet.getParameter("sort");
		if (sort == null || sort.equals("")) {
			sort = "5";
		}

		// 属性搜索，多个属性可用,分割
		String params = "";
		String myparams = requet.getParameter("selectparams");

		if (myparams == null || myparams.equals("")) {
			myparams = "";
		} else {
			if (myparams.indexOf(",") > 0) {
				String[] myStrings = myparams.split(",");
				for (int i = 0; i < myStrings.length; i++) {
					String[] paramStrs = myStrings[i].split(":");
					params += paramStrs[1] + ",";
				}
				params = params.substring(0, params.length() - 1);
			} else {
				params = myparams.split(":")[1];
			}
		}
		// 类别类型
		String hideType = requet.getParameter("selecttype");
		if (hideType == null || hideType.equals("")) {
			hideType = "";
		}

		// 数据来源（djq代金券）
		String searchSource = requet.getParameter("searchSource");

		// 当前页数
		String curpage = requet.getParameter("curpage");
		if (curpage == null || curpage.equals("")) {
			curpage = "1";
		}
		// 显示菜单 例如：全部结果 > 美容护肤 > 面部护理
		String menuTab = "";
		// 商品类别id
		String type_id = requet.getParameter("type_id");
		if (type_id == null || type_id.equals("")) {
			type_id = "0";
			menuTab = "{'menu':[],'flag':'no'}";
		} else {
			menuTab = searchService.makeMenuTab(appConfig.getWebRoot(), region_code, type_id, sort, searchSource, curpage);
		}

		// 全网头部内容
		String headhtml = "";
		if (StringUtils.isNotBlank(region_code)) {
			if (region_code.startsWith("32")) {
				headhtml = MallHeader.getHtml(appConfig.getHeaderUrl(), region_code, "UNION-WWW", "gsc");
			} else {
				headhtml = MallHeader.getHtml(appConfig.getHeaderUrl(), region_code, "QW-WWW", "qw-gsc");
			}

		} else {
			headhtml = MallHeader.getHtml(appConfig.getHeaderUrl(), "320500", "UNION-WWW", "gsc");
		}

		// 返回查询条件
		model.addAttribute("headhtml", headhtml);
		model.addAttribute("region_code", region_code);
		if (type_id != "" && type_id != "0") {
			model.addAttribute("type_id", type_id);
		}
		model.addAttribute("sort", sort);
		model.addAttribute("store_id", store_id);
		model.addAttribute("brand", brand);
		model.addAttribute("curpage", curpage);
		model.addAttribute("myparams", myparams);
		model.addAttribute("hideType", hideType);
		model.addAttribute("searchSource", searchSource);
		model.addAttribute("webroot", appConfig.getWebRoot());
		model.addAttribute("imgSvrHost", appConfig.getImgSvrHost());
		if (searchKey.length() > 50) {
			String errorJson = "{'RET':'500','MSG':'关键字已超过50个字！请重新输入关键字'}";
			model.addAttribute("json", JSONObject.fromObject(errorJson));
			return "search/search";
		}
		model.addAttribute("menuMap", menuTab);
		model.addAttribute("keyword", searchKey);

		// 同时为空的时候提示错误信息
		if ((searchKey == null || searchKey.equals("")) && (type_id == null || type_id.equals(""))) {
			String errorJson = "{'RET':'404''}";
			model.addAttribute("json", JSONObject.fromObject(errorJson));
			return "search/search";
		}

		String searchJson = "";

		JSONObject searchJsonObject;
		try {
			// httpclient 获取搜索返回的json
			searchJson = searchService.getSearchJson(searchKey, store_id, region_code, type_id, brand, sort, myparams, curpage);
			if (searchJson == null || searchJson.equals("")) {
				searchJson = "{'RET':'404'}";
			}
			searchJsonObject = JSONObject.fromObject(searchJson);
		}
		catch (Exception e) {
			String errorJson = "{'RET':'404'}";
			model.addAttribute("json", JSONObject.fromObject(errorJson));
			logger.error(errorJson);
			return "search/search";
		}

		// 如果返回的JSON内容RET是0000说明返回正确，不然显示错误信息
		if (searchJsonObject.containsKey("RET")) {
			if (searchJsonObject.getString("RET").equals("0000")) {
				if (searchJsonObject.getString("DATA").equals("[]")) {
					String errorJson = "{'RET':'400','MSG':''}";
					model.addAttribute("json", JSONObject.fromObject(errorJson));
				} else {
					PageInfo pageInfo = searchService.getSearchPageInfo(curpage, searchJsonObject);
					model.addAttribute("pageInfos", searchService.getSearchScript(pageInfo));

					model.addAttribute("totalPage", pageInfo.getPageTotal());
					model.addAttribute("json", searchJsonObject);
				}
			} else {
				model.addAttribute("json", searchJsonObject);
				return "search/search";
			}
		}

		// 返回搜索json
		// model.addAttribute("PARAMS",JSONObject.fromObject(paramsString) );

		return "search/search";
	}

	// 获取搜索页菜单
	@RequestMapping(value = "/search/menu")
	@ResponseBody
	public Object getSearchMenu(HttpServletRequest requet, HttpServletResponse response) {
		String type_id = requet.getParameter("type_id");
		String keyword = requet.getParameter("keyword");
		List<TSysTypeDTO> childList = new ArrayList<TSysTypeDTO>();
		List<TSysType> parentList = new ArrayList<TSysType>();
		if (type_id != null && !type_id.equals("")) {
			childList = sysTypeUtil.getChildSort(Integer.parseInt(type_id));
			parentList = sysTypeUtil.getParentTypeList(Integer.parseInt(type_id));
			Collections.reverse(parentList);
			if (keyword == null || keyword.equals("")) {
				// 左边菜单
				Map reList = new HashMap();
				reList.put("parent", parentList);
				reList.put("child", childList);
				return JsonRespWrapper.json("data", reList, true);
			}
			for (TSysType tSysType : parentList) {
				// type_id相等的情况下，验证keyword是否是菜单列表的关键字
				if (type_id.equals(tSysType.getId() + "")) {
					if (!tSysType.getName().equals(keyword)) {
						childList = new ArrayList<TSysTypeDTO>();
						parentList = new ArrayList<TSysType>();
					}
				}
			}
		} else {
			childList = new ArrayList<TSysTypeDTO>();
			parentList = new ArrayList<TSysType>();
		}

		// 左边菜单
		Map reList = new HashMap();
		reList.put("parent", parentList);
		reList.put("child", childList);
		return JsonRespWrapper.json("data", reList, true);
	}

	/**
	 * 网上流传版本, 未完整测试.
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
