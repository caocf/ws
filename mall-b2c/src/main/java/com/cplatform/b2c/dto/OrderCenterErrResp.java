package com.cplatform.b2c.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * User: chneke
 * Date: 13-11-13
 * Time: 下午6:05
 */
public class OrderCenterErrResp {

    private int code;               //状态码
    private Long goodsId;           //商品Id
    private String text;            //简易描述
    private String validStatus;     //T_ITEM_SALE.IS_VALID值
    private String startTime;       //T_ITEM_SALE中的sale_start_time
    private String stopTime;        // T_ITEM_SALE中的sale_stop_time
    private Long goodsShopId;       //商品的商户Id
    private Long orderShopId;       //订单的商户Id
    private String status;          //根据code 对应T_ITEM_SALE的STATUS或商户的STATUS字段
    private Long shopId;            //商户Id
    private String validFlag;       //对应商户的IS_VALID字段
    private Long userId;            //用户Id
    private int currentCount;       //已购买数量
    private int buyCount;           //企图购买数量
    private int limit;              //限制购买数量

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public Long getGoodsShopId() {
        return goodsShopId;
    }

    public void setGoodsShopId(Long goodsShopId) {
        this.goodsShopId = goodsShopId;
    }

    public Long getOrderShopId() {
        return orderShopId;
    }

    public void setOrderShopId(Long orderShopId) {
        this.orderShopId = orderShopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
