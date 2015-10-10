package com.cplatform.b2c.service;

/**
 *
 * User: chenke
 * Date: 13-11-13
 * Time: 下午5:45
 */
public class OrderCenterStatus {
    static enum STATUS {
        GOODS_NOT_EXIST(1002, "商品信息不存在"),

        GOODS_LACK_STOCK(1010, "商品库存不足"),

        GOODS_OFF_SHELF(1006, "商品已下架"),

        GOODS_SALE_NOT_START(1004, "商品尚未开始销售"),

        GOODS_SALE_END(1005, "商品已结束销售"),

        GOODS_SHOPID_NOT_EQUAL(1003, "商品的商户Id与订单的商户Id不符"),

        GOODS_ABNORMAL_STATE(1007, "商品状态异常"),

        GOODS_SHOP_NOT_EXIST(1008, "商户信息不存在"),

        GOODS_ABNORMAL_VALIEDFLAG(1011, "商户ValiedFlag异常"),

        GOODS_ABNORMAL_SHOP(1012, "商户状态异常"),

        GOODS_BUY_LIMIT(1009, "商品购买数量超过上限");

        STATUS(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static String getMsg(int code) {
        STATUS[] orderCenterStatus = STATUS.values();
        for (STATUS orderCenterSta : orderCenterStatus) {
            if (orderCenterSta.getCode() == code) {
                return orderCenterSta.getMsg();
            }
        }
        return "未知错误码（" + code + "）";
    }
}
