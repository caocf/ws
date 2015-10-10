package com.cplatform.mall.back.store.entity;

// default package

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 渠道商、结算商户、业务门店账号表 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午1:33:06
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_SHOP_USER")
public class ShopUser extends IdEntity implements java.io.Serializable {

	/** 初始密码 **/
	public static final String INIT_PWD = "e10adc3949ba59abbe56e057f20f883e";
	/** 账号名称前缀**/
	public static Map<Long, String> prefixMap = null;

	
	private String confirmPass;
	
	static {
		prefixMap = new HashMap<Long, String>();
		prefixMap.put(1l, "SH");//门店
		prefixMap.put(2l, "JS");//商户
		prefixMap.put(3l, "QD");//渠道商 
		
	}
	
	/** 账号状态map */
	private static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("0", "失效");
		statusMap.put("1", "有效");
		statusMap.put("2", "暂停");
	}
	
	/** 类型map */
	private static Map<String, String> typeMap = null;
	static {
		typeMap = new HashMap<String, String>();
		typeMap.put("1", "管理员");
		typeMap.put("2", "普通用户");
		typeMap.put("3", "操作员");
	}
	
	/** 类别map */
	private static Map<String, String> shopClassMap = null;
	static {
		shopClassMap = new HashMap<String, String>();
		shopClassMap.put("1", "业务门店");
		shopClassMap.put("2", "商户");
		shopClassMap.put("3", "渠道商");
	}

	/** 账号名称 **/
	private String code;

	/** 登录密码 **/
	private String pwd;

	/** 状态0--失效 1--有效 2--暂停 **/
	private Long status;

	/** 用户类型1--管理员 2--普通用户 3--操作员 **/
	private Long type;

	/** 邮箱地址 **/
	private String email;

	/** 更新时间 **/
	private String updateTime;
	
	private Long storeId;



	/** 手机号码 **/
	private String mobile;

	/** 昵称 **/
	private String nickName;

	/** 商户类型1--业务门店 2--商户 3--渠道商 **/
	private Long shopClass;

	/** 商户ID **/
	private Long shopId;

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "PWD", length = 32)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "TYPE", precision = 1, scale = 0)
	public Long getType() {
		return type;
	}

	
	@Transient
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "NICK_NAME", length = 20)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "SHOP_CLASS", precision = 1, scale = 0)
	public Long getShopClass() {
		return shopClass;
	}

	public void setShopClass(Long shopClass) {
		this.shopClass = shopClass;
	}

	@Column(name = "SHOP_ID", precision = 9, scale = 0)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	//**********bus method********
	@Transient
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus() + "");
	}
	
	@Transient
	public static Map<String, String> getTypeMap() {
		return typeMap;
	}

	@Transient
	public String getTypeName() {
		return typeMap.get(this.getType() + "");
	}
	
	@Transient
	public static Map<String, String> getShopClassMap() {
		return shopClassMap;
	}

	@Transient
	public String getShopClassName() {
		return shopClassMap.get(this.getShopClass() + "");
	}
	
	private String name;
	
	public void setName(String name) {
	    this.name = name;
    }
	@Transient
	public String getName() {
	    return name;
    }
	
	
	@Transient
	public String getConfirmPass() {
		return confirmPass;
	}

	@Transient
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
}