package com.cplatform.b2c.model;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-15 下午3:01
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class MemberInvoice {

    private Long id;

    private Long mid;

    private String invoiceType;

    private String invoiceTitleType;

    private String invoiceTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitleType() {
        return invoiceTitleType;
    }

    public void setInvoiceTitleType(String invoiceTitleType) {
        this.invoiceTitleType = invoiceTitleType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }
}
