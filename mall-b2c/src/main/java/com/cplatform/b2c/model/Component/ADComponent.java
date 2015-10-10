package com.cplatform.b2c.model.Component;

import java.util.HashMap;

import org.springframework.stereotype.Service;

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

@Service
public class ADComponent extends ComponentBase {

	@SuppressWarnings("rawtypes")
	@Override
	public String execute(HashMap params) throws Exception {

		return "<script language=\"javascript\" type=\"text/javascript\" src=\"http://localhost:8081/mall-b2c/ad/t?adid="
				+ params.get("id")
				+ "&eid="
				+ params.get("eid")
				+ "\" ></script>";

	}

}
