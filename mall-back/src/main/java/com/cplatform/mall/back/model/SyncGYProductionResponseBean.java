package com.cplatform.mall.back.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品协议同步高阳接口返回封装类 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-7-3 上午10:00:02
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public class SyncGYProductionResponseBean {

	private String capitaltype;

	private String merchid;

	private String productionid;

	private String status;

	private String msg;

	private String code;

	private Long settleId;

	public boolean isSyncSuccess() {
		return "URM00000".equals(this.code);
	}

	private static Map<String, Long> statusMap = null;
	static {// 高阳状态内部状态转换
		statusMap = new HashMap<String, Long>();
		statusMap.put("1", 3L);
		statusMap.put("2", 5L);
		statusMap.put("3", 2L);
		statusMap.put("4", 4L);
		statusMap.put("5", 0L);
		statusMap.put("6", 1L);
	}

	public String getProductionid() {
		return productionid;
	}

	public void setProductionid(String productionid) {
		this.productionid = productionid;
	}

	public Long getStatusMap() {
		return this.statusMap.get(this.status);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setCapitaltype(String capitaltype) {
		this.capitaltype = capitaltype;
	}

	public String getCapitaltype() {
		return capitaltype;
	}

	public void setMerchid(String merchid) {
		this.merchid = merchid;
	}

	public String getMerchid() {
		return merchid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public Long getSettleId() {
		return settleId;
	}

	public void setSettleId(Long settleId) {
		this.settleId = settleId;
	}

}
