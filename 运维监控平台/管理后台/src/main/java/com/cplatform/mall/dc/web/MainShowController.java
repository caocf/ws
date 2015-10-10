package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.dc.utils.AppConfig;

/**
 * 默认页面 <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-13 上午10:00:46
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/main")
public class MainShowController {
	
	@Autowired
	AppConfig config;
	
	@RequestMapping(value = "/getUrl", method = RequestMethod.POST)
	@ResponseBody
	public String getUrl(Model model) throws SQLException {
		String[] mainShow = config.getWebMainShow().split(",");
		List<String> urls = new ArrayList<String>();
		if(mainShow!=null&&mainShow.length>0){
			for(int i = 0;i<(mainShow.length>4?4:mainShow.length);i++){
				urls.add(mainShow[i]);
			}
		}
		return JSONArray.fromObject(urls).toString();
	}

}
