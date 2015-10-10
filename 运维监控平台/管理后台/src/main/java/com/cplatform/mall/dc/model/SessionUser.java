package com.cplatform.mall.dc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cplatform.mall.dc.entity.DcArea;

/**
 * session中使用的用户对象<br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午6:48:49
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
public class SessionUser implements Serializable {

	public static final String SESSION_USER_KEY = "session_dc_user_key__";

	/** 图片验证码 session key */
	public static final String SESSION_IMAGE_CODE_KEY = "rand";

	private static final long serialVersionUID = -8834343678734190594L;

	private Long id;

	private String code;

	private String name;

	private String terminalId;

	private String email;

	private int status;

	private int type;

	private Long userId;

	private List<DcArea> areas;

	public List<DcArea> getAreas() {
		return areas;
	}

	public void setAreas(List<DcArea> areas) {
		this.areas = areas;
	}

	/**
	 * 地市数据查看权限
	 */
	private String[] listArea;

	public String[] getListArea() {
		return listArea.clone();
	}

	public void setListArea(String[] listArea) {
		if(listArea!=null)
			this.listArea = listArea.clone();
	}

	private Map<String, MenuPrivilege> privilege;

	private List<StackSysMenu> menus;

	private List<Long> roleId = new ArrayList<Long>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Map<String, MenuPrivilege> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Map<String, MenuPrivilege> privilege) {
		this.privilege = privilege;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StackSysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<StackSysMenu> menus) {
		this.menus = menus;
	}

	public List<Long> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

	/**
	 * 是否是超级管理员
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return 1 == type;
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

}
