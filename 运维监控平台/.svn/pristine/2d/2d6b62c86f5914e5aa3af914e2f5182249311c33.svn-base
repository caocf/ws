package com.cplatform.mall.dc.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.service.DcUserService;

/**
 * 基础信息后台验证类
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午16:25:13
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
@Component
public class DcUserPostValidator implements Validator {

	@Autowired
	DcUserService dcUserService;

	@Override
	public boolean supports(Class<?> clazz) {
		return DcUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DcUser dcUser = (DcUser) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPass", null, "请填写新密码");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPass", null, "请填写新密码确认");
		if (!errors.hasFieldErrors("newPass") && !errors.hasFieldErrors("confirmPass")) {
			if (!StringUtils.equals(dcUser.getNewPass(), dcUser.getConfirmPass())) {
				errors.rejectValue("confirmPass", null, "新密码前后两次填写不一致");
			}
		}
	}
}
