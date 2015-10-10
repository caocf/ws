package com.cplatform.mall.back.cms.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.mall.back.cms.model.WebTemplate;
import com.cplatform.mall.back.cms.service.FreeMakerService;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.HttpUtil;

/**
 * 同步消息处理
 * <p>
 * 同步消息处理多线程基于servlet实现。
 * <p>
 * Copyright: Copyright (c) 2013-5-22 下午4:18:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
@Service
public class SyncStaticizeTask {

	@Autowired
	private FreeMakerService freeMakerService;

	@Autowired
	private AppConfig appConfig;

	/** 日志记录器 */
	private Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("static-access")
	public String run(WebTemplate webTemplate, String event, int int_IsPreview)
			throws Exception {

		logger.info("静态化模板：" + webTemplate.getFilePath() + " event:" + event);
		String result = "";
		switch (webTemplate.getType()) {

		// 基于模板的缓存
		case WebTemplate.TYPE_TEMPLATE: {

			// HTTP 获取数据
			// String data = HttpUtil.getData(webTemplate.getDataURL());
			// JSONObject obj = new JSONObject().fromObject(data);

			// 获取freemaker 实例 , 生成静态文件
			try {

				JSONObject obj = new JSONObject().fromObject(event);
				if (int_IsPreview == 0) {
					freeMakerService.process(webTemplate.getFilePath(),
							obj.getString("path"), obj.get("map"),
							webTemplate.getOutputChartset());
					result = "{RET:0,MSG:'生成成功！'}";
				} else {

					result = freeMakerService.process(
							webTemplate.getFilePath(), obj.get("map"),
							webTemplate.getOutputChartset());

				}

			} catch (Exception e) {
				logger.error("模板静态化错误：" + webTemplate.getFilePath() + " msg:"
						+ event);
				e.printStackTrace();
				throw e;
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

				result = "{RET:0,MSG:'生成成功！'}";
			} catch (Exception e) {

				logger.error("JSP静态化错误：" + webTemplate.getDataURL());
				e.printStackTrace();
				throw e;
			}

		}

		}

		return result;

	}

}
