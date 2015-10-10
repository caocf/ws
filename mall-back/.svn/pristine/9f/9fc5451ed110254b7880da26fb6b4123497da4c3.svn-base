package com.cplatform.mall.back.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.sys.dao.SysLogDao;
import com.cplatform.mall.back.sys.dao.SysLoginLogDao;
import com.cplatform.mall.back.sys.entity.SysLogInfo;
import com.cplatform.mall.back.sys.entity.SysLoginInfo;
import com.cplatform.util2.TimeStamp;

/**
 * 记录日志的工具类. <br>
 * 记录日志的工具类. Copyright: Copyright (c) May 28, 2007
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baijie
 * <p>
 * Version: 1.0
 */
@Component
public class LogUtils {

	/** 日志类型： 1-查看 2-添加 3-修改 4-删除 5-审核 6-其他 */
	public final Long VIEW = 1L;

	public final Long ADD = 2L;

	public final Long MODIFY = 3L;

	public final Long DELETE = 4L;

	public final Long AUDIT = 5L;

	public final Long OTHER = 6L;

	@Autowired
	private SysLogDao logDao;

	@Autowired
	private SysLoginLogDao loginLogDao;

	/** 日志对象 */
	private Logger logger = Logger.getLogger(LogUtils.class);

	/**
	 * 记录添加类型的系统日志
	 * 
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 */
	public void logAdd(String module, String description) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, ADD, module, description, null);
	}

	public void logAdd(String module, String description, Long opId) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, ADD, module, description, opId);
	}

	/**
	 * 记录审核类型的系统日志
	 * 
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 */
	public void logAudit(String module, String description) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, AUDIT, module, description, null);
	}

	public void logAudit(String module, String description, Long opId) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, AUDIT, module, description, opId);
	}

	/**
	 * 记录删除类型的系统日志
	 * 
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 */
	public void logDelete(String module, String description) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, DELETE, module, description, null);
	}

	public void logDelete(String module, String description, Long opId) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, DELETE, module, description, opId);
	}

	/**
	 * 记录用户登录日志
	 * 
	 * @param userId
	 *            登录用户编号
	 * @param userType
	 *            登录用户类型
	 * @param userCode
	 *            登录用户账号
	 * @param userName
	 *            登录用户名
	 * @param ip
	 * @param logSuccess
	 *            true -登录成功 false -登录失败
	 */
	public void loginLog(Long userId, Integer userType, String userCode, String userName, String ip, boolean logSuccess) {
		SysLoginInfo tSysLoginLog = new SysLoginInfo();
		tSysLoginLog.setUserId(userId);
		tSysLoginLog.setUserType(userType);
		tSysLoginLog.setUserName(userName);
		tSysLoginLog.setIp(ip);
		tSysLoginLog.setLoginTime(TimeStamp.getTime(14));
		tSysLoginLog.setSuccessFlag(logSuccess ? 1 : 0);
		loginLogDao.save(tSysLoginLog);
	}

	/**
	 * 记录修改类型的系统日志
	 * 
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 */
	public void logModify(String module, String description) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, MODIFY, module, description, null);
	}

	public void logModify(String module, String description, Long opid) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, MODIFY, module, description, opid);
	}

	/**
	 * 记录其它类型的系统日志
	 * 
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 */
	public void logOther(String module, String description) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, OTHER, module, description, null);
	}

	public void logOther(String module, String description, Long opid) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, OTHER, module, description, null);
	}

	/**
	 * 记录查看类型的系统日志
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 */
	public void logView(String module, String description) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, VIEW, module, description, null);
	}

	public void logView(String module, String description, Long opid) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		sysLog(request, VIEW, module, description, opid);
	}

	/**
	 * 记录系统日志
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param operType
	 *            操作类型
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 */
	private void sysLog(HttpServletRequest req, Long operType, String module, String description, Long opid) {
		HttpSession session = req.getSession();
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		sysLog(sessionUser.getId(), sessionUser.getUserType(), sessionUser.getName(), operType, module, description, req.getRemoteAddr(), opid);
	}

	/**
	 * 记录系统日志
	 * 
	 * @param userId
	 *            用户编号
	 * @param userType
	 *            用户类型
	 * @param userCode
	 *            用户账号
	 * @param userName
	 *            用户名
	 * @param operType
	 *            操作类型
	 * @param module
	 *            操作模块编号
	 * @param description
	 *            操作内容描述
	 * @param ip
	 *            操作者机器IP
	 */
	public void sysLog(Long userId, int userType, String userName, Long operType, String module, String description, String ip, Long opid) {
		try {
			SysLogInfo tSysLog = new SysLogInfo();
			tSysLog.setUserId(String.valueOf(userId));
			tSysLog.setUserType(String.valueOf(userType));
			tSysLog.setUserName(userName);
			tSysLog.setOperType(String.valueOf(operType));
			tSysLog.setDescription(description);
			tSysLog.setModule(module);
			tSysLog.setIp(ip);
			tSysLog.setOperTime(TimeStamp.getTime(14));
			logger.info(tSysLog.toString());
			logDao.save(tSysLog);
		}
		catch (Exception e) {
			logger.error("记录日志异常-" + module + "\t" + operType + "\t" + description, e);
		}
	}
}
