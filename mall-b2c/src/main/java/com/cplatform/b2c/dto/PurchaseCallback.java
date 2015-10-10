package com.cplatform.b2c.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 营销平台支付成功回调 <br>
 * 参数封装
 * <p/>
 * Copyright: Copyright (c) ${date} ${time}
 * <p/>
 * Company: 苏州宽连十方数字技术有限公司
 * <p/>
 *
 * @author cuikai-ca@c-platform.com
 * @version 1.0.0
 */
public class PurchaseCallback {

    private Request request;
    private Response response;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public static class Request {
        private String actOrderId;
        private String goodId;
        private String goodName;
        private String buyNum;
        private String price;
        private String shopId;
        private String orderTime;
        private String payTime;
        private String payChannel;
        private String buyId;
        private String address;
        private String src;

        public String getActOrderId() {
            return actOrderId;
        }

        public void setActOrderId(String actOrderId) {
            this.actOrderId = actOrderId;
        }

        public String getGoodId() {
            return goodId;
        }

        public void setGoodId(String goodId) {
            this.goodId = goodId;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(String buyNum) {
            this.buyNum = buyNum;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }

        public String getBuyId() {
            return buyId;
        }

        public void setBuyId(String buyId) {
            this.buyId = buyId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }

    public static class Response {
        private String flag;
        private String msg;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
