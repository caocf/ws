package com.cplatform.b2c.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.b2c.dto.UserCenterObj;

/**
 * 发邮件保存信息. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-14 下午4:30:07
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Component
public class MemCacheUserCenterUtil extends AbstractMemCacheWr {

	@Autowired
	AppConfig appConfig;

	/**
	 * 生成找码对象至memcache中，并返回校验码
	 * 
	 * @return
	 */
	public String genCode(String userId, String userName, String type, String email) {
		UserCenterObj userCenterObj = new UserCenterObj(userId, userName, type, email);
		writeObject(userCenterObj.getCode(), type, getTimeout(), userCenterObj);
		return userCenterObj.getCode();
	}

	/**
	 * 删除校验码对象
	 * 
	 * @param code
	 */
	public void delete(String code, String type) {
		UserCenterObj UserCenterObj = readKey(code, type, UserCenterObj.class);
		if (UserCenterObj != null) {
			deleteKey(code, type);
		}
	}

	/**
	 * 验证校验码是否正确
	 * 
	 * @param code
	 * @return
	 */
	public boolean check(String userId, String code, String type) {
		UserCenterObj userCenterObj = readKey(code, type, UserCenterObj.class);
		if (userCenterObj == null) {
			return false;
		}
		return userCenterObj.checkValid(userId, code);
	}

	public UserCenterObj find(String userId, String code, String type) {
		UserCenterObj userCenterObj = readKey(code, type, UserCenterObj.class);
		if (userCenterObj == null) {
			return null;
		}
		boolean result = userCenterObj.checkValid(userId, code);
		if (result) {
			return userCenterObj;
		}
		return null;
	}

	/**
	 * 生存时间
	 * 
	 * @return
	 */
	private int getTimeout() {
		return 30 * 60;
	}

	/**
	 * 绑定邮箱设置key值前缀
	 */
	@Override
	String bindEmailKeyPre() {
		return "sc:bind-email:";
	}

	/**
	 * 修改邮箱设置key值前缀
	 */
	@Override
	String editEmailKeyPre() {
		return "sc:edit-email";
	}
}
