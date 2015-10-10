package com.cplatform.mall.dc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.dc.dao.DcUserDao;
import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.service.DcUserService;
import com.cplatform.mall.dc.utils.JsonRespWrapper;
import com.cplatform.mall.dc.web.validator.DcUserPostValidator;

/**
 * 业务概览 <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午5:14:19
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/base")
public class BaseController {

	static final Log logger = LogFactory.getLog(BaseController.class);

	@Autowired
	DcUserDao dcUserDao;

	@Autowired
	DcUserService dcUserService;

	@Autowired
	DcUserPostValidator dcUserPostValidator;

	@RequestMapping(value = "/editpwd", method = RequestMethod.GET)
	public String toEditPwd(Model model, HttpSession session) {
		SessionUser sessionUser = SessionUser.getSessionUser();

		DcUser shopUser = dcUserDao.findOne(sessionUser.getId());

		model.addAttribute("dcUser", shopUser);
		return "base/edit_pwd";
	}

	@RequestMapping(value = "/editpwd", method = RequestMethod.POST)
	@ResponseBody
	public Object editPwdPost(@ModelAttribute("dcUser") DcUser dcUser, HttpServletRequest request, HttpSession session, BindingResult result) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		DcUser oldDcUser = this.dcUserDao.findOne(sessionUser.getId());
		// 修改密码
		if (StringUtils.isNotBlank(dcUser.getOldPass())) {
			String pass = dcUserService.md5pass(dcUser.getOldPass(), null);
			// 输入旧密码和数据库不符
			if (!StringUtils.equals(pass, oldDcUser.getPwd())) {
				return JsonRespWrapper.errorField("oldPass", "原密码输入有误");
			} else {
				dcUserPostValidator.validate(dcUser, result);
				if (result.hasErrors()) {
					return JsonRespWrapper.error(result.getFieldErrors());
				} else {
					String getNewPassMD5 = dcUserService.md5pass(dcUser.getNewPass(), null);
					oldDcUser.setPwd(getNewPassMD5);
				}
			}
		} else {
			return JsonRespWrapper.errorField("oldPass", "原密码不能为空");
		}
		dcUserDao.save(oldDcUser);
		return JsonRespWrapper.success("个人设置操作成功！", "/home");
	}
}
