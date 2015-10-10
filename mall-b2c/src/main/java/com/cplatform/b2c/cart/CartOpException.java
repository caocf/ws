package com.cplatform.b2c.cart;

/**
 * 购物车操作错误封装
 * <p/>
 * Copyright: Copyright (c) 13-6-13 上午11:54
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class CartOpException extends RuntimeException {

    public CartOpException() {
        super();
    }

    public CartOpException(String message) {
        super(message);
    }

    public CartOpException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartOpException(Throwable cause) {
        super(cause);
    }
}
