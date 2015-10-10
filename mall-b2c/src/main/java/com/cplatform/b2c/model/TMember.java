package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户信息. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-20 上午10:51:35
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "t_member")
public class TMember {

	// 绑定邮箱
	public static final String SESSION_CENTER_USER_BIND_EMAIL_KEY = "session_center_bind_email_key_";

	// 绑定手机号
	public static final String SESSION_CENTER_USER_BIND_MOBLIE_KEY = "session_center_bind_moblie_key_";

	private Long id;

	private String userName;

	private String userPass;

	private String email;

	private String realName;

	private String terminalId;

	private String regTime;

	private int status;

	private String updateTime;

	private String vid;

	private String nickName;

	private String areaCode;

	private Integer sex;

	private String birthday;

	private Integer opened;

	private String qq;

	private String remark;

	private String signature;

	private Integer regSource;

	private String userLevel;

	private String cartUuid;

	private Integer redMember;

	private Integer subRegSource;

	private String avatar;

	private String imageX;

	private String imageY;

	private String imageWidth;

	private String imageHeight;

	private String scale;

	private String odlPwd;

	private String userPassAg;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen")
	@SequenceGenerator(name = "gen", sequenceName = "SEQ_MEMBER")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_PASS")
	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "REAL_NAME")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "TERMINAL_ID")
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "REG_TIME")
	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	@Column(name = "NICK_NAME")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getOpened() {
		return opened;
	}

	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "REG_SOURCE")
	public Integer getRegSource() {
		return regSource;
	}

	public void setRegSource(Integer regSource) {
		this.regSource = regSource;
	}

	@Column(name = "USER_LEVEL")
	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	@Column(name = "CART_UUID")
	public String getCartUuid() {
		return cartUuid;
	}

	public void setCartUuid(String cartUuid) {
		this.cartUuid = cartUuid;
	}

	@Column(name = "RED_MEMBER")
	public Integer getRedMember() {
		return redMember;
	}

	public void setRedMember(Integer redMember) {
		this.redMember = redMember;
	}

	public Integer getSubRegSource() {
		return subRegSource;
	}

	public void setSubRegSource(Integer subRegSource) {
		this.subRegSource = subRegSource;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Transient
	public String getImageX() {
		return imageX;
	}

	public void setImageX(String imageX) {
		this.imageX = imageX;
	}

	@Transient
	public String getImageY() {
		return imageY;
	}

	public void setImageY(String imageY) {
		this.imageY = imageY;
	}

	@Transient
	public String getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	@Transient
	public String getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}

	@Transient
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getOdlPwd() {
		return odlPwd;
	}

	public void setOdlPwd(String odlPwd) {
		this.odlPwd = odlPwd;
	}

	public String getUserPassAg() {
		return userPassAg;
	}

	public void setUserPassAg(String userPassAg) {
		this.userPassAg = userPassAg;
	}
}
