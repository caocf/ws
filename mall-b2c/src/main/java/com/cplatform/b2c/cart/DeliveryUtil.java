package com.cplatform.b2c.cart;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

/**
 * 物流相关工具类
 * <p/>
 * Copyright: Copyright (c) 13-6-20 下午3:22
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class DeliveryUtil {

    /**
     * 获取商品的物流费用
     *
     * @param itemInfo 商品信息
     * @return 金额 单位元
     */
    public static double getDeliveryFee(ItemSaleDataDTO itemInfo) {
        try {
//            String feeStr = itemInfo.getJSONObject("item").getString("logisticsFee");
            String feeStr = String.valueOf(itemInfo.getItem().getLogisticsFee());
            return BigDecimal.valueOf(Double.valueOf(feeStr))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 获取商品的物流费用计费类型，0不累加，1按数量累加
     *
     * @param itemInfo 商品信息
     * @return 类型
     */
    public static int getDeliveryType(ItemSaleDataDTO itemInfo) {
        try {
//            return itemInfo.getJSONObject("item").getInt("logisticsFeeType");
            return itemInfo.getItem().getLogisticsFeeType();
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 根据goods列表获取对应所有商品的累计物流费用
     *
     * @param goods 商品列表
     * @return 金额 单位分
     */
    public static int getDeliveryTotalFee(List<ActOrderGoodsInfoExt> goods) {

        int fee = 0;
        java.util.Set<Long> goodIds = new HashSet<Long>();

        for (ActOrderGoodsInfoExt good : goods) {
            if (goodIds.contains(good.getGoodsId())) {
                if (good.getDeliveryType() == 0) {
                    continue;
                }
            }
            int sfee = BigDecimal.valueOf(good.getDeliveryFee()).multiply(new BigDecimal(100)).intValue();
            if (good.getDeliveryType() == 1) {
                sfee = sfee * good.getCount();
            }
            fee += sfee;
            goodIds.add(good.getGoodsId());
        }
        return fee;
    }

}
