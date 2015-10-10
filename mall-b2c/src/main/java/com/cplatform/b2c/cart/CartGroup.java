package com.cplatform.b2c.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车商品组，不同组的商品不能合并支付
 * <p/>
 * Copyright: Copyright (c) 14-1-22 上午11:09
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class CartGroup implements Serializable {

    private static final long serialVersionUID = 5107894931327249144L;

    private GroupKey groupKey;

    transient private String shopName;

    private List<CartItem> cartItems = new ArrayList<CartItem>();

    public CartGroup() {
    }

    public CartGroup(GroupKey groupKey) {
        this.groupKey = groupKey;
    }

    public GroupKey getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(GroupKey groupKey) {
        this.groupKey = groupKey;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /* shopId 相同 则认为是相同的对象 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartGroup)) return false;

        CartGroup that = (CartGroup) o;

        if (!groupKey.equals(that.groupKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return groupKey.hashCode();
    }
}
