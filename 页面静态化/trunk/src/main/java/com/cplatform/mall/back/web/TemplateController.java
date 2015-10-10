package com.cplatform.mall.back.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.mall.back.cms.model.WebTemplate;
import com.cplatform.mall.back.cms.service.WebTemplateService;
import com.cplatform.mall.back.cms.task.StaticizeTask;
import com.cplatform.mall.back.utils.AppConfig;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午5:07:39
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Controller
public class TemplateController {

	@Autowired
	private WebTemplateService templateService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private ApplicationContext cx;

	@Autowired
	ThreadPoolTaskExecutor threadPool;

	@RequestMapping(value = "/template/add")
	public String add(HttpServletRequest request,
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("dataURL") String dataURL,@RequestParam("outputChartset") String outputChartset,
			@RequestParam("type") int type, Model model) {

		WebTemplate template = null;
		if (type == WebTemplate.TYPE_TEMPLATE) {

			File targetFile = new File(appConfig.getTemplate_Upload_Path(),
					file.getOriginalFilename());
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			// 保存上传的模板
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			template = WebTemplate.newInstance(name, dataURL,
					file.getOriginalFilename(), type,outputChartset);
		} else {

			template = WebTemplate.newInstance(name, dataURL,
					request.getParameter("filepath"), type,outputChartset);
		}

		templateService.save(template);

		return list(model);
	}

	@RequestMapping(value = "/template/list")
	public String list(Model model) {

		model.addAttribute("list", templateService.getAll());
		return "info";
	}

	@RequestMapping(value = "/template/test1")
	@ResponseBody
	public String test1(HttpServletResponse response) {

		StaticizeTask staticizeTask = cx.getBean(StaticizeTask.class);
	//	staticizeTask.setWebTemplate(templateService
	//			.get("a5c85f12-aef1-4b7b-bf0b-6bca6cd2f799"));
		threadPool.execute(staticizeTask);

		return "";
	}

	@RequestMapping(value = "/template/test")
	@ResponseBody
	public void test(HttpServletResponse response) {

		response.setContentType("text/json; charset=utf-8");
		JSONObject json = null;

		Bean bean = new Bean();

		bean.setPath("d:\\temp\\bb\\a1.html");

		HashMap<String, Object> a = new HashMap<String, Object>();

		a.put("name", "suyd");

		bean.setMap(a);

		json = JSONObject.fromObject(bean);

		PrintWriter out;
		try {
			out = response.getWriter();
			if (json != null)
				out.print(json);
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public class Bean {

		private String path;

		private HashMap<String, Object> map;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public HashMap<String, Object> getMap() {
			return map;
		}

		public void setMap(HashMap<String, Object> map) {
			this.map = map;
		}

	}

}
