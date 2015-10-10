package com.cplatform.mall.back.store.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 渠道商代理结算商户关系表Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 下午3:22:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_STORE_AGENT")
public class StoreAgent extends IdEntity implements java.io.Serializable {

	/**
	 * 草稿
	 */
	public static final Long STATUS_SAVE = 0L;

	/**
	 * 待审核
	 */
	public static final Long STATUS_NO_AUDIT = 1L;

	/**
	 * 审核中
	 */

	public static final Long STATUS_AUDITING = 2L;

	/**
	 * 审核通过
	 */

	public static final Long STATUS_AUDIT_PASS = 3L;

	/**
	 * 审核驳回
	 */

	public static final Long STATUS_AUDIT_REJECT = 4L;

	/** 渠道商编号 **/
	private Long qdStoreId;

	/** 商户编号 **/
	private Long jsStoreId;

	/** 代理有效开始时间 **/
	private String startTime;

	/** 代理有效结束时间 **/
	private String stopTime;

	/** 创建后台用户 **/
	private Long sysUserId;

	/** 商户或渠道商名称 **/
	private String storeName;

	/** 是否折扣商户代理 0否 1是 **/
	private Long shopAgentFlag;

	/** 是否商品销售代理 0否 1是 **/
	private Long goodsAgentFlag;

	@Column(name = "SHOP_AGENT_FLAG")
	public Long getShopAgentFlag() {
		return shopAgentFlag;
	}

	public void setShopAgentFlag(Long shopAgentFlag) {
		this.shopAgentFlag = shopAgentFlag;
	}

	@Column(name = "GOODS_AGENT_FLAG")
	public Long getGoodsAgentFlag() {
		return goodsAgentFlag;
	}

	public void setGoodsAgentFlag(Long goodsAgentFlag) {
		this.goodsAgentFlag = goodsAgentFlag;
	}

	@Transient
	public String getShopAgentFlagName() {
		return shopAgentFlagMap.get(this.shopAgentFlag);
	}

	@Transient
	public String getGoodsAgentFlagName() {
		return goodsAgentFlagMap.get(this.goodsAgentFlag);
	}

	/**
	 * 状态 0--草稿 1--待审核 2--审核中 3--审核通过 4--审核驳回
	 **/
	private Long status;

	@Column(name = "QD_STORE_ID", precision = 8, scale = 0)
	public Long getQdStoreId() {
		return qdStoreId;
	}

	public void setQdStoreId(Long qdStoreId) {
		this.qdStoreId = qdStoreId;
	}

	@Column(name = "JS_STORE_ID", precision = 8, scale = 0)
	public Long getJsStoreId() {
		return jsStoreId;
	}

	public void setJsStoreId(Long jsStoreId) {
		this.jsStoreId = jsStoreId;
	}

	@Column(name = "START_TIME", length = 14)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "STOP_TIME", length = 14)
	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	@Column(name = "SYS_USER_ID", precision = 8, scale = 0)
	public Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Transient
	public String getStoreName() {
		return storeName;
	}

	@Transient
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public static Map<Long, String> shopAgentFlagMap = null;

	public static Map<Long, String> goodsAgentFlagMap = null;

	public static Map<Long, String> statusMap = null;
	static {
		shopAgentFlagMap = new HashMap<Long, String>();
		shopAgentFlagMap.put(0l, "否 ");
		shopAgentFlagMap.put(1l, "是");
	}
	static {
		goodsAgentFlagMap = new HashMap<Long, String>();
		goodsAgentFlagMap.put(0l, "否 ");
		goodsAgentFlagMap.put(1l, "是");
	}

	static {
		statusMap = new HashMap<Long, String>();
		statusMap.put(0l, "草稿");
		statusMap.put(1l, "待审核");
		statusMap.put(2l, "审核中");
		statusMap.put(3l, "审核通过");
		statusMap.put(4l, "审核驳回");
	}

	@Transient
	public String getStatusName() {
		return statusMap.get(this.status);
	}

	private String channelName;

	@Transient
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	private String jsStoreIds;

	@Transient
	public String getJsStoreIds() {
		return jsStoreIds;
	}

	public void setJsStoreIds(String jsStoreIds) {
		this.jsStoreIds = jsStoreIds;
	}

}
