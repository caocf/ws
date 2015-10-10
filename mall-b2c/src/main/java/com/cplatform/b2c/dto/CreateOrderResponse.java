package com.cplatform.b2c.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: cuikai
 * Date: 13-11-5
 * Time: 下午4:37
 */
public class CreateOrderResponse {

    @JsonProperty("FLAG")
    private String flag;
    @JsonProperty("MSG")
    private String msg;
    @JsonProperty("ORDER_ID")
    private Long orderId;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
