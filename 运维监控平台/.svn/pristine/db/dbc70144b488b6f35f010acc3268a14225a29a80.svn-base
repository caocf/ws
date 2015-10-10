package com.cplatform.mall.dc.utils;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.util2.mail.SendMail;

/**
 * 
 * @Title. 邮件发送类<br>
 * @Description.
 * <p>
 * @Copyright: Copyright (c) May 16, 2009 3:19:29 PM
 * <p>
 * @Company: 北京宽连十方数字技术有限公司
 * <p>
 * @Author: JinLei
 * <p>
 * @Version: 1.0
 * <p>
 * @History: 
 * <p>
 */
@Service
public class MailSender {

	private final Logger logger = Logger.getLogger(MailSender.class);
	
	@Autowired
	private AppConfig config;
	
	/**
	 * 发送邮件
	 * @param toMail 接收方邮件地址
	 * @param toName 接收方姓名
	 * @param subject 邮件标题
	 * @param msg 邮件内容
	 */
	public void sendMail(String toMail,String subject,String msg){
		sendMails(toMail,"",subject,msg);
	}
	
	/**
	 * 发送邮件
	 * @param toMail
	 * @param ccMailArr
	 * @param subject
	 * @param msg
	 */
	public void sendMails(String toMail,String[] ccMailArr,String subject,String msg){		
		String ccMails = "";		
		if( (ccMailArr != null) && (ccMailArr.length > 0) ){
			ccMails = StringUtil.join(ccMailArr,",");
		}		
		sendMails(toMail,ccMails,subject,msg);
	}
	
	/**
	 * 发送邮件
	 * @param toMail
	 * @param ccMailList
	 * @param subject
	 * @param msg
	 */
	public boolean sendMails(String toMail,List<String> ccMailList,String subject,String msg){		
		String ccMails = "";		
		if( (ccMailList != null) && (ccMailList.size() > 0) ){
			ccMails = StringUtil.join(ccMailList.toArray(new String[1]),",");
		}		
		return sendMails(toMail,ccMails,subject,msg);
	}
	
	/**
	 * 发送邮件
	 * @param toMail
	 * @param ccMails
	 * @param subject
	 * @param msg
	 */
	public boolean sendMails(String toMail,String ccMails,String subject,String msg){
		//邮件服务器信息
		String mailHost = config.getMailHost();
		String mailUser = config.getMailUser();
		String mailPwd = config.getMailPwd();
		return sendMails(mailHost,mailUser,mailPwd,toMail,ccMails,subject,msg);
	}
	
	/**
	 * 邮件发送
	 * @param mailHost 邮件服务器地址
	 * @param mailFrom 发送方邮件地址
	 * @param mailUser 邮件服务器登录用户名
	 * @param mailPwd 邮件服务器登录密码
	 * @param toMail 接收方mail地址
	 * @param toName 接收方姓名
	 * @param subject 邮件标题
	 * @param msg 邮件内容
	 */
	public boolean sendMails(String mailHost,String mailUser,String mailPwd,
			String toMail,String ccMails,String subject,String msg){
		SendMail txtMail = null;
		try{
			txtMail = new SendMail();
			txtMail.setAccount(mailHost, mailUser, mailPwd);
			return txtMail.sendHtmlMail(toMail,ccMails, "", subject, msg, null); 
		}catch(Exception ex){
			logger.error("邮件发送异常!", ex);
		}finally{
			txtMail = null;
		}
		return false;
	}
	
	
	/**
	 * 发送附件邮件
	 * @param toMail
	 * @param ccMails
	 * @param subject
	 * @param msg
	 * @param fileList
	 * @return
	 */
	public boolean sendMailFile(String toMail,String ccMails,String subject,String msg,Vector<String> fileList){
		String mailHost = config.getMailHost();
		String mailUser = config.getMailUser();
		String mailPwd = config.getMailPwd();
		
		SendMail txtMail = null;
		try{
			txtMail = new SendMail();
			txtMail.setAccount(mailHost, mailUser, mailPwd);
			return txtMail.sendHtmlMail(toMail,ccMails, "", subject, msg, fileList); 
		}catch(Exception ex){
			logger.error("邮件发送异常!", ex);
		}finally{
			txtMail = null;
		}
		return false;
	}
	
	
	public void main(String[] args){
		Vector v = new Vector();
		v.add("E:/mms.zip");
		v.add("E:/mms1.zip");
		SendMail mail = new SendMail();
		mail.setAccount("mail.c-platform.com", "sys-service@c-platform.com", "cplatform");
		mail.sendHtmlMail("guyu@c-platform.com","","", "学习", "学习", v);
	}
	
}
