package com.cplatform.b2c.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: cuikai
 * Date: 13-11-5
 * Time: 下午4:38
 */
public class PayOrderResponse {

    @JsonProperty("FLAG")
    private String flag;
    @JsonProperty("MSG")
    private String msg;
    @JsonProperty("ORDER_ID")
    private Long orderId;
    @JsonProperty("REDIRECT_URL")
    private String redirectUrl;
    @JsonProperty("FORM_ACTION_URL")
    private String formActionUrl;
    @JsonProperty("HTML")
    private String html;

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

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getFormActionUrl() {
        return formActionUrl;
    }

    public void setFormActionUrl(String formActionUrl) {
        this.formActionUrl = formActionUrl;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
