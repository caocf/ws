package com.cplatform.mall.backuc.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-28 下午2:06
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Configuration
public class AppConfig {

    @Value("${jssso.valid.url}")
    private String jsValidUrl;

    @Value("${jssso.channel.code}")
    private String jsChannelCode;

    @Value("${jssso.channel.pwd}")
    private String jsChannelPwd;


    public String getJsValidUrl() {
        return jsValidUrl;
    }

    public void setJsValidUrl(String jsValidUrl) {
        this.jsValidUrl = jsValidUrl;
    }

    public String getJsChannelCode() {
        return jsChannelCode;
    }

    public void setJsChannelCode(String jsChannelCode) {
        this.jsChannelCode = jsChannelCode;
    }

    public String getJsChannelPwd() {
        return jsChannelPwd;
    }

    public void setJsChannelPwd(String jsChannelPwd) {
        this.jsChannelPwd = jsChannelPwd;
    }
}
