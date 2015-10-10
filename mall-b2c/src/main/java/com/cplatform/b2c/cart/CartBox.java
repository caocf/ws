package com.cplatform.b2c.cart;

import com.cplatform.b2c.util.SerializeUtil;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车对象
 * <p/>
 * Copyright: Copyright (c) 13-6-8 上午11:28
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class CartBox implements Serializable {

    private static final long serialVersionUID = -8330459379555211761L;

    private List<CartGroup> cartGroups = new ArrayList<CartGroup>();

    public List<CartGroup> getCartGroups() {
        return cartGroups;
    }

    public void setCartGroups(List<CartGroup> cartGroups) {
        this.cartGroups = cartGroups;
    }

    /**
     * 根据购物车中商品标识来查找商品数据
     * @param itemKey 商品标识
     * @return 购物车商品对象，没找到返回null
     */
    public CartItem find(String itemKey) {
        for (CartGroup cartGroup : cartGroups) {
            List<CartItem> cartItems = cartGroup.getCartItems();
            for (CartItem cartItem : cartItems) {
                if (StringUtils.equals(cartItem.getItemKey(), itemKey)) {
                    return cartItem;
                }
            }
        }
        return null;
    }

    public void addAll(CartBox cartBox) {
        if (cartBox == null) return;
        List<CartGroup> cartGroups = cartBox.getCartGroups();
        for (CartGroup cartGroup : cartGroups) {
            List<CartItem> cartItems = cartGroup.getCartItems();
            for (CartItem cartItem : cartItems) {
                put(cartItem);
            }
        }
    }


    public void del(CartItem cartItem) {
        if (cartItem == null) return;
        CartGroup cartGroup = new CartGroup(cartItem.getGroupKey());

        if (!cartGroups.contains(cartGroup)) {
            return;
        } else {
            cartGroup = cartGroups.get(cartGroups.indexOf(cartGroup));
        }

        List<CartItem> cartItems = cartGroup.getCartItems();

        if (cartItems.contains(cartItem)) {
            cartItems.remove(cartItem);
        }

        if (cartItems.size() == 0) {
            cartGroups.remove(cartGroup);
        }
    }

    /**
     * 修改某个商品数据，不存在不操作
     * @param cartItem 商品数据
     */
    public void replace(CartItem cartItem) {
        CartGroup cartGroup = new CartGroup(cartItem.getGroupKey());

        if (!cartGroups.contains(cartGroup)) {
            return;
        } else {
            cartGroup = cartGroups.get(cartGroups.indexOf(cartGroup));
        }

        List<CartItem> cartItems = cartGroup.getCartItems();

        if (cartItems.contains(cartItem)) {
            cartItems.set(cartItems.indexOf(cartItem), cartItem);
        }
    }

    /**
     * 添加购物商品，如果已存在则不添加
     * @param cartItem 商品数据
     */
    public void put(CartItem cartItem) {
        CartGroup cartGroup = new CartGroup(cartItem.getGroupKey());
        if (!cartGroups.contains(cartGroup)) {
            cartGroups.add(cartGroup);
        } else {
            cartGroup = cartGroups.get(cartGroups.indexOf(cartGroup));
        }

        List<CartItem> cartItems = cartGroup.getCartItems();

        if (!cartItems.contains(cartItem)) {
            cartItems.add(cartItem);
        } else {
            CartItem ci = cartItems.get(cartItems.indexOf(cartItem));
            ci.setQuantity(ci.getQuantity() + cartItem.getQuantity());
        }
    }

    public static CartBox fromByteArray(byte[] data) throws IOException, ClassNotFoundException {
        return (CartBox) SerializeUtil.unserialize(data);
    }

    public byte[] toByteArray() {
        return SerializeUtil.serialize(this);
    }

    @Override
    public String toString() {
        return JSONSerializer.toJSON(this).toString();
    }
}
