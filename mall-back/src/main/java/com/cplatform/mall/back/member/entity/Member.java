package com.cplatform.mall.back.member.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

import com.cplatform.mall.back.sys.entity.SysRegion;

/**
 * 会员管理表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 下午07:46:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_MEMBER")
public class Member implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 2542760343492839808L;

	/** 会员状态map */
	private static Map<String, String> statusMap = null;
	static {
		statusMap = new HashMap<String, String>();
		statusMap.put("1", "正常");
		statusMap.put("2", "暂停");
	}

	/** 性别map */
	private static Map<String, String> sexMap = null;
	static {
		sexMap = new HashMap<String, String>();
		sexMap.put("1", "女");
		sexMap.put("2", "男");
	}

	/** 信息是否公开map */
	private static Map<String, String> openedMap = null;
	static {
		openedMap = new HashMap<String, String>();
		openedMap.put("0", "不公开");
		openedMap.put("1", "公开");
	}

	/** 会员级别map */
	private static Map<String, String> userLevelMap = null;
	static {
		userLevelMap = new HashMap<String, String>();
		userLevelMap.put("0", "普通会员");
		userLevelMap.put("1", "定制会员");
		userLevelMap.put("2", "VIP会员");
	}

	@Transient
	public static Map<String, String> getStatusMap() {
		return statusMap;
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.getStatus() + "");
	}

	@Transient
	public static Map<String, String> getSexMap() {
		return sexMap;
	}

	@Transient
	public String getSexName() {
		return sexMap.get(this.getSex() + "");
	}

	@Transient
	public static Map<String, String> getOpenedMap() {
		return openedMap;
	}

	@Transient
	public String getOpenedName() {
		return openedMap.get(this.getOpened() + "");
	}

	@Transient
	public static Map<String, String> getUserLevelMap() {
		return userLevelMap;
	}

	@Transient
	public String getUserLevelName() {
		return userLevelMap.get(this.getUserLevel() + "");
	}

	// 会员状态
	/**
	 * 会员状态 － 正常
	 */
	public static final String STATUS_1 = "1";

	/**
	 * 会员状态 － 暂停
	 */
	public static final String STATUS_2 = "2";

	// 性别
	/**
	 * 性别-女
	 */
	public static final Long SEX_1 = 1L;

	/**
	 * 性别-男
	 */
	public static final Long SEX_2 = 2L;

	// 信息是否公开
	/**
	 * 信息是否公开-不公开
	 */
	public static final Long OPENED_0 = 0L;

	/**
	 * 信息是否公开-公开
	 */
	public static final Long OPENED_1 = 1L;

	// 会员级别
	/**
	 * 会员级别-普通会员
	 */
	public static final String USER_LEVEL_0 = "0";

	/**
	 * 会员级别-定制会员
	 */
	public static final String USER_LEVEL_1 = "1";

	/**
	 * 会员级别-VIP会员
	 */
	public static final String USER_LEVEL_2 = "2";

	/** 序列 **/
	private Long id;

	/** 用户登录名 **/
	private String userName;

	/** 用户密码 **/
	private String userPass;

	/** 用户邮箱 **/
	private String email;

	/** 真实姓名 **/
	private String realName;

	/** 手机号码 **/
	private String terminalId;

	/** 注册时间 **/
	private String regTime;

	/** 状态 1正常 2暂停 **/
	private String status;

	/** 更新时间 **/
	private String updateTime;

	/** JSSSO VID **/
	private String vid;

	/** 昵称 **/
	private String nickName;

	/** 归属地区代码 **/
	private String areaCode;

	/** 性别1女2男 **/
	private Long sex;

	/** 生日 **/
	private String birthday;

	/** 信息是否公开 0不公开 1公开 **/
	private Long opened;

	/** QQ **/
	private String qq;

	/** 个人信息描述 **/
	private String remark;

	/** 个性签名 **/
	private String signature;

	/** 注册来源 1WEBSSO **/
	private Long regSource;

	/** 会员级别 0普通会员 1定制会员 2VIP会员 **/
	private String userLevel;

	/** 购物车标识 **/
	private String cartUuid;

	public void setId(Long id) {
		this.id = id;
	}

	@SequenceGenerator(name = "seq_member", sequenceName = "SEQ_MEMBER")
	@Id
	@GeneratedValue(generator = "seq_member")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Column(name = "USER_PASS")
	public String getUserPass() {
		return userPass;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "REAL_NAME")
	public String getRealName() {
		return realName;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "TERMINAL_ID")
	public String getTerminalId() {
		return terminalId;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	@Column(name = "REG_TIME")
	public String getRegTime() {
		return regTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	@Column(name = "VID")
	public String getVid() {
		return vid;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "NICK_NAME")
	public String getNickName() {
		return nickName;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	@Column(name = "SEX")
	public Long getSex() {
		return sex;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "BIRTHDAY")
	public String getBirthday() {
		return birthday;
	}

	public void setOpened(Long opened) {
		this.opened = opened;
	}

	@Column(name = "OPENED")
	public Long getOpened() {
		return opened;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "QQ")
	public String getQq() {
		return qq;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "SIGNATURE")
	public String getSignature() {
		return signature;
	}

	public void setRegSource(Long regSource) {
		this.regSource = regSource;
	}

	@Column(name = "REG_SOURCE")
	public Long getRegSource() {
		return regSource;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	@Column(name = "USER_LEVEL")
	public String getUserLevel() {
		return userLevel;
	}

	public void setCartUuid(String cartUuid) {
		this.cartUuid = cartUuid;
	}

	@Column(name = "CART_UUID")
	public String getCartUuid() {
		return cartUuid;
	}

	// ----bus -method---
	private String regTimeBegin;

	private String regTimeEnd;

	private String confirmPass;

	private SysRegion sysRegion;

	@Transient
	public void setRegTimeBegin(String regTimeBegin) {
		this.regTimeBegin = regTimeBegin;
	}

	@Transient
	public String getRegTimeBegin() {
		return regTimeBegin;
	}

	@Transient
	public void setRegTimeEnd(String regTimeEnd) {
		this.regTimeEnd = regTimeEnd;
	}

	@Transient
	public String getRegTimeEnd() {
		return regTimeEnd;
	}

	@Transient
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	@Transient
	public String getConfirmPass() {
		return confirmPass;
	}

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_CODE", insertable = false, updatable = false)
	public SysRegion getSysRegion() {
		return sysRegion;
	}

	public void setSysRegion(SysRegion sysRegion) {
		this.sysRegion = sysRegion;
	}

}
