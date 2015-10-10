package com.cplatform.mall.back.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;
import com.cplatform.mall.back.websys.entity.SysPos;
/**
 * 
 * 业务门店终端关系表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-31 下午03:58:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SHOP_POS_LINK")
public class ShopPosLink extends IdEntity implements java.io.Serializable {
	
	/** 序列 **/
	private Long id;
	/** 门店ID **/
	private Long shopId;
	/** 终端ID **/
	private Long posId;
	/** 终端设备号 **/
	private String posNo;
	/** 终端标识 **/
	private String terminalId;
	
	public void setShopId(Long shopId) {
	    this.shopId = shopId;
    }
	@Column(name = "SHOP_ID")
	public Long getShopId() {
	    return shopId;
    }
	
	public void setPosId(Long posId) {
	    this.posId = posId;
    }
	@Column(name = "POS_ID")
	public Long getPosId() {
	    return posId;
    }
	
	public void setPosNo(String posNo) {
	    this.posNo = posNo;
    }
	@Column(name = "POS_NO")
	public String getPosNo() {
	    return posNo;
    }
	

	public void setTerminalId(String terminalId) {
	    this.terminalId = terminalId;
    }
	@Column(name = "TERMINAL_ID")
	public String getTerminalId() {
	    return terminalId;
    }
	// -------------bus method ---------------------
	private String shopName;
	private String posName;
	private SysPos sysPos;
	@Transient
	public void setShopName(String shopName) {
	    this.shopName = shopName;
    }
	@Transient
	public String getShopName() {
	    return shopName;
    }
	@Transient
	public void setPosName(String posName) {
	    this.posName = posName;
    }
	@Transient
	public String getPosName() {
	    return posName;
    }
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POS_ID", insertable = false, updatable = false)
	public SysPos getSysPos() {
		return sysPos;
	}

	public void setSysPos(SysPos sysPos) {
		this.sysPos = sysPos;
	}
}
