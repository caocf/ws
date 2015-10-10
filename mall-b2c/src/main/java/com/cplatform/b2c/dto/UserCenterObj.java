package com.cplatform.b2c.dto;

import org.apache.commons.lang.RandomStringUtils;

import com.alibaba.druid.util.StringUtils;

/**
 * 个人中心发送邮件等. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-7 上午9:30:45
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
public class UserCenterObj {

	// 绑定邮箱
	public static final String BIND_EMAIL = "BIND_EMAIL";

	// 修改邮箱
	public static final String EDIT_EMAIL = "EDIT_EMAIL";

	// 修改手机号第一步
	public static final String EDIT_MOBILE_STEP1 = "EDIT_MOBILE_STEP1";

	// 修改手机号第二步
	public static final String EDIT_MOBILE_STEP2 = "EDIT_MOBILE_STEP2";

	private String userId; // 用户编号

	private String userName; // 用户姓名

	private String type; // 操作类型

	private String code; // 生成码

	private String email; // 邮箱

	public UserCenterObj() {
	}

	public UserCenterObj(String userId, String userName, String type, String email) {
		this.userId = userId;
		this.code = RandomStringUtils.randomAlphanumeric(32);
		this.userName = userName;
		this.type = type;
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean checkValid(String userId, String code) {
		if (StringUtils.equals(code, this.code) && StringUtils.equals(userId, this.userId)) {
			return true;
		}
		return false;
	}

}
