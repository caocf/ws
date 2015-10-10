package com.cplatform;

import com.cplatform.jettyrun.ServerRunner;

/**
 * 数据中心主程序 <br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午4:07:44
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
public class DataCenterStart {

	/**
	 * main函数.
	 * 
	 * @param args
	 *            启动参数
	 * @throws Exception
	 *             Exception
	 */
	public static void main(String... args) throws Exception {
		ServerRunner sr = new ServerRunner();
		sr.setPort(9080);
		sr.setContextPath("/data-center");
		sr.start();
	}
}
