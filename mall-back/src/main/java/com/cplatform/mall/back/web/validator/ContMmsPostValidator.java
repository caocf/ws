package com.cplatform.mall.back.web.validator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.cont.entity.ContMms;
import com.cplatform.mall.back.cont.mms.bean.Frame;
import com.cplatform.mall.back.cont.service.ContMmsService;
import com.cplatform.mall.back.sys.service.SysInfoService;

/**
 * 彩信发送后台验证类
 * <p>
 * Copyright: Copyright (c) 2012-11-15 下午4:53:30
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jisn@c-platform.com
 * @version 1.0.0
 */
@Component
public class ContMmsPostValidator implements Validator {

	@Autowired
	SysInfoService filterWordService;

	@Autowired
	ContMmsService contMmsService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ContMms.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

	public void validate(Object target, Errors errors, Collection<Frame> frames) {
		ContMms contMms = (ContMms) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", null, "请填写彩信标题");
		if (!errors.hasFieldErrors("title")) {
			if (contMms.getTitle().length() > 140) {
				errors.rejectValue("title", null, "填写的彩信标题过长，不超过140个字符");
			} else {
				if (filterWordService.checkFilterWord(contMms.getTitle())) {
					errors.rejectValue("title", null, "填写的彩信标题含有非法字符");
				}
			}

		}
		int flag = contMmsService.checkMmsContent(frames);
		if (flag == 0) {
			errors.rejectValue("content", null, "填写的彩信内容含有非法字符");
		} else if (flag == 1) {
			errors.rejectValue("content", null, "填写的彩信内容过长，不超过1500个字符");
		}

		if (contMms.getStatus() != null && contMms.getStatus() == 1) {
			errors.rejectValue("content", null, "彩信已被审核通过，不能再编辑");
		}
	}

	public void validateStatus(Object target, Errors errors) {
		ContMms contMms = (ContMms) target;
		if (contMms.getStatus() != null && contMms.getStatus() == 1) {
			errors.rejectValue("content", null, "彩信已被审核通过，不能再编辑");
		}
	}
}
