package com.cplatform.mall.back.order.web;
import java.sql.SQLException;

import javassist.compiler.ast.CondExpr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.order.entity.CodeInfo;
import com.cplatform.mall.back.order.service.CodeInfoService;
import com.cplatform.mall.back.sys.entity.SysFee;




/**
 * 验证码查询管理类 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-8 下午8:02:52
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: luyd@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

@Controller
@RequestMapping(value = "/code/composite")
public class CodeinfoController {
	@Autowired
	private CodeInfoService codeInfoService;
	
	@RequestMapping(value = "/list")
	public String findCompositeCode(
			CodeInfo codeInfo,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
			Model model) throws SQLException {
	//**
		Page<CodeInfo> pageData = codeInfoService.compositeCodeList(codeInfo, page);
		model.addAttribute("statusMap",CodeInfo.statusMap);
		model.addAttribute("pageData", pageData);
		return "/code/composite/code_list";
	}
}
