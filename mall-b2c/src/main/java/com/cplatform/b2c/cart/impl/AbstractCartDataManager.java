package com.cplatform.b2c.cart.impl;

import com.cplatform.b2c.cart.CartBox;
import com.cplatform.b2c.cart.CartDataManager;
import com.cplatform.b2c.cart.CartItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 购物车数据管理的基础静态类
 * <p/>
 * Copyright: Copyright (c) 13-6-20 上午8:43
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public abstract class AbstractCartDataManager implements CartDataManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 一天所需的秒数 */
    protected static long ONE_DAY_SEC = 60 * 60 * 24;

    /** 默认购物车数据失效时间 */
    protected static int DEFAULT_EXPIRE_DAY = 90;

    protected int expireDay = DEFAULT_EXPIRE_DAY;

    @Override
    public int getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(int expireDay) {
        this.expireDay = expireDay;
    }

    abstract CartBox getObject(String identity);

    abstract void putObject(String identity, CartBox cartBox, long expireSeconds);

    abstract void delObject(String identity);

    @Override
    public CartBox addItem(String identity, CartItem cartItem) {
        CartBox cartBox = getBox(identity);
        if (cartBox == null) {
            cartBox = new CartBox();
        }
        cartBox.put(cartItem);
        putObject(identity, cartBox, (long) expireDay * ONE_DAY_SEC);
        if (logger.isDebugEnabled()) {
            logger.debug("cart: item added. key:" + identity + ", value:" + cartBox.toString());
        }
        return cartBox;
    }

    @Override
    public CartBox getBox(String identity) {
        return getObject(identity);
    }

    @Override
    public CartBox setBox(String identity, CartBox cartBox) {
        putObject(identity, cartBox, (long) expireDay * ONE_DAY_SEC);
        if (logger.isDebugEnabled()) {
            logger.debug("cart: set box. key:" + identity + ", value:" + cartBox.toString());
        }
        return cartBox;
    }

    @Override
    public void delBox(String identity) {
        delObject(identity);
        if (logger.isDebugEnabled()) {
            logger.debug("cart: delete box. key:" + identity);
        }
    }

}
