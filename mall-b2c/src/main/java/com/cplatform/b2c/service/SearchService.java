package com.cplatform.b2c.service;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.HttpClientUtils;
import com.cplatform.b2c.util.SysTypeUtil;

/**
 * 搜索相关类.
 * <p>
 * Copyright: Copyright (c) 2013-6-9 上午9:34:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author yangxm@c-platform.com
 */

@Service
@Transactional
public class SearchService {

	@Autowired
	private SysTypeUtil sysTypeUtil;

	@Autowired
	private AppConfig appConfig;

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	// 每页数据数
	private static final int SEARCH_PAGE_ROWS = 20;

	/**
	 * http post方式获得搜索json
	 * 
	 * @param keyword
	 * @param store_id
	 * @param type_id
	 * @param brand
	 * @param sort
	 * @param params
	 * @param curpage
	 * @return
	 * @throws Exception
	 */
	public String getSearchJson(String keyword, String store_id, String region_code, String type_id, String brand, String sort, String params,
	        String curpage) throws Exception {

		// 创建http的body
		JSONObject jsonBody = new JSONObject();
		try {
			if (!"".equals(region_code) && region_code != null) {
				region_code = region_code.substring(0, region_code.length() - 2);
			}
			if (StringUtils.isNotBlank(type_id) && !type_id.equals("0")) {
				keyword = "";
			}
			jsonBody = HttpClientUtils.makeJsonBody(keyword, store_id, region_code, type_id, brand, sort, params, curpage, SEARCH_PAGE_ROWS);

		}
		catch (Exception e) {
			logger.error("search make http body error:" + e.toString());
		}

		String jsonResponce = "";
		try {
			// 执行post请求
			jsonResponce = HttpClientUtils.httpPost(appConfig.getSearch_Http_Url(),
			                                        new String(Base64.encodeBase64(jsonBody.toString().getBytes("UTF-8"))), 0, 0);

			String stringJson = new String(Base64.decodeBase64(jsonResponce.getBytes("UTF-8")), "UTF-8");
			if (stringJson != null && !stringJson.equals("")) {
				return stringJson;
			}
		}
		catch (Exception e) {
			logger.error("httpPost error:" + e.toString());
			throw new Exception(e);
		}
		return jsonResponce;

	}

	public String getSearchJsonHeNan(String keyword, String store_id, String region_code, String type_id, String brand, String sort, String params,
	        String curpage) throws Exception {

		// 创建http的body
		JSONObject jsonBody = new JSONObject();
		try {
			if (!"".equals(region_code) && region_code != null) {
				region_code = region_code.substring(0, region_code.length() - 2);
			}
			// type_id和keyword可以同时传递。
			// if ("" != type_id && type_id != null && !type_id.equals("0")) {
			// keyword = "";
			// }
			jsonBody = HttpClientUtils.makeJsonBody(keyword, store_id, region_code, type_id, brand, sort, params, curpage, SEARCH_PAGE_ROWS);

		}
		catch (Exception e) {
			logger.error("search make http body error:" + e.toString());
		}

		String jsonResponce = "";
		try {
			// 执行post请求
			jsonResponce = HttpClientUtils.httpPost(appConfig.getSearch_Http_Url(),
			                                        new String(Base64.encodeBase64(jsonBody.toString().getBytes("UTF-8"))), 0, 0);

			String stringJson = new String(Base64.decodeBase64(jsonResponce.getBytes("UTF-8")), "UTF-8");
			if (stringJson != null && !stringJson.equals("")) {
				return stringJson;
			}
		}
		catch (Exception e) {
			logger.error("httpPost error:" + e.toString());
			throw new Exception(e);
		}
		return jsonResponce;
	}

	/**
	 * 获取搜索内容json分页内容
	 * 
	 * @param curpage
	 * @param json
	 * @return
	 */
	public PageInfo getSearchPageInfo(String curpage, JSONObject json) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		pageInfo.setRecordCount(Integer.valueOf(json.getString("TOTAL")));
		pageInfo.setPageRows(SEARCH_PAGE_ROWS);
		pageInfo.setPageTotals();

		return pageInfo;
	}

	/**
	 * 获取分页后页面内容
	 * 
	 * @param pageInfo
	 * @return
	 */
	public String getSearchScript(PageInfo pageInfo) {
		logger.debug(pageInfo.getScript());
		return pageInfo.getScript();
	}

	/**
	 * 生成菜单目录
	 * 
	 * @param type_id
	 * @return
	 */
	public String makeMenuTab(String webroot, String region_code, String type_id, String sort, String searchSource, String curpage) {
		return sysTypeUtil.getMenuTypeList(webroot, region_code, type_id, sort, searchSource, curpage);
	}
}
