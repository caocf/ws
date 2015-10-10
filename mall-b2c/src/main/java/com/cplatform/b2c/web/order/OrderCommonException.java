package com.cplatform.b2c.web.order;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-16 下午6:21
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class OrderCommonException extends RuntimeException {

    public OrderCommonException() {
        super();
    }

    public OrderCommonException(String message) {
        super(message);
    }

    public OrderCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderCommonException(Throwable cause) {
        super(cause);
    }
}