package com.cplatform.b2c.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.util.AppConfig;

/**
 * 发送邮件. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-6 下午5:23:06
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class EmailMessageService {

	private final Logger logger = LoggerFactory.getLogger(EmailMessageService.class);

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	@Autowired
	private SimpleMailMessage simpleMailMessage;

	@Autowired
	private AppConfig appConfig;

	/**
	 * 邮件发送
	 * 
	 * @param to
	 * @param text
	 */
	public void send(String to, String text) {
		logger.info("开始发送邮件：" + to);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setText(text);
		javaMailSenderImpl.send(simpleMailMessage);
		logger.info("邮件发送成功");
	}
}
