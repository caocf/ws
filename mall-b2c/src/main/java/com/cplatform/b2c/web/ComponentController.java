package com.cplatform.b2c.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.model.Component.ADComponent;

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

@Controller
public class ComponentController {

	@Autowired
	private ADComponent adComponent;

	@RequestMapping(value = "/ftl/component")
	@ResponseBody
	public void genItem(@RequestParam("name") String name,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		if (name.equals("AD")) {

			try {

				PrintWriter out;
				try {
					out = response.getWriter();
					out.print(adComponent.execute(convertMap(request
							.getParameterMap())));

					out.flush();
				} catch (IOException e) {

					e.printStackTrace();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@SuppressWarnings("rawtypes")
	private HashMap<String, String> convertMap(Map map) {

		HashMap<String, String> kv = new HashMap<String, String>();

		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			String val = ((String[])entry.getValue())[0];

			kv.put(key, val);
		}

		return kv;

	}

}
