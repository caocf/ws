package com.cplatform.mall.backuc.cas;

import org.jasig.cas.authentication.principal.RememberMeUsernamePasswordCredentials;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-27 下午3:54
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class ExtendedCredentials extends RememberMeUsernamePasswordCredentials {

    private static final long serialVersionUID = 7874976325437431902L;

    /** 用于模拟登录 */
    private String vcode;

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExtendedCredentials)) return false;
        if (!super.equals(o)) return false;

        ExtendedCredentials that = (ExtendedCredentials) o;

        if (vcode != null ? !vcode.equals(that.vcode) : that.vcode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (vcode != null ? vcode.hashCode() : 0);
        return result;
    }
}
