package com.cplatform.b2c.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: cuikai Date: 13-11-11 Time: 上午11:02
 */
public class QueryWelfareNewResp {

	private List<Bonu> bonus;

	private Long payOrderId;

	private Long payOrderRecordId;

	private int statusCode;

	private String statusText;

	public static class Bonu {

		private int amt;

		private String type;

		public int getAmt() {
			return amt;
		}

		public void setAmt(int amt) {
			this.amt = amt;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	public Long getPayOrderRecordId() {
		return payOrderRecordId;
	}

	public void setPayOrderRecordId(Long payOrderRecordId) {
		this.payOrderRecordId = payOrderRecordId;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public List<Bonu> getBonus() {
		return bonus;
	}

	public void setBonus(List<Bonu> bonus) {
		this.bonus = bonus;
	}
}
