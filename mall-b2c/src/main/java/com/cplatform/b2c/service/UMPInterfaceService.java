package com.cplatform.b2c.service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONParser;
import com.cplatform.b2c.model.TActOrder;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Md5Encrypt;
import com.cplatform.b2c.util.WebUtils;

/**
 * Title:调用联动接口获取代金券订单（聚U惠_数字商品订单服务） Description: <p/> Copyright：Copyright (c)
 * 13-12-23 上午10:56 <p/> Company：苏州宽连信息技术有限公司 <p/>
 * 
 * @author zhumh@c-platform.com
 * @version 1.0
 */
@Service
public class UMPInterfaceService {

	/**
	 * 日志记录器 *
	 */
	private static final Logger logger = Logger.getLogger(UMPInterfaceService.class);

	@Autowired
	private AppConfig appConfig;

	/**
	 * 主要方法，用来返回订单列表
	 * 
	 * @param mobileNo
	 *            电话号码，作为pay-mobile
	 * @param startTime
	 *            yyyyMMddHHmmss
	 * @param endTime
	 *            yyyyMMddHHmmss
	 * @param payStatus
	 *            -99：初始状态 0：初始状态2：支付成功4：支付失败6：退款7：支付超时
	 * @param orderStatus
	 *            -99：初始状态0：初始状态1：配送中2：配送成功3：无效订单4：配送失败5：请求失败6：订单超时7：通知保险平台失败8：订单成功订单超时9：缺货10：商户自发货
	 * @param orderType
	 *            0：普通商品1：腾讯直充2：保险AB款3：奇顺直充4：优惠劵 5：电影票6：网票网电影
	 * @param name
	 *            搜索添加，名字过滤
	 * @return
	 */
	public List<TActOrder> getOrders(String mobileNo, String startTime, String endTime, String payStatus, String orderStatus, String orderType,
	        String name) {
		// 判断参数
		if (StringUtils.isBlank(name)) {
			name = "";// 匹配区别字符
		}
		// 转换startTime 和endTime以便转换
		try {
			if (StringUtils.isNotBlank(startTime)) {
				if (startTime.length() > 8) {

				} else {
					startTime = startTime.substring(0, 4) + "-" + startTime.substring(4, 6) + "-" + startTime.substring(6, 8);
				}
			} else {
				startTime = "2000-01-01";
			}
			if (StringUtils.isNotBlank(endTime)) {
				if (endTime.length() > 8) {

				} else {
					endTime = endTime.substring(0, 4) + "-" + endTime.substring(4, 6) + "-" + endTime.substring(6, 8);

				}
			} else {
				endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 今天
			}
		}
		catch (Exception e) {
			logger.error("转换startTime,endTime 错误");
		}
		// 调用接口 - queryOrderList
		List<TActOrder> list = new ArrayList<TActOrder>();
		JSONObject json = null;
		JSONArray jsonArray = null;
		try { // 防止访问出错
			json = queryOrderList("", mobileNo, startTime, endTime, payStatus, orderStatus, orderType);
			jsonArray = (JSONArray) json.get("Orders");
		}
		catch (Exception e) {
			logger.error("查询联动数据出错，mobileNo=" + mobileNo);
			return list;
		}
		if (null == jsonArray) {
			return list;
		}
		for (int i = 0; i < jsonArray.size(); i++) {
			TActOrder order = new TActOrder();
			// 取得json中的数据
			JSONObject obj = (JSONObject) jsonArray.get(i);
			// 还有mobileNo, payStatus, orderStatus,orderType
			String payMobile = (String) obj.get("payMobile");
			String orderDate = (String) obj.get("orderDate");
			String portalId = (String) obj.get("portalId");
			String orderNo = (String) obj.get("orderNo");
			String goodsName = (String) obj.get("goodsName");
			String payTime = (String) obj.get("payTime");
			String amount = (String) obj.get("amount");
			String payStatusNew = (String) obj.get("payStatus");
			String orderStatusNew = (String) obj.get("orderStatus");
			String orderTypeNew = (String) obj.get("orderType");
			// 设置order，没有设置orderStatus,goodsName
			String orderDateString = null;
			String payTimeString = null;
			Date createTime_D = new Date();// 默认为今日
			Date payTime_D = null; // 默认没有付款
			try {
				createTime_D = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDate);
				orderDateString = new SimpleDateFormat("yyyyMMddHHmmss").format(createTime_D);

				payTime_D = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(payTime);
				payTimeString = new SimpleDateFormat("yyyyMMddHHmmss").format(payTime_D);

			}
			catch (Exception e) {
				logger.error("parese createTime 错误");
			}
			String amountParse = amount;
			try {
				// 分转元
				DecimalFormat df = new DecimalFormat("#.00");
				amountParse = df.format(Double.parseDouble(amount) / 100);
			}
			catch (Exception e) {
				logger.error("parese amount 错误");
			}
			// 判断条件， goodsname like '%name%'
			if (!goodsName.contains(name)) {
				continue; // 不符合条件，不处理
			}
			// 设置order
			if (!StringUtils.isBlank(payMobile)) {
				order.setUserId(Long.parseLong(payMobile));// 将电话号码设为UserId.
			}
			order.setCreateTime(orderDateString);
			order.setPayTime(payTimeString);
			order.setStatus(Integer.parseInt(payStatusNew)); // -99：初始状态
			// 0：初始状态 2：支付成功
			// 4：支付失败 6：退款
			// 7：支付超时
			order.setOrderType(60L);// 代金券60；
			order.setId(Long.parseLong(orderNo));
			order.setPayAmount(amountParse);
			order.setShopSubject("联动");// 默认联动
			order.setSource("2"); // 联动
			order.setGoodsName(goodsName);
			list.add(order);
		}
		return list;
	}

	/**
	 * 3.1 查询订单列表
	 * 
	 * @param recvMobile
	 *            接收手机号
	 * @param payMobile
	 *            付费手机号，在商城平台和recvMobile一样
	 * @param startTime
	 *            查询时间-开始
	 * @param endTime
	 *            查询时间-结束
	 * @param payStatus
	 *            支付状态
	 * @param orderStatus
	 *            订单状态
	 * @param orderType
	 *            订单类型
	 * @return
	 */
	public JSONObject queryOrderList(String recvMobile, String payMobile, String startTime, String endTime, String payStatus, String orderStatus,
	        String orderType) {
		String urlString = getUmpQueryOrderListUrl();
		String channelId = getUmpChannelId();
		String portalId = getUmpPortalId();
		String secretKey = getUmpSecretKey();

		logger.info("调用联动接口 - queryOrderList - 开始");
		JSONObject jsonParameter = new JSONObject();
		// 检查参数
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime) || StringUtils.isBlank(payStatus) || StringUtils.isBlank(orderStatus)
		        || StringUtils.isBlank(orderType)) {
			logger.error("调用联动接口 - queryOrderList - 参数不正确 - 为空");
		}
		// 签名
		StringBuffer sign = new StringBuffer();
		sign.append(channelId).append(portalId);
		if (!StringUtils.isBlank(recvMobile)) {
			sign.append(recvMobile);
		}
		if (!StringUtils.isBlank(payMobile)) {
			sign.append(payMobile);
		}
		sign.append(startTime).append(endTime).append(payStatus).append(orderStatus).append(orderType).append("||").append(secretKey);
		logger.info("参数 - sign:" + sign);
		String signMD5 = Md5Encrypt.md5(sign.toString());
		// 接口参数jsonParameter
		jsonParameter.put("channelId", channelId);
		jsonParameter.put("portalId", portalId);
		if (!StringUtils.isBlank(recvMobile)) {
			jsonParameter.put("recvMobile", recvMobile);
		}
		if (!StringUtils.isBlank(payMobile)) {
			jsonParameter.put("payMobile", payMobile);
		}
		jsonParameter.put("startTime", startTime);
		jsonParameter.put("endTime", endTime);
		jsonParameter.put("payStatus", payStatus);
		jsonParameter.put("orderStatus", orderStatus);
		jsonParameter.put("orderType", orderType);
		jsonParameter.put("sign", signMD5);
		logger.info("参数 - json:" + jsonParameter.toString());
		// 返回参数
		JSONObject json = new JSONObject();
		/** 联动参数和头文件 */
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String, String>();
		/* 联动接口resp和header */
		String resp = null;
		HttpURLConnection conn = null;
		/** 访问联动接口，处理resp和header信息 */
		try {
			String query = WebUtils.buildQuery(params, "UTF-8");
			String ctype = "text/json;charset=UTF-8";
			URL url = WebUtils.buildGetUrl(urlString, query);
			conn = WebUtils.getConnection(url, headers, "POST", ctype);
			/* 传入json参数 */
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(new String(jsonParameter.toString().getBytes("UTF-8")));
			out.flush();
			out.close();
			// 得到响应
			resp = WebUtils.getResponseAsString(conn);
			logger.info("调用联动接口 - queryOrderList - 返回的信息:" + resp);
			/** 处理返回异常情况 */
			if (StringUtils.isBlank(resp)) {
				logger.error("调用联动接口 - queryOrderList - 无法得到响应内容");
			}
			Map map = (Map) new JSONParser(resp).parse();
			json.putAll(map);
		}
		catch (Exception e) {
			logger.error("调用联动接口 - queryOrderList - 出错或者返回Header时错误");
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		logger.info("调用联动接口 - queryOrderList - 结束 + jsonResult=" + json.toString());
		return json;
	}

	/**
	 * 3.2 查询单笔订单
	 * 
	 * @param orderNo
	 *            订单号
	 * @return
	 */
	public JSONObject queryOrder(String orderNo) {
		String urlString = getUmpQueryOrderUrl();
		String channelId = getUmpChannelId();
		String portalId = getUmpPortalId();
		String secretKey = getUmpSecretKey();

		logger.info("调用联动接口 - queryOrder - orderNumber = " + orderNo + " - 开始");
		JSONObject jsonParameter = new JSONObject();
		// 检查参数
		if (StringUtils.isBlank(orderNo)) {
			logger.error("调用联动接口 - queryOrder - orderNo参数不正确 - 为空");
		}
		// 签名
		StringBuffer sign = new StringBuffer();
		sign.append(channelId).append(portalId).append(orderNo).append("||").append(secretKey);
		logger.info("参数 - sign:" + sign);
		String signMD5 = Md5Encrypt.md5(sign.toString());
		// 接口参数jsonParameter
		jsonParameter.put("channelId", channelId);
		jsonParameter.put("portalId", portalId);
		jsonParameter.put("orderNo", orderNo);
		jsonParameter.put("sign", signMD5);
		logger.info("参数 - json:" + jsonParameter.toString());
		// 返回参数
		JSONObject json = new JSONObject();
		/** 联动参数和头文件 */
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String, String>();
		/* 联动接口resp和header */
		String resp = null;
		HttpURLConnection conn = null;
		/** 访问联动接口，处理resp和header信息 */
		try {
			String query = WebUtils.buildQuery(params, "UTF-8");
			String ctype = "text/json;charset=UTF-8";
			URL url = WebUtils.buildGetUrl(urlString, query);
			conn = WebUtils.getConnection(url, headers, "POST", ctype);
			/* 传入json参数 */
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(new String(jsonParameter.toString().getBytes("UTF-8")));
			out.flush();
			out.close();
			// 得到响应
			resp = WebUtils.getResponseAsString(conn);
			logger.info("调用联动接口 - queryOrder - 返回的信息:" + resp);
			/** 处理返回异常情况 */
			if (StringUtils.isBlank(resp)) {
				logger.error("调用联动接口 - queryOrder - 无法得到响应内容");
			}
			Map map = (Map) new JSONParser(resp).parse();
			json.putAll(map);
		}
		catch (IOException e) {
			logger.error("调用联动接口 - queryOrder - 出错或者返回Header时错误");
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		logger.info("调用联动接口 - queryOrder - 结束 + jsonResult=" + json.toString());
		return json;
	}

	/**
	 * 3.3 卡密重发 - 注意，此方便没有测试和联调
	 * 
	 * @param orderNo
	 *            订单号
	 * @return
	 */
	public JSONObject resendOrder(String orderNo) {
		String urlString = getUmpResendOrderUrl();
		String channelId = getUmpChannelId();
		String portalId = getUmpPortalId();
		String secretKey = getUmpSecretKey();

		logger.info("调用联动接口 - resendOrder - orderNumber = " + orderNo + " - 开始");
		JSONObject jsonParameter = new JSONObject();
		// 检查参数
		if (StringUtils.isBlank(orderNo)) {
			logger.error("调用联动接口 - resendOrder - 参数不正确 - 为空");
		}
		// 签名
		StringBuffer sign = new StringBuffer();
		sign.append(channelId).append(portalId).append(orderNo).append("||").append(secretKey);
		logger.info("参数 - sign:" + sign);
		String signMD5 = Md5Encrypt.md5(sign.toString());
		// 接口参数jsonParameter
		jsonParameter.put("channelId", channelId);
		jsonParameter.put("portalId", portalId);
		jsonParameter.put("orderNo", orderNo);
		jsonParameter.put("sign", signMD5);
		logger.info("参数 - json:" + jsonParameter.toString());
		// 返回参数
		JSONObject json = new JSONObject();
		/** 联动参数和头文件 */
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String, String>();
		/* 联动接口resp和header */
		String resp = null;
		HttpURLConnection conn = null;
		/** 访问联动接口，处理resp和header信息 */
		try {
			String query = WebUtils.buildQuery(params, "UTF-8");
			String ctype = "application/x-www-form-urlencoded;charset=UTF-8";
			URL url = WebUtils.buildGetUrl(urlString, query);
			conn = WebUtils.getConnection(url, headers, "POST", ctype);
			/* 传入json参数 */
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(new String(jsonParameter.toString().getBytes("UTF-8")));
			out.flush();
			out.close();
			// 得到响应
			resp = WebUtils.getResponseAsString(conn);
			logger.info("调用联动接口 - resendOrder - 返回的信息:" + resp);
			// 装配json
			XMLSerializer xmlSerializer = new XMLSerializer();
			/** 处理返回异常情况 */
			if (StringUtils.isBlank(resp)) {
				logger.error("调用联动接口 - resendOrder - 无法得到响应内容");
			}
			Map map = (Map) new JSONParser(resp).parse();
			json.putAll(map);
		}
		catch (IOException e) {
			logger.error("调用联动接口 - resendOrder - 出错或者返回Header时错误");
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		logger.info("调用联动接口 - resendOrder - 结束 + jsonResult=" + json.toString());
		return json;
	}

	public static void main(String[] args) throws Exception {
		logger.setLevel(Level.ALL);
		UMPInterfaceService ump = new UMPInterfaceService();
		// 测试接口 - queryOrderList -- 联调成功
		// JSON json =
		// ump.queryOrderList("","","2012-12-20","2013-12-24","99","99","99");
		// 测试接口 - queryOrder -- 联调成功
		// JSONObject qo = ump.queryOrder("1312200919181243");

		// 测试接口 - resendOrder -- 联调成功
		// JSON ro = ump.resendOrder("1312241117108751");

		// 测试getOrderList
		List<TActOrder> list = ump.getOrders("13520554871", "2012-12-20", "2013-12-24", "99", "99", "99", "");

	}

	/**
	 * 根据返回码得到返回码描述，定义如下 0000 成功 订单类 0006 订单不存在或不合法 0007 订单未支付成功，不能重发订单 系统类 9998
	 * 数据有效性鉴权失败 9999 系统未知错误
	 */
	private static class CodeUtil {

		public static String getCodeInfo(String code) {
			String description = null;
			if (StringUtils.isBlank(code)) {
				logger.info("响应结果码为空");
				return description;
			} else if ("0000".equals(code)) {
				description = "成功";
			} else if ("0006".equals(code)) {
				description = "订单不存在或不合法";
			} else if ("0007".equals(code)) {
				description = "订单未支付成功，不能重发订单";
			} else if ("9998".equals(code)) {
				description = "数据有效性鉴权失败";
			} else if ("9999".equals(code)) {
				description = "系统未知错误";
			}
			return description;
		}
	}

	/**
	 * 主要用来测试接口，因为如果不在web环境下，appconfig数据无法得到
	 * 
	 * @return
	 */
	public String getUmpQueryOrderListUrl() {
		if (appConfig == null || StringUtils.isBlank(appConfig.getUmpQueryOrderListUrl())) {
			logger.error("getUmpQueryOrderListUrl 读不到配置数据");
			return "http://114.251.148.199:8080/interface/api/queryOrderList.do";
		}
		return appConfig.getUmpQueryOrderListUrl();
	}

	/**
	 * 主要用来测试接口，因为如果不在web环境下，appconfig数据无法得到
	 * 
	 * @return
	 */
	public String getUmpQueryOrderUrl() {
		if (appConfig == null || StringUtils.isBlank(appConfig.getUmpQueryOrderUrl())) {
			logger.error("getUmpQueryOrderUrl 读不到配置数据");
			return "http://114.251.148.199:8080/interface/api/queryOrder.do";
		}
		return appConfig.getUmpQueryOrderUrl();
	}

	/**
	 * 主要用来测试接口，因为如果不在web环境下，appconfig数据无法得到
	 * 
	 * @return
	 */
	public String getUmpResendOrderUrl() {
		if (appConfig == null || StringUtils.isBlank(appConfig.getUmpResendOrderUrl())) {
			logger.error("getUmpResendOrderUrl 读不到配置数据");
			return "http://114.251.148.199:8080/interface/api/resendOrder.do";
		}
		return appConfig.getUmpResendOrderUrl();
	}

	/**
	 * 主要用来测试接口，因为如果不在web环境下，appconfig数据无法得到
	 * 
	 * @return
	 */
	public String getUmpChannelId() {
		if (appConfig == null || StringUtils.isBlank(appConfig.getUmpChannelId())) {
			logger.error("getUmpChannelId 读不到配置数据");
			return "jsmo";
		}
		return appConfig.getUmpChannelId();
	}

	/**
	 * 主要用来测试接口，因为如果不在web环境下，appconfig数据无法得到
	 * 
	 * @return
	 */
	public String getUmpPortalId() {
		if (appConfig == null || StringUtils.isBlank(appConfig.getUmpChannelId())) {
			logger.error("getUmpPortalId 读不到配置数据");
			return "2013121965901760";
		}
		return appConfig.getUmpPortalId();
	}

	/**
	 * 主要用来测试接口，因为如果不在web环境下，appconfig数据无法得到
	 * 
	 * @return
	 */
	public String getUmpSecretKey() {
		if (appConfig == null || StringUtils.isBlank(appConfig.getUmpSecretKey())) {
			logger.error("getUmpSecretKey 读不到配置数据");
			return "3l3#d*s8";
		}
		return appConfig.getUmpSecretKey();
	}

}
