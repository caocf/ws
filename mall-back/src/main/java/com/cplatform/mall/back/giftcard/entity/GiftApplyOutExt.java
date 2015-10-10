package com.cplatform.mall.back.giftcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;


/**
 * Title. <br>
 * 礼品卡出库申请表
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: mudeng@ca-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_GIFT_APPLY_OUT_EXT")
public class GiftApplyOutExt extends IdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long applyId;	//			出库申请编号
	private Long batchNo;	//		批次号
	private int num;	//			卡数量
	private String modelNo;	//			卡型号
	private int payPrice;	//			支付单价，分
	private String remark;
	
	private Long stocks;
	
	
	@Transient
	public Long getStocks() {
		return stocks;
	}

	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	public GiftApplyOutExt(){
		
	}
	
	public GiftApplyOutExt(Long applyId,Long batchNo,int num,String modelNo,int payPrice,String remark){
		this.setApplyId(applyId);
		this.setBatchNo(batchNo);
		this.setNum(num);
		this.setModelNo(modelNo);
		this.setPayPrice(payPrice);
		this.setRemark(remark);
	}
	
	@Column(name="APPLY_ID")
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	
	@Column(name="BATCH_NO")
	public Long getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}
	
	@Column(name="NUM")
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Column(name="MODEL_NO")
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	
	@Column(name="PAY_PRICE")
	public int getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}
	
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	//			备注
}
