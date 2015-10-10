package com.cplatform.mall.dc.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cplatform.mall.dc.dao.DcLoginLogDao;
import com.cplatform.mall.dc.dao.DcOpLogDao;
import com.cplatform.mall.dc.entity.DcLoginLog;
import com.cplatform.mall.dc.entity.DcOpLog;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.util2.TimeStamp;

/**
 * 日志工具类<br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午6:51:44
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
@Component
public class LogUtils {

	@Autowired
	private DcOpLogDao dcOpLogDao;

	@Autowired
	private DcLoginLogDao loginLogDao;

	/**
	 * 查看
	 */
	public static final long OP_TYPE_QUERY = 1L;

	/**
	 * 增加
	 */
	public static final long OP_TYPE_INSERT = 2L;

	/**
	 * 修改
	 */
	public static final long OP_TYPE_UPDATE = 3L;

	/**
	 * 删除
	 */
	public static final long OP_TYPE_DELETE = 4L;

	/**
	 * 审核
	 */
	public static final long OP_TYPE_VERIFY = 5L;

	/**
	 * 其他
	 */
	public static final long OP_TYPE_OTHER = 6L;

	/**
	 * 记录登录日志
	 * 
	 * @param sessionUser
	 *            SessionUser
	 */
	public void recordLoginLog(SessionUser sessionUser) {
		if (sessionUser != null) {
			Long userId = sessionUser.getId();
			if (userId != 0L) {
				DcLoginLog log = new DcLoginLog();
				log.setUserId(userId);
				log.setUserCode(sessionUser.getCode());
				log.setUserName(sessionUser.getName());
				log.setLoginTime(TimeStamp.getTime(14));

				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				log.setIp(IpAddress.getipaddr(request));

				loginLogDao.save(log);
			}
		}
	}

	/**
	 * 记录操作日志
	 * 
	 * @param module
	 *            操作模块
	 * @param opObject
	 *            操作对象
	 * @param opType
	 *            操作类型
	 */
	public void recordOpLog(String module, String opObject, Long opType) {
		SessionUser sessionUser = SessionUser.getSessionUser();
		if (sessionUser != null) {
			Long userId = sessionUser.getId();
			if (userId != 0L) {
				DcOpLog log = new DcOpLog();
				log.setUserId(String.valueOf(userId));
				log.setUserName(sessionUser.getName());
				log.setModule(module);
				log.setOpObject(opObject);
				log.setOpType(opType);
				log.setOpTime(TimeStamp.getTime(14));
				dcOpLogDao.save(log);
			}
		}
	}
}
