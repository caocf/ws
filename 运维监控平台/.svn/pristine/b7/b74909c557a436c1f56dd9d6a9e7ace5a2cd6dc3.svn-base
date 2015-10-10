package com.cplatform.mall.dc.web;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cplatform.mall.dc.model.BackUpInfo;
import com.cplatform.mall.dc.service.BackUpService;
import com.cplatform.mall.dc.service.BackUpService.Type;

/**
 * 备份统计 <br>
 * <p>
 * Copyright: Copyright (c) 2014-5-7 下午04:24:05
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author guyu@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/backup")
public class BackUpController {

	@Autowired
	BackUpService service;
	
	
	@RequestMapping(value = "/sc", method = RequestMethod.GET)
	public String toweb(Model model) throws SQLException {
		Type type = Type.SC;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		List<BackUpInfo> list = service.getBackUp(type, sf.format(new Date()));
		model.addAttribute("backupList",list);
		return "/backup/view";
	}
	
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String towapb(Model model) throws SQLException {
		Type type = Type.DB;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		List<BackUpInfo> list = service.getBackUp(type, sf.format(new Date()));
		model.addAttribute("backupList",list);
		return "/backup/view";
	}

}
