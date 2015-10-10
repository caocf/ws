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
import com.cplatform.mall.dc.model.PayMoneyInfo;
import com.cplatform.mall.dc.service.CRMService.Type;
import com.cplatform.mall.dc.utils.AppConfig;
import com.cplatform.mall.dc.utils.ChartDataUtil;
import com.cplatform.mall.dc.utils.DateUtil;

/**
 * PayMoneyService.java <br>
 * <p>
 * Copyright: Copyright (c) 2014-4-30 下午03:26:18
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Service
public class PayMoneyService {
	
	@Autowired
	private AppConfig config;

	@Autowired
	DbHelper dbHelper;

	//时间差
	public static int TIME_DIF = 10;
	//取数据量
	public static int SEARCH_NUM = 20;
	
	private String START_TIME_CACHE;
	
	private Map<Type,List> cache = new HashMap<Type,List>();
	
	@PostConstruct
	public void init() throws SQLException {
		SEARCH_NUM = config.getWebShowNum();
		String startTime = DateUtil.getTime(-(TIME_DIF*(SEARCH_NUM-1)));
		startTime = startTime.substring(0,11)+"0";
		START_TIME_CACHE = startTime;
		String sql = "select * from t_pay_money_num where substr(collect_time,0,12)>=?";
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{startTime});
		List<PayMoneyInfo> orderList = new ArrayList<PayMoneyInfo>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				PayMoneyInfo order = new PayMoneyInfo();
				order.setId(map.get("seq_id"));
				order.setType(map.get("act_type"));
				order.setCollectTime(map.get("collect_time"));
				order.setMoney(Double.parseDouble(map.get("money")));
				orderList.add(order);
			}
		}
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> cashList=new ArrayList<Double>();
		List<Double> coinList=new ArrayList<Double>();
		List<Double> balanceList=new ArrayList<Double>();
		List<Double> scoreList=new ArrayList<Double>();
		if(orderList.size()>0){
			//存入时间
			for(int i=0;i<SEARCH_NUM;i++){
				Double cashvalue = 0D;
				Double coinvalue = 0D;
				Double balancevalue = 0D;
				Double scorevalue = 0D;
				String time = DateUtil.getTime(startTime, i*TIME_DIF);
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
				for(PayMoneyInfo o : orderList){
					if(o.getCollectTime().substring(0,12).equals(time)){
						if(o.getType().equals(Type.CASH.getType())){
							cashvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
						}
						if(o.getType().equals(Type.COIN.getType())){
							coinvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
						}
						if(o.getType().equals(Type.BALANCE.getType())){
							balancevalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
						}
						if(o.getType().equals(Type.SCORE.getType())){
							scorevalue = ChartDataUtil.doubleFormat(o.getMoney()/67,"#.##");
						}
					}
					
				}
				cashList.add(cashvalue);
				coinList.add(coinvalue);
				balanceList.add(balancevalue);
				scoreList.add(scorevalue);
			}
		}
		cache.put(Type.FIELD, seriesList);
		cache.put(Type.CASH, cashList);
		cache.put(Type.COIN, coinList);
		cache.put(Type.BALANCE, balanceList);
		cache.put(Type.SCORE, scoreList);
	}

	public enum Type { 
		//1、web 2、wap 3：client
		CASH("cash"), COIN("coin"), SCORE("score") , BALANCE("balance") , FIELD("seriesList"), START("start"), END("end");
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
	public Map<Type,List> getInit() throws SQLException {
		String startTime = DateUtil.getTime(-(TIME_DIF*(SEARCH_NUM-1)));
		startTime = startTime.substring(0,11)+"0";
		//刷新时若时间不变，不更新
		if (!startTime.equals(START_TIME_CACHE)) {
			START_TIME_CACHE = startTime;
			init();
		}
		return cache;
	}
	
	public Map<Type,List> getAllOrder(String start, String end, int type) throws SQLException {
		if(!start.endsWith("0")){
			start = DateUtil.getTime(start,10);
			start = start.substring(0,11)+"0";
		}
		end = end.substring(0,11)+"0";
		int num = 0;
		String sql = "";
		//type:1、分 2、小时 3、天 4、周  5、月
		if(type == 1){
			try {
				num = DateUtil.getDifTime(start, end)/TIME_DIF+1;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql = "select * from t_pay_money_num where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?";
		}else if(type == 2){
			try {
				num = DateUtil.getDifHour(start, end) + 1;
				sql = "select act_type,substr(collect_time,0,10) collect_time,sum(money) money " +
				"from t_pay_money_num " +
				"where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?" +
				"group by act_type,substr(collect_time,0,10)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(type == 3||type == 4){
			try {
				if(type == 3)
					num = DateUtil.getDifDay(start, end) + 1;
				else if(type == 4)
					num = DateUtil.getDifDay(start, end)/7 + 1;
				sql = "select act_type,substr(collect_time,0,8) collect_time,sum(money) money " +
				"from t_pay_money_num " +
				"where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?" +
				"group by act_type,substr(collect_time,0,8)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(type == 5){
			try {
				num = DateUtil.getDifMonth(start, end) + 1;
				sql = "select act_type,substr(collect_time,0,6) collect_time,sum(money) money " +
				"from t_pay_money_num " +
				"where substr(collect_time,0,12)>=? and substr(collect_time,0,12)<=?" +
				"group by act_type,substr(collect_time,0,6)";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Map<Type,List> listMap = new HashMap<Type,List>();
		List<Map<String, String>> list = dbHelper.getMapList(sql,new Object[]{start.substring(0,12),end.substring(0,12)});
		// 列名称
		List<String> seriesList = new ArrayList<String>();
		List<Double> cashList=new ArrayList<Double>();
		List<Double> coinList=new ArrayList<Double>();
		List<Double> balanceList=new ArrayList<Double>();
		List<Double> scoreList=new ArrayList<Double>();
		List<PayMoneyInfo> orderList = new ArrayList<PayMoneyInfo>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				PayMoneyInfo order = new PayMoneyInfo();
				order.setId(map.get("seq_id"));
				order.setType(map.get("act_type"));
				order.setCollectTime(map.get("collect_time"));
				order.setMoney(Double.parseDouble(map.get("money")));
				orderList.add(order);
			}
		}
		if(orderList.size()>0&&num>0){
			//存入时间
			for(int i=0;i<num;i++){
				Double cashvalue = 0D;
				Double coinvalue = 0D;
				Double balancevalue = 0D;
				Double scorevalue = 0D;
				String time = "";
				if(type==1){
					time = DateUtil.getTime(start, i * TIME_DIF);
				}else if(type==2){
					time = DateUtil.getTime(start, i * TIME_DIF * 6).substring(0,10);
				}else if(type==3){
					time = DateUtil.getTime(start, i * TIME_DIF * 6 * 24)
					.substring(0, 8);
				}else if (type == 4) {
					time = DateUtil.getTime(start, i * TIME_DIF * 6 * 24 * 7)
					.substring(0, 8);
				}else if (type == 5) {
					time = DateUtil.getMonth(start, i).substring(0, 6);
				}
				String showTime = DateUtil.getShowTime(time);
				String endTime = time;
				if(type == 4){
					endTime = DateUtil.getTime(start, (i * 7 + 6) * TIME_DIF * 6 * 24)
					.substring(0, 8);
					int dif = 0;
					try {
						dif = DateUtil.getDifTime(endTime, end.substring(0, 8));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if(dif<0){
						endTime = end.substring(0, 8);
					}
					showTime = showTime + "~" + DateUtil.getShowTime(endTime);
				}
				for(PayMoneyInfo o : orderList){
					if(type==1){
						if(o.getCollectTime().substring(0,12).equals(time)){
							if(o.getType().equals(Type.CASH.getType())){
								cashvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.COIN.getType())){
								coinvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.BALANCE.getType())){
								balancevalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.SCORE.getType())){
								scorevalue = ChartDataUtil.doubleFormat(o.getMoney()/67,"#.##");
							}
						}
					}else if(type == 2){
						if(o.getCollectTime().substring(0,10).equals(time)){
							if(o.getType().equals(Type.CASH.getType())){
								cashvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.COIN.getType())){
								coinvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.BALANCE.getType())){
								balancevalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.SCORE.getType())){
								scorevalue = ChartDataUtil.doubleFormat(o.getMoney()/67,"#.##");
							}
						}
					}else if(type == 3){
						if(o.getCollectTime().substring(0,8).equals(time)){
							if(o.getType().equals(Type.CASH.getType())){
								cashvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.COIN.getType())){
								coinvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.BALANCE.getType())){
								balancevalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.SCORE.getType())){
								scorevalue = ChartDataUtil.doubleFormat(o.getMoney()/67,"#.##");
							}
						}
					}else if(type == 4){
						for(int j=0;j<7;j++){
							String newDate = DateUtil.getTime(time,60*24*j).substring(0,8);
							if(o.getCollectTime().substring(0,8).equals(newDate)){
								if(o.getType().equals(Type.CASH.getType())){
									cashvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
								}
								if(o.getType().equals(Type.COIN.getType())){
									coinvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
								}
								if(o.getType().equals(Type.BALANCE.getType())){
									balancevalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
								}
								if(o.getType().equals(Type.SCORE.getType())){
									scorevalue = ChartDataUtil.doubleFormat(o.getMoney()/67,"#.##");
								}
							}
						}
					}else if(type == 5){
						if(o.getCollectTime().substring(0,6).equals(time)){
							if(o.getType().equals(Type.CASH.getType())){
								cashvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.COIN.getType())){
								coinvalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.BALANCE.getType())){
								balancevalue = ChartDataUtil.doubleFormat(o.getMoney()/100,"#.##");
							}
							if(o.getType().equals(Type.SCORE.getType())){
								scorevalue = ChartDataUtil.doubleFormat(o.getMoney()/67,"#.##");
							}
						}
					}
				}
				cashList.add(cashvalue);
				coinList.add(coinvalue);
				balanceList.add(balancevalue);
				scoreList.add(scorevalue);
				seriesList.add(showTime);
			}
		}
		// 列名称
		listMap.put(Type.FIELD, seriesList);
		listMap.put(Type.CASH, cashList);
		listMap.put(Type.COIN, coinList);
		listMap.put(Type.BALANCE, balanceList);
		listMap.put(Type.SCORE, scoreList);
		return listMap;
	}
	
}
