package com.cplatform.mall.dc.utils;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-8 下午3:52
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class UrlPathEncoder {

    private static URLEncoder urlEncoder;

    static {

        urlEncoder = new URLEncoder();

        // 'safe' rule
        urlEncoder.addSafeCharacter('$');
        urlEncoder.addSafeCharacter('-');
        urlEncoder.addSafeCharacter('_');
        urlEncoder.addSafeCharacter('.');
        urlEncoder.addSafeCharacter('+');

        // 'extra' rule
        urlEncoder.addSafeCharacter('!');
        urlEncoder.addSafeCharacter('*');
        urlEncoder.addSafeCharacter('\'');
        urlEncoder.addSafeCharacter('(');
        urlEncoder.addSafeCharacter(')');
        urlEncoder.addSafeCharacter(',');

        // special characters common to http: file: and ftp: URLs ('fsegment' and 'hsegment' rules)
        urlEncoder.addSafeCharacter('/');
        urlEncoder.addSafeCharacter(':');
        urlEncoder.addSafeCharacter('@');
        urlEncoder.addSafeCharacter('&');
        urlEncoder.addSafeCharacter('=');
        urlEncoder.addSafeCharacter('?');
    }


    /**
     * URL rewriter.
     *
     * @param path Path which has to be rewiten
     */
    public static String rewriteUrl(String path) {
        return urlEncoder.encode( path );
    }

}
