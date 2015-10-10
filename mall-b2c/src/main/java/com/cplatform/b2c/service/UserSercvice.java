package com.cplatform.b2c.service;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.cplatform.b2c.model.TOrderWeiBoShare;
import com.cplatform.b2c.model.TSupplierApply;
import com.cplatform.b2c.model.TUserExchangeLog;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CTime;
import com.cplatform.b2c.util.HttpClientUtils;

@Service
@Transactional
public class UserSercvice {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private SessionFactory sessionFactory;

	private static final Log logger = LogFactory.getLog(UserSercvice.class);

	public final static Map<String, String> BRAN_STRINGS = new HashMap<String, String>();
	static {
		BRAN_STRINGS.put("1", "全球通");
		BRAN_STRINGS.put("2", "金卡快捷通");
		BRAN_STRINGS.put("3", "快捷通");
		BRAN_STRINGS.put("4", "随e行");
		BRAN_STRINGS.put("5", "神州行大众卡");
		BRAN_STRINGS.put("6", "动感地带");
		BRAN_STRINGS.put("7", "神州行金卡");
		BRAN_STRINGS.put("8", "神州行新春卡");
		BRAN_STRINGS.put("9", "神州行长途卡");
		BRAN_STRINGS.put("10", "神州行家园卡");
		BRAN_STRINGS.put("11", "动感地带2");
		BRAN_STRINGS.put("12", "神州行标准卡");
		BRAN_STRINGS.put("13", "A类行业应用卡");
		BRAN_STRINGS.put("14", "B类行业应用卡");
		BRAN_STRINGS.put("15", "C类行业应用卡");
		BRAN_STRINGS.put("99", "不限品牌");
	}

	public boolean exchangePoints(String mobile, String ip, String type, String amount) throws Exception {
		try {
			// 兑换接口url
			StringBuilder param = new StringBuilder();
			param.append("mobile=");
			if (!StringUtils.isEmpty(mobile)) {
				param.append(mobile);
			}
			param.append("&ip=");
			if (!StringUtils.isEmpty(ip)) {
				param.append(ip);
			}
			param.append("&type=");
			if (!StringUtils.isEmpty(type)) {
				param.append(type);
			}
			param.append("&amount=");
			if (!StringUtils.isEmpty(amount)) {
				param.append(amount);
			}
			String response = HttpClientUtils.httpGet(appConfig.getExchange_Points_Url(), param.toString(), 0, 0);
			// 解析返回的xml
			StringReader sReader = new StringReader(response);
			InputSource iSource = new InputSource(sReader);
			Document document = (new SAXBuilder()).build(iSource);
			Element root = document.getRootElement();
			List<Element> messList = root.getChildren();

			boolean flag = false;
			if (messList == null || messList.size() < 0) {
				return flag;
			}

			String code = "";
			String message = "";
			for (int i = 0; i < messList.size(); i++) {
				if ("code".equals(messList.get(i).getName())) {
					code = messList.get(i).getText();
				}
				if ("message".equals(messList.get(i).getName())) {
					message = messList.get(i).getText();
				}
			}
			if (code.equals("0")) {
				flag = true;
			}
			// 记录兑换商城币日志
			TUserExchangeLog exchangeLog = new TUserExchangeLog();
			exchangeLog.setTerminalId(mobile);
			exchangeLog.setUserIp(ip);
			exchangeLog.setAmount(Integer.parseInt(amount));
			exchangeLog.setExcPercent(appConfig.getExchange_Percent());
			exchangeLog.setrCode(Integer.parseInt(code));
			exchangeLog.setrMsg(message);
			exchangeLog.setExcDate(CTime.getTime(14));

			sessionFactory.getCurrentSession().save(exchangeLog);

			return flag;
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Map<String, String> queryExchangePoints(String mobile) throws Exception {
		// 兑换查询接口url
		StringBuilder param = new StringBuilder();
		param.append("mobile=");
		if (!StringUtils.isEmpty(mobile)) {
			param.append(mobile);
		}
		logger.info("获取用户积分接口开始============" + appConfig.getExchange_Query_Url() + "?" + param.toString());
		String response = HttpClientUtils.httpGet(appConfig.getExchange_Query_Url(), param.toString(), 0, 0);
		logger.info("获取用户积分接口结束============" + response);
		// String response =
		// "{\"brand\":\"2\",\"coin\":\"\",\"score\":\"32\",\"statusCode\":0,\"statusText\":\"查询成功\"}";
		Map<String, String> returnMap = new HashMap<String, String>();
		if (response != null && !response.equals("")) {
			try {
				JSONObject jsonObject = JSONObject.fromObject(response);
				if (!jsonObject.isEmpty()) {
					returnMap.put("statusCode", jsonObject.getInt("statusCode") + "");
					returnMap.put("statusText", jsonObject.getString("statusText"));
					returnMap.put("brand", BRAN_STRINGS.get(jsonObject.get("brand") + ""));
					returnMap.put("score", jsonObject.getInt("score") + "");
				}
			}
			catch (Exception e) {
				throw new Exception(e);
			}
		}

		return returnMap;
	}

	public List<TUserExchangeLog> getExchangeLogs(String terminalId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TUserExchangeLog.class);
		criteria.add(Restrictions.eq("terminalId", terminalId));
		criteria.add(Restrictions.eq("rCode", 0));
		criteria.addOrder(Order.desc("excDate"));
		List<TUserExchangeLog> exchangeLogs = criteria.list();

		return exchangeLogs;
	}

	public static String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public Map<String, String> queryMyCoin(String mobile) throws Exception {
		// 兑换查询接口url
		StringBuilder param = new StringBuilder();
		param.append("mobile=");
		if (!StringUtils.isEmpty(mobile)) {
			param.append(mobile);
		}
		logger.info("获取用户商城币接口开始============" + appConfig.getInterfaceCoinInfo() + "?" + param.toString());
		String response = HttpClientUtils.httpGet(appConfig.getInterfaceCoinInfo(), param.toString(), 0, 0);
		logger.info("获取用户商城币接口开始============" + response);
		Map<String, String> returnMap = new HashMap<String, String>();
		if (response != null && !response.equals("")) {
			try {
				JSONObject jsonObject = JSONObject.fromObject(response);
				if (!jsonObject.isEmpty()) {
					returnMap.put("statusCode", jsonObject.getInt("statusCode") + "");
					returnMap.put("coin", jsonObject.getInt("coin") + "");
				}
			}
			catch (Exception e) {
				throw new Exception(e);
			}
		}

		return returnMap;
	}

	public void insertWeiBoShare(TOrderWeiBoShare orderWeiBoShare) {
		sessionFactory.getCurrentSession().saveOrUpdate(orderWeiBoShare);
	}

	/**
	 * 保存供应商
	 * 
	 * @param tSupplierApply
	 */
	public boolean saveTSupplierApply(TSupplierApply tSupplierApply) {
		try {
			sessionFactory.getCurrentSession().save(tSupplierApply);
		}
		catch (Exception e) {
			logger.info("保存供应商申请信息出错：" + e.getMessage());
			return false;
		}
		return true;
	}
}
