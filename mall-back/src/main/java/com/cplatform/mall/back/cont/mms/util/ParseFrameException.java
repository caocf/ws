package com.cplatform.mall.back.cont.mms.util;

/**
 * 解析smil文件异常类. <br>
 * 类详细说明.
 * <p>
 * 修改历史: Jan 15, 2010 10:55:29 AM baowr@c-platform.com <br>
 * 修改说明: 代码规范修改 <br>
 * <p>
 * Copyright: Copyright (c) Jan 15, 2009 10:55:13 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author chengyao@c-platform.com
 * @version 1.0.0
 */
public class ParseFrameException extends Exception {

	/**
	 * 构造函数
	 */
	public ParseFrameException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 *            异常信息
	 */
	public ParseFrameException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 *            异常信息
	 * @param cause
	 *            cause
	 */
	public ParseFrameException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause
	 *            cause
	 */
	public ParseFrameException(Throwable cause) {
		super(cause);
	}

}
