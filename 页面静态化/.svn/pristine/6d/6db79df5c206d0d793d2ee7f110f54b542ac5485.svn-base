package com.cplatform.mall.back.cms.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.cplatform.mall.back.cms.model.WebTemplate;
import com.cplatform.mall.back.cms.service.FreeMakerService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.HttpUtil;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-22 下午4:18:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
public class StaticizeTask implements Runnable {

	private WebTemplate webTemplate;

	@Autowired
	private FreeMakerService freeMakerService;

	@Autowired
	private AppConfig appConfig;

	@SuppressWarnings({ "static-access" })
	@Override
	public void run() {

		switch (webTemplate.getType()) {

		// 基于模板的缓存
		case WebTemplate.TYPE_TEMPLATE: {

			// HTTP 获取数据
			String data = HttpUtil.getData(webTemplate.getDataURL());

			JSONObject obj = new JSONObject().fromObject(data);

			// 获取freemaker 实例 , 生成静态文件
			try {
				freeMakerService.process(webTemplate.getFilePath(),
						obj.getString("path"), obj.get("map"),webTemplate.getOutputChartset());

			} catch (Exception e) {

				e.printStackTrace();
			}
			break;
		}

		// 直接访问jsp页面缓存
		case WebTemplate.TYPE_JSP: {

			try {
				File file = new File(webTemplate.getFilePath());

				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				Writer out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(webTemplate.getFilePath()),
						appConfig.getTemplate_FTL_Encoding()));

				out.write(HttpUtil.getData(webTemplate.getDataURL()));
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		}

	}

	public WebTemplate getWebTemplate() {
		return webTemplate;
	}

	public void setWebTemplate(WebTemplate webTemplate) {
		this.webTemplate = webTemplate;
	}

}
