package com.cplatform.b2c.model.Component;

import java.util.HashMap;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 上午10:50:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
public abstract class ComponentBase {

	public abstract String execute(HashMap<String, String> params)
			throws Exception;
}
