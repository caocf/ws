package com.cplatform.b2c.cart;

/**
 * 购物车数据管理接口
 * <p/>
 * Copyright: Copyright (c) 13-6-8 上午11:58
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public interface CartDataManager {

    public int getExpireDay();

    /**
     * 把商品数据插入到标识对应的购物车中
     * @param identity 购物车标识，可能是一个uuid或者用户的id
     * @param cartItem 商品数据
     * @return 购物车对象
     */
    public CartBox addItem(String identity, CartItem cartItem);

    public CartBox getBox(String identity);

    public CartBox setBox(String identity, CartBox cartBox);

    public void delBox(String identity);

}
