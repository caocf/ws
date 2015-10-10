package com.cplatform.mall.back.cms.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.HttpUtil;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午9:46:01
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Service
public class ComponentService implements TemplateDirectiveModel {

	@Autowired
	private AppConfig appConfig;

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		String url = appConfig.getTemplate_Component_URL();

		url += "?1=1";
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			SimpleScalar val = (SimpleScalar) entry.getValue();

			url += "&" + key + "=" + val.toString();
		}

		env.getOut().write(HttpUtil.getData(url));
	}

}
