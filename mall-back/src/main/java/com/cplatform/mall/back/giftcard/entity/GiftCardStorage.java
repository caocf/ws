package com.cplatform.mall.back.giftcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 礼品卡库存表映射 . <br>
 * 用于存放数据库映射实体.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:01:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author mudeng-ca@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_GIFT_CARD_STORAGE ")
public class GiftCardStorage extends IdEntity implements java.io.Serializable {

	private Long id;

	private Long batchNo;

	private Long stocks;

	private String remark;
	
	private Long stocksIn;
	
	private Long stocksOut;

	@Column(name = "BATCH_NO")
	public Long getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "STOCKS")
	public Long getStocks() {
		return stocks;
	}

	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STOCKS_IN")
	public Long getStocksIn() {
		return stocksIn;
	}

	public void setStocksIn(Long stocksIn) {
		this.stocksIn = stocksIn;
	}

	@Column(name = "STOCKS_OUT")
	public Long getStocksOut() {
		return stocksOut;
	}

	public void setStocksOut(Long stocksOut) {
		this.stocksOut = stocksOut;
	}
	
	
}
