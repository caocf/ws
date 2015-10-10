package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.model.OrderInfo;
import com.cplatform.mall.dc.model.OrderRateInfo;
import com.cplatform.mall.dc.utils.AppConfig;
import com.cplatform.mall.dc.utils.ChartDataUtil;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * 订单转化率统计 <br>
 * <p>
 * Copyright: Copyright (c) 2014-4-29 上午10:07:06
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class OrderRateService {
	
	@Autowired
	private AppConfig config;
	
	@Autowired
	DbHelper dbHelper;

	// 时间差
	public static int TIME_DIF = 60;
	// 取数据量
	public static int SEARCH_NUM = 24;

	private String START_TIME_CACHE;

	private Map<Type, List> cache = new HashMap<Type, List>();

	@PostConstruct
	public void init() throws SQLException {
		SEARCH_NUM = config.getWebShowNum();
		String startTime = DateUtil.getTime(-(TIME_DIF*(SEARCH_NUM-1)));
		startTime = startTime.substring(0, 10)+"00";
		START_TIME_CACHE = startTime;
		String sql = "select * from t_pay_turnover_rate where substr(collect_time,0,12)>=?";
		List<Map<String, String>> list = dbHelper.getMapList(sql,
				new Object[] { startTime });
		List<OrderRateInfo> orderList = new ArrayList<OrderRateInfo>();
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				OrderRateInfo order = new OrderRateInfo();
				order.setId(map.get("seq_id"));
				order.setType(map.get("act_type"));
				order.setCollectTime(map.get("collect_time"));
				order.setRate(ChartDataUtil.doubleFormat(Double.parseDouble(map.get("turnover_rate")), "#.##"));
				orderList.add(order);
			}
		}
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> webList = new ArrayList<Double>();
		List<Double> wapList = new ArrayList<Double>();
		List<Double> clientList = new ArrayList<Double>();
		if (orderList.size() > 0) {
			// 存入时间
			for (int i = 0; i < SEARCH_NUM; i++) {
				Double webvalue = 0D;
				Double wapvalue = 0D;
				Double clientvalue = 0D;
				String time = DateUtil.getTime(startTime, i * TIME_DIF);
				if(i==0){
					List<String> start = new ArrayList<String>();
					start.add(DateUtil.getWebTime(time));
					cache.put(Type.START, start);
				}
				if(i==(SEARCH_NUM-1)){
					List<String> end = new ArrayList<String>();
					end.add(DateUtil.getWebTime(time));
					cache.put(Type.END, end);
				}
				seriesList.add(DateUtil.getShowTime(time));
				for (OrderRateInfo o : orderList) {
					if (o.getCollectTime().substring(0, 10).equals(time.substring(0,10))) {
						if (o.getType().equals("1")) {
							webvalue = o.getRate();
						}
						if (o.getType().equals("2")) {
							wapvalue = o.getRate();
						}
						if (o.getType().equals("3")) {
							clientvalue = o.getRate();
						}
					}

				}
				webList.add(webvalue);
				wapList.add(wapvalue);
				clientList.add(clientvalue);
			}
		}
		cache.put(Type.FIELD, seriesList);
		cache.put(Type.WEB, webList);
		cache.put(Type.WAP, wapList);
		cache.put(Type.CLIENT, clientList);
	}

	public enum Type {
		// 1、web 2、wap 3：client
		WEB("1"), WAP("2"), CLIENT("3"), FIELD("seriesList"), START("start"), END("end");
		private final String type;

		Type(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	/**
	 * 获取订单数总量
	 * 
	 * @param condition
	 *            条件模型对象
	 * @return 数据列表
	 * @throws SQLException
	 */
	public Map<Type, List> getInit() throws SQLException {
		String startTime = DateUtil.getTime(-(TIME_DIF*(SEARCH_NUM-1)));
		startTime = startTime.substring(0,10)+"00";
		// 刷新时若时间不变，不更新
		if (!startTime.equals(START_TIME_CACHE)) {
			START_TIME_CACHE = startTime;
			init();
		}
		return cache;
	}

	public Map<Type, List> getAllOrder(String start, String end)
			throws SQLException {
		if (!start.endsWith("00")) {
			start = DateUtil.getTime(start, 60);
			start = start.substring(0, 10) + "00";
		}
		end = end.substring(0, 10) + "00";
		int num = 0;
		try {
			num = DateUtil.getDifTime(start, end) / TIME_DIF + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from t_pay_turnover_rate where substr(collect_time,0,10)>=? and substr(collect_time,0,10)<=?";
		Map<Type, List> listMap = new HashMap<Type, List>();
		List<Map<String, String>> list = dbHelper.getMapList(sql, new Object[] {
				start.substring(0,12), end.substring(0,12) });
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> webList = new ArrayList<Double>();
		List<Double> wapList = new ArrayList<Double>();
		List<Double> clientList = new ArrayList<Double>();
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				OrderInfo order = new OrderInfo();
				order.setType(map.get("act_type"));
				order.setCollectTime(map.get("collect_time"));
				order.setFlowNum(ChartDataUtil.doubleFormat(Double.parseDouble(map.get("turnover_rate")), "#.##"));
				orderList.add(order);
			}
		}
		if (orderList.size() > 0 && num > 0) {
			// 存入时间
			for (int i = 0; i < num; i++) {
				Double webvalue = 0D;
				Double wapvalue = 0D;
				Double clientvalue = 0D;
				String time = "";
				time = DateUtil.getTime(start, i * TIME_DIF);
				seriesList.add(DateUtil.getShowTime(time));
				for (OrderInfo o : orderList) {
					if (o.getCollectTime().substring(0, 12).equals(time)) {
						if (o.getType().equals("1")) {
							webvalue = o.getFlowNum();
						}
						if (o.getType().equals("2")) {
							wapvalue = o.getFlowNum();
						}
						if (o.getType().equals("3")) {
							clientvalue = o.getFlowNum();
						}
					}
				}
				webList.add(webvalue);
				wapList.add(wapvalue);
				clientList.add(clientvalue);
			}
		}
		// 列名称
		listMap.put(Type.FIELD, seriesList);
		listMap.put(Type.WEB, webList);
		listMap.put(Type.WAP, wapList);
		listMap.put(Type.CLIENT, clientList);
		return listMap;
	}

}
