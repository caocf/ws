package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.ListPage;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.utils.AppConfig;
import com.cplatform.mall.dc.utils.JsonRespWrapper;
import com.cplatform.mall.dc.view.ExcelData;
import com.cplatform.mall.dc.view.ExcelSheet;
import com.cplatform.util2.TimeStamp;
import com.cplatform.verifycode.ConsumeReqInfo;
import com.cplatform.verifycode.ConsumeRespInfo;
//import com.cplatform.verifycode.Platform;
import com.cplatform.verifycode.VerifyService;

/**
 * 综合验证服务
 * <p/>
 * Copyright: Copyright (c) 13-6-4 上午10:27
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class CertService {

	@Autowired
	AppConfig appConfig;

	@Autowired
	DbHelper dbHelper;

	@Autowired
	VerifyService codeServiceClient;

	final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 验证码验证，从接口获取数据
	 * 
	 * @param vcode
	 *            验码
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> verifyCode(String vcode) throws Exception {

		SessionUser sessionUser = SessionUser.getSessionUser();
		// int shopClass = sessionUser.getShopClass();
		//
		// String shopId = "0";
		// String storeId = sessionUser.getShopId().toString();
		//
		// if (shopClass == AppConfig.SHOP_CLASS_SHOP) {
		// shopId = sessionUser.getShopId().toString();
		// storeId = sessionUser.getActShopId().toString();
		// }

		ConsumeReqInfo request = new ConsumeReqInfo();
		// 16长度
		if (vcode.length() == 16) {
//			request.setPlatform_id(Platform.Founder.getCode()); // 方正验码
		} else {
//			request.setPlatform_id(Platform.Unknown.getCode());
		}
		// request.setStoreId(storeId); //商户编号
		// request.setShopId(shopId); //门店编号
		request.setVerifyCode(vcode); // 验证码
		request.setVerifyChannel(ConsumeReqInfo.VerifyChannel.WEB); // WEB验证渠道
		request.setUsetimes(1);

		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("UserId",
				StringUtils.isEmpty(sessionUser.getName()) ? sessionUser
						.getId().toString() : sessionUser.getName()); // 操作人
		request.setfExtReqInfo(hmap);

		ConsumeRespInfo response = codeServiceClient.verify(request);
		logger.info("验码请求响应包：" + response.toStringPretty());
		if (0 == response.getStatusCode()) {
			// 验码成功，展示内容
			return JsonRespWrapper
					.success()
					.json("vcode", vcode)
					.json("code", response.getStatusCode())
					.json("msg",
							StringUtils.replace(response.getPrintText(),
									"\r\n", "<br/>"));
		} else {
			// 验码失败，失败说明
			return JsonRespWrapper.errorField("vcode",
					"验码失败，" + response.getStatusText());
		}
	}

	/**
	 * 获取查询sql及查询参数
	 * 
	 * @param timeRange
	 * @param startTime
	 * @param endTime
	 * @param itemId
	 * @param itemName
	 * @param op
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getVerifyHistoryQueryObject(String timeRange,
			String startTime, String endTime, String itemId, String itemName,
			String op) throws SQLException {
		StringBuilder sql = new StringBuilder();

		Object[] params = new Object[] {};

		sql.append("select t2.item_id, t2.item_name, t2.act_order_id, t1.* "
				+ "from v_verify_consume t1, t_verify_code_info t2 "
				+ "where t1.order_id = t2.order_id ");

		// if (sessionUser.getShopClass() == AppConfig.SHOP_CLASS_SHOP) {
		// sql.append(" and t1.ship_id = ?");
		// params = ArrayUtils.add(params, sessionUser.getShopId());
		// } else {
		// sql.append(" and t2.store_id = ?");
		// params = ArrayUtils.add(params, sessionUser.getShopId());
		// }

		if ("today".equals(timeRange)) {
			String now = TimeStamp.getTime(TimeStamp.YYYYMMDD);
			startTime = now;
			endTime = now;
		} else if ("seven".equals(timeRange)) {
			startTime = TimeStamp.getAddTime(Calendar.getInstance(),
					Calendar.DAY_OF_MONTH, -7, TimeStamp.YYYYMMDD);
			endTime = TimeStamp.getTime(TimeStamp.YYYYMMDD);
		}

		if (StringUtils.isNotBlank(timeRange) && !timeRange.equals("all")) {
			if (StringUtils.isNotBlank(startTime)) {
				sql.append(" and t1.verify_date >= ?");
				params = ArrayUtils.add(params, startTime.concat("000000"));
			}
			if (StringUtils.isNotBlank(endTime)) {
				sql.append(" and t1.verify_date <= ?");
				params = ArrayUtils.add(params, endTime.concat("235959"));
			}
		}

		if (StringUtils.isNotBlank(itemId)) {
			sql.append(" and t2.item_id = ?");
			params = ArrayUtils.add(params, itemId);
		}

		if (StringUtils.isNotBlank(itemName)) {
			sql.append(" and t2.item_name like ?");
			params = ArrayUtils.add(params, "%" + itemName + "%");
		}

		if (StringUtils.isNotBlank(op)) {
			sql.append(" and t1.verify_user like ?");
			params = ArrayUtils.add(params, "%" + op + "%");
		}

		sql.append(" order by t1.verify_date desc, t1.id");

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sql", sql.toString());
		result.put("params", params);
		return result;
	}

	/**
	 * 验证历史记录的翻页实现
	 * 
	 * @param timeRange
	 * @param startTime
	 * @param endTime
	 * @param itemId
	 * @param itemName
	 * @param op
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ListPage<Map<String, String>> getVerifyHistory(String timeRange,
			String startTime, String endTime, String itemId, String itemName,
			String op, int page, int pageSize) throws SQLException {
		Map<String, Object> queryObject = getVerifyHistoryQueryObject(
				timeRange, startTime, endTime, itemId, itemName, op);
		Object[] params = (Object[]) queryObject.get("params");
		return dbHelper.getMapPage((String) queryObject.get("sql"), page,
				pageSize, params);
	}

	/**
	 * 验证历史记录导出功能服务
	 * 
	 * @param timeRange
	 * @param startTime
	 * @param endTime
	 * @param itemId
	 * @param itemName
	 * @param op
	 * @return
	 * @throws SQLException
	 */
	public ExcelData getVerifyHistoryReport(String timeRange, String startTime,
			String endTime, String itemId, String itemName, String op)
			throws SQLException {
		Map<String, Object> queryObject = getVerifyHistoryQueryObject(
				timeRange, startTime, endTime, itemId, itemName, op);
		Object[] params = (Object[]) queryObject.get("params");

		List<Map<String, String>> list = dbHelper.getMapList(
				(String) queryObject.get("sql"), params);

		ExcelData excel = new ExcelData();
		excel.setFileName("验证码历史验证记录");

		Map<String, ExcelSheet> sheets = new TreeMap<String, ExcelSheet>();
		ExcelSheet sheet = new ExcelSheet();

		sheet.setName("验证码历史验证记录");
		sheet.setTitles(new String[] { "商品编号", "商品名称", "订单号", "数量", "验证渠道",
				"验证时间", "验证状态", "操作员" });
		sheet.setWidths(new int[] { 4, 15, 6, 3, 3, 6, 3, 6 });
		sheet.setDataTypes(new ExcelData.DataType[] {
				ExcelData.DataType.STRING, ExcelData.DataType.STRING,
				ExcelData.DataType.STRING, ExcelData.DataType.LONG,
				ExcelData.DataType.STRING, ExcelData.DataType.DATE14,
				ExcelData.DataType.STRING, ExcelData.DataType.STRING });
		sheets.put("1", sheet);

		for (Map<String, String> stringObjectMap : list) {

			// t.id, t.account_number, t.goods_name, t.goods_src, t.score
			// score1, t.senior_score_price score2, t.vip_score_price score3,
			// ");
			// t2.area_code, t3.code, busi_name
			Object[] line = new Object[] { stringObjectMap.get("item_id"),
					stringObjectMap.get("item_name"),
					stringObjectMap.get("act_order_id"),
					stringObjectMap.get("use_times"),
					channelToStr(stringObjectMap.get("verify_channel")),
					stringObjectMap.get("verify_date"),
					statusToStr(stringObjectMap.get("verify_status")),
					opToStr(stringObjectMap) };
			sheet.getContents().add(line);
		}

//		for (String s : sheets.keySet()) {
//			excel.getSheets().add(sheets.get(s));
//		}

		for (Iterator<Entry<String, ExcelSheet>> iterator = sheets.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, ExcelSheet> entry = iterator.next();
			excel.getSheets().add(sheets.get(entry.getKey()));
		}

		return excel;
	}

	private String statusToStr(String status) {
		if ("0000".equals(status) || "1000".equals(status))
			return "成功";
		else
			return "失败(CODE:" + status + ")";
	}

	private String opToStr(Map<String, String> so) {
		if ("POS".equals(so.get("verify_channel"))) {
			return "POS:" + so.get("pos_id") + " - " + so.get("pos_seq");
		} else {
			return so.get("verify_user");
		}
	}

	private String channelToStr(String channel) {
		if ("WEB".equals(channel))
			return "网站";
		if ("WAP".equals(channel))
			return "WAP";
		if ("SMS".equals(channel))
			return "短信";
		if ("CLIENT".equals(channel))
			return "客户端";
		if ("POS".equals(channel))
			return "POS机";
		return "";
	}
}
