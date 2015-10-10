package com.cplatform.b2c.cart;

import java.io.Serializable;

import com.cplatform.b2c.dto.ItemSaleDataDTO;

/**
 * 购物车分组KEY，相同的KEY可以放入一组
 * <p/>
 * Copyright: Copyright (c) 14-1-22 上午11:14
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class GroupKey implements Serializable {

	private String shopId;

	private boolean allowCash;

	private boolean allowCoin;

	private boolean allowScore;

	private boolean allowBill;

	private boolean allowRed;

	public GroupKey() {

	}

	public GroupKey(ItemSaleDataDTO dto) {
		this.shopId = String.valueOf(dto.getItem().getStoreId());
		this.allowCash = dto.getItem().getCashIdgoods() == 0 ? false : true;
		this.allowCoin = dto.getItem().getCoinIdgoods() == 0 ? false : true;
		this.allowScore = dto.getItem().getScoreIdgoods() == 0 ? false : true;
		this.allowBill = dto.getItem().getBillPay() == 0 ? false : true;
		this.allowRed = dto.getItem().getRedPay() == 0 ? false : true;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public boolean isAllowCash() {
		return allowCash;
	}

	public void setAllowCash(boolean allowCash) {
		this.allowCash = allowCash;
	}

	public boolean isAllowCoin() {
		return allowCoin;
	}

	public void setAllowCoin(boolean allowCoin) {
		this.allowCoin = allowCoin;
	}

	public boolean isAllowScore() {
		return allowScore;
	}

	public void setAllowScore(boolean allowScore) {
		this.allowScore = allowScore;
	}

	public boolean isAllowBill() {
		return allowBill;
	}

	public void setAllowBill(boolean allowBill) {
		this.allowBill = allowBill;
	}

	public boolean isAllowRed() {
		return allowRed;
	}

	public void setAllowRed(boolean allowRed) {
		this.allowRed = allowRed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof GroupKey))
			return false;

		GroupKey groupKey = (GroupKey) o;

		if (allowBill != groupKey.allowBill)
			return false;
		if (allowCash != groupKey.allowCash)
			return false;
		if (allowCoin != groupKey.allowCoin)
			return false;
		if (allowRed != groupKey.allowRed)
			return false;
		if (allowScore != groupKey.allowScore)
			return false;
		if (!shopId.equals(groupKey.shopId))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = shopId.hashCode();
		result = 31 * result + (allowCash ? 1 : 0);
		result = 31 * result + (allowCoin ? 1 : 0);
		result = 31 * result + (allowScore ? 1 : 0);
		result = 31 * result + (allowBill ? 1 : 0);
		result = 31 * result + (allowRed ? 1 : 0);
		return result;
	}

	public static void main(String[] args) {
		GroupKey k1 = new GroupKey();
		GroupKey k2 = new GroupKey();
		k1.setShopId("1");
		k2.setShopId("1");
		k1.setAllowBill(true);
		k2.setAllowCash(true);

	}
}
