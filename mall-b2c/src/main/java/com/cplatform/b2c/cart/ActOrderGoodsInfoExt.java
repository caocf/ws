package com.cplatform.b2c.cart;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.order.ActOrderGoodsInfo;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 扩展的订单对象。包含商品信息
 * <p/>
 * Copyright: Copyright (c) 13-6-20 下午3:03
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class ActOrderGoodsInfoExt extends ActOrderGoodsInfo {

    private String itemKey;

    private ItemSaleDataDTO itemInfo;

    public ItemSaleDataDTO getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemSaleDataDTO itemInfo) {
        this.itemInfo = itemInfo;
    }

    /**
     * 商品关键索引项
     * @return
     */
    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * 获取物流费用
     * @return 金额 单位元
     */
    public double getDeliveryFee() {
        return DeliveryUtil.getDeliveryFee(this.itemInfo);
    }

    /**
     * 获取商品的物流费用计费类型
     * @return 类型 ，0不累加，1按数量累加
     */
    public int getDeliveryType() {
        return DeliveryUtil.getDeliveryType(this.itemInfo);
    }

    /**
     * 生成原有的订单对象
     * @return
     */
    public ActOrderGoodsInfo getOrg() {
        ActOrderGoodsInfo info = new ActOrderGoodsInfo();
        try {
            BeanUtils.copyProperties(info, this);
        } catch (IllegalAccessException e) {
            // ignore
        } catch (InvocationTargetException e) {
            // ignore
        }
        return info;
    }
}
