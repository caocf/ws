package com.cplatform.b2c.cart;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 购物车单个项目对象
 * <p/>
 * Copyright: Copyright (c) 13-6-8 上午11:25
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class CartItem implements Serializable {

    private static final long serialVersionUID = 250567072361884479L;

    private GroupKey groupKey;

    private String itemId;

    private int quantity;

    private long addTime = System.currentTimeMillis();

    transient private ItemSaleDataDTO itemInfo;

    private CartItem() {

    }

    public CartItem(GroupKey groupKey, String itemId, int quantity) {
        this.groupKey = groupKey;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public CartItem(GroupKey groupKey, String itemId, int quantity, ItemSaleDataDTO itemInfo) {
        this.groupKey = groupKey;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemInfo = itemInfo;
    }

//    public JSONObject getItemInfo() {
//        return itemInfo;
//    }
//
//    public void setItemInfo(JSONObject itemInfo) {
//        this.itemInfo = itemInfo;
//    }


    public ItemSaleDataDTO getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemSaleDataDTO itemInfo) {
        this.itemInfo = itemInfo;
    }

    public String getItemKey() {
        return this.itemId;
    }

    public GroupKey getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(GroupKey groupKey) {
        this.groupKey = groupKey;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public double getDeliveryFee() {
        return DeliveryUtil.getDeliveryFee(this.itemInfo);
    }

    public int getDeliveryType() {
        return DeliveryUtil.getDeliveryType(this.itemInfo);
    }

    /* 如果购物车项目中商品id 相同 则 认为是同一样购物车物品 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;

        CartItem cartItem = (CartItem) o;
        if (!getItemKey().equals(cartItem.getItemKey())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getItemKey().hashCode();
    }
}
