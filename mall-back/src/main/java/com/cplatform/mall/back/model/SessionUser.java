package com.cplatform.mall.back.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.entity.SysUnit;
import com.cplatform.mall.back.sys.entity.SysUser;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 下午2:06
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class SessionUser implements Serializable {

	private static final long serialVersionUID = -3911707577350340265L;

	public static final String SESSION_USER_KEY = "session_user_key__";

	private Long id;

	private String name;

	private String mobile;

	private String email;

	private int status;

	private int userType;

	/** 登录用户对应单位 */
	private SysUnit sysUnit;

	/** 登录用户单位id */
	private Long unitId;

	private Map<String, MenuPrivilege> privilege;

	/** 子单位 */
	private List<SysUnit> chidrenUnitList;

	/** 地区列表 */
	private List<SysRegion> areaList;

	/** 系统用户 */
	private SysUser sysUser;

	/**
	 * 是否是超级管理员
	 * 
	 * @return
	 */
	public boolean isSuperAdmin() {
		// return "0".equals(userType);
		return 0 == userType;
	}

	/**
	 * 获得当前登录用户信息
	 * 
	 * @return
	 */
	public static SessionUser getSessionUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return (SessionUser) session.getAttribute(SESSION_USER_KEY);
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return getSessionUser() != null;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, MenuPrivilege> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Map<String, MenuPrivilege> privilege) {
		this.privilege = privilege;
	}

	public SysUnit getSysUnit() {
		return sysUnit;
	}

	public void setSysUnit(SysUnit sysUnit) {
		this.sysUnit = sysUnit;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long untiId) {
		this.unitId = untiId;
	}

	public List<SysUnit> getChidrenUnitList() {
		return chidrenUnitList;
	}

	public void setChidrenUnitList(List<SysUnit> chidrenUnitList) {
		this.chidrenUnitList = chidrenUnitList;
	}

	public List<SysRegion> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<SysRegion> areaList) {
		this.areaList = areaList;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}
