package com.cplatform.b2c.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;

import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;

/**
 * 
 * 设置文件路径, 模版配置, 统一变量输出. 使用方法, setPath==>setResponseData
 * <p>
 * Copyright: Copyright (c) 2013-6-16 上午11:17:03
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
public class BaseController {

	@Autowired
	private AppConfig appConfig;

	private static Configuration cfg = new Configuration();

	private Template template;

	private String path;

	private File srcfile;

	private File destfile;

	private Writer out;

	@Autowired
	private PathUtil pathUtil;

	public BaseController() {
		path = ContextLoader.getCurrentWebApplicationContext()
				.getServletContext().getRealPath("/");

		cfg.setDefaultEncoding("UTF8");
		cfg.setClassicCompatible(true);
		cfg.setLocale(java.util.Locale.CHINESE);
		cfg.setNumberFormat("0.##########");
	}

	public void setPath(String templateName, String htmPath) {
		try {

			String s_path = appConfig.getB2c_Index_RFPath();

			try {
				cfg.setDirectoryForTemplateLoading(new File(s_path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			template = cfg.getTemplate(templateName + ".ftl");
			template.setOutputEncoding("UTF8");

			// htmPath = path + htmPath;

			String htmPath_tmp = htmPath + "." + new Date().getTime();

			srcfile = new File(htmPath_tmp);

			if (!srcfile.getParentFile().exists()) {
				srcfile.getParentFile().mkdirs();
			}

			destfile = new File(htmPath);

			if (!destfile.getParentFile().exists()) {
				destfile.getParentFile().mkdirs();
			}

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmPath_tmp), "utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setResponseData(HashMap<String, Object> map) throws Exception {
		if (map == null) {
			return;
		}
/*
		map.put("WEB_ROOT", appConfig.getWebRoot());
		map.put("imgSvrHost", appConfig.getImgSvrHost());
		map.put("storeLogoPath", appConfig.getB2c_Store_Logo_Path());
		map.put("storeShelfPath", appConfig.getB2c_Store_Shelf_Path());
		map.put("channelPicPath", appConfig.getB2c_Channel_Pic_Path());
		map.put("pathUtil", pathUtil);*/
		
		this.initGlobalParams(map);

		try {
			template.process(map, out);
			out.flush();
			out.close();

			FileUtils.copyFile(srcfile, destfile);
			FileUtils.deleteQuietly(srcfile);

		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public StringWriter getResponseData(HashMap<String, Object> map) throws Exception {		
		this.initGlobalParams(map);

		try {
			StringWriter strWriter = new StringWriter();
			template.process(map, strWriter);
			out.flush();
			out.close();
			out.toString();
			return  strWriter;

		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void initGlobalParams(HashMap<String, Object> map){
		if (map == null) {
			return;
		}
		map.put("WEB_ROOT", appConfig.getWebRoot());
		map.put("imgSvrHost", appConfig.getImgSvrHost());
		map.put("storeLogoPath", appConfig.getB2c_Store_Logo_Path());
		map.put("storeShelfPath", appConfig.getB2c_Store_Shelf_Path());
		map.put("channelPicPath", appConfig.getB2c_Channel_Pic_Path());
		map.put("channelAdPath", appConfig.getB2c_ad_path());
		map.put("pathUtil", pathUtil);
	}

	public static TemplateHashModel useStaticPackage(String packageName) {
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			TemplateHashModel fileStatics = (TemplateHashModel) staticModels
					.get(packageName);
			return fileStatics;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
