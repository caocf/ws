package com.cplatform.mall.back.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.cplatform.com.message.SmsMtMessage;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.back.utils.AppConfig;

/**
 * 发送jms消息服务
 * <p/>
 * Copyright: Copyright (c) 13-8-1 下午2:03
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class JmsMessageService {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private DbHelper dbHelper;

	/**
	 * 短消息发送
	 * 
	 * @param message
	 *            短信内容
	 * @param terminalId
	 *            目标手机号
	 * @throws JmsException
	 * @throws SQLException
	 */
	public void sendSms(String message, String terminalId) throws JmsException,
			SQLException {
		String areaCode = queryAreaCode(terminalId);
		if (areaCode == null)
			return;

		SmsMtMessage mt = new SmsMtMessage("新商城管理后台", null);
		mt.setAreaCode(areaCode); // 接收方地区编码
		mt.setFrom(appConfig.getJmsSmsSpcode()); // 发送用特服号
		mt.setCharge(terminalId); // 接收方号码
		mt.setTo(terminalId); // 接收方号码
		mt.setServiceId("FREE"); // 计费编码
		mt.setFeeType(SmsMtMessage.FEE_TYPE_FREE); // 计费方式
		// 取值范围：SmsMtMessage.FEE_TYPE_**
		mt.setFee(0); // 计费金额
		mt.setNeedMtReport(true); // 是否要状态报告
		mt.setOperatorCode("JSYD"); // 运营商编码
		mt.setContent(message); // 短信内容
		jmsTemplate.convertAndSend(appConfig.getJmsSmsDestination(), mt);
	}

	private String queryAreaCode(String terminalId) throws SQLException {
		if (terminalId == null || terminalId.length() != 11) {
			return null;
		}
		String sql = "select area_code from t_sys_segment where segment_code = ?";
		String areaCode = dbHelper.queryScalar(sql, terminalId.substring(0, 7));
		return areaCode;
	}
}
