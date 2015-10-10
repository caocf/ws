package com.cplatform.mall.back.cms.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.mall.back.utils.AppConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午4:42:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Service
public class FreeMakerService {

	public static Configuration cfg;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private ComponentService componentService;

	/** 日志记录器 */
	private Logger logger = Logger.getLogger(getClass());

	static {

		// 初始化freemaker配置，创建一个Configuration对象
		cfg = new Configuration();
		cfg.setTemplateUpdateDelay(5);
		cfg.setClassicCompatible(true);
		cfg.setLocale(java.util.Locale.CHINESE);
		cfg.setNumberFormat("0.#######");
	}

	public String process(String tempName, Object map, String chartSet)
			throws Exception {

		String out = "";
		try {
			cfg.setDirectoryForTemplateLoading(new File(appConfig
					.getTemplate_Upload_Path()));
			cfg.setDefaultEncoding(appConfig.getTemplate_Config_Encoding());

			cfg.setSharedVariable("Component", componentService);

			Template template = cfg.getTemplate(tempName);

			template.setEncoding(appConfig.getTemplate_FTL_Encoding());

			StringWriter strWriter = new StringWriter();

			// 设置输出编码
			template.setOutputEncoding(chartSet);

			template.process(map, strWriter);

			out = strWriter.toString();

		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;
		}
		return out;
	}

	public void process(String tempName, String outputFile, Object map,
			String chartSet) throws Exception {
		// Configuration cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File(appConfig
					.getTemplate_Upload_Path()));

			cfg.setDefaultEncoding(appConfig.getTemplate_Config_Encoding());
			cfg.setSharedVariable("Component", componentService);

			Template template = cfg.getTemplate(tempName);

			template.setEncoding(appConfig.getTemplate_FTL_Encoding());

			String outputFile_temp = outputFile + "." + new Date().getTime();

			File srcfile = new File(outputFile_temp);

			if (!srcfile.getParentFile().exists()) {
				srcfile.getParentFile().mkdirs();
			}

			File destfile = new File(outputFile);

			if (!destfile.getParentFile().exists()) {
				destfile.getParentFile().mkdirs();
			}

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outputFile_temp),
					appConfig.getTemplate_FTL_Encoding()));

			// 设置输出编码
			template.setOutputEncoding(chartSet);

			template.process(map, out);

			out.flush();
			out.close();

			FileUtils.copyFile(srcfile, destfile);
			FileUtils.deleteQuietly(srcfile);

		} catch (Exception ex) {
			logger.error("静态化错误：" + tempName + " map:" + map.toString());
			ex.printStackTrace();
			throw ex;
		}

	}

	public static Configuration getCfg() {
		return cfg;
	}

	public static void setCfg(Configuration cfg) {
		FreeMakerService.cfg = cfg;
	}

}
