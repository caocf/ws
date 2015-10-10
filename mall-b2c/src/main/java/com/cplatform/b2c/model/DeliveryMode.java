package com.cplatform.b2c.model;

import java.math.BigDecimal;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-16 上午10:32
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class DeliveryMode {

    private String id;

    private String logisticsId;

    private String logisticsName;

    private BigDecimal fee;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getFeeString() {
        return fee != null ? fee.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() : "";
    }

    @Override
    public String toString() {
        return logisticsName + " " + (fee.equals(BigDecimal.ZERO) ? "免费" : (getFeeString() + "元"));
    }
}
