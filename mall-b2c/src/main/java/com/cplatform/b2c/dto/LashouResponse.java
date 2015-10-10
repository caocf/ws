package com.cplatform.b2c.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: cuikai
 * Date: 13-11-19
 * Time: 上午9:35
 */
public class LashouResponse {
    private String ret;
    private String msg;
    private int sellCount;
    private String venderTeamId;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public String getVenderTeamId() {
        return venderTeamId;
    }

    public void setVenderTeamId(String venderTeamId) {
        this.venderTeamId = venderTeamId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
