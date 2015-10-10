package com.cplatform.mall.back.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cplatform.settle_interface.bean.MerchantFeeInfo;

/**
 * 同步高阳接口返回封装类 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-22 下午12:45:22
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class SyncGYResponseBean {

	private String code;

	public String getCode() {
		return code;
	}

	private String msg;

	private String merchid;

	private String status;

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private List<MerchantFeeInfo> merchantFeeInfoList = null;

	public List<MerchantFeeInfo> getMerchantFeeInfoList() {
		return merchantFeeInfoList;
	}

	public void setMerchantFeeInfoList(List<MerchantFeeInfo> merchantFeeInfoList) {
		this.merchantFeeInfoList = merchantFeeInfoList;
	}

	// public String getCode() {
	// return this.codeMap.get(this.code) == null ? "0" :
	// this.codeMap.get(this.code);
	// }

	public boolean isSyncSuccess() {
		return "URM00000".equals(this.code);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMerchid() {
		return merchid;
	}

	public void setMerchid(String merchid) {
		this.merchid = merchid;
	}

	private static Map<String, Long> baseInfoMapStatus = null;

	private static Map<String, Long> settleInfoMapStatus = null;

	private static Map<String, Long> feeInfoMapStatus = null;
	static {
		baseInfoMapStatus = new HashMap<String, Long>();
		baseInfoMapStatus.put("0", 3L);
		baseInfoMapStatus.put("1", 2L);
		baseInfoMapStatus.put("2", 4L);
		baseInfoMapStatus.put("3", 5L);
		baseInfoMapStatus.put("4", 6L);
		baseInfoMapStatus.put("5", 7L);
		baseInfoMapStatus.put("6", 8L);

		settleInfoMapStatus = new HashMap<String, Long>();
		settleInfoMapStatus.put("1", 3L);
		settleInfoMapStatus.put("2", 5L);
		settleInfoMapStatus.put("3", 2L);
		settleInfoMapStatus.put("4", 4L);

		feeInfoMapStatus = new HashMap<String, Long>();
		feeInfoMapStatus.put("1", 3L);
		feeInfoMapStatus.put("2", 5L);
		feeInfoMapStatus.put("3", 2L);
		feeInfoMapStatus.put("4", 4L);
	}

	public Long getBaseInfoStatus() {
		return this.baseInfoMapStatus.get(this.status);
	}

	public Long getSettleInfoStatus() {
		return this.settleInfoMapStatus.get(this.status);
	}

	public Long getFeeInfoStatus() {
		return this.feeInfoMapStatus.get(this.status);
	}

	// public static Map<String, String> codeMap = null;
	//
	// static {
	// codeMap = new HashMap<String, String>();
	// // codeMap.put("UUS20003", "0");
	// codeMap.put("URM00000", "1");
	// }

}
