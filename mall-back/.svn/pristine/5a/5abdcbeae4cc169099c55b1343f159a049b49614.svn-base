package com.cplatform.mall.back.cont.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.cont.dao.ContentCodeDao;
import com.cplatform.mall.back.cont.entity.ContentCode;

/**
 * 内容源校验类. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-30 下午6:01:44
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Component
public class ContCodeValidator implements Validator {

	@Autowired
	private ContentCodeDao contentCodeDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return ContentCode.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ContentCode contCode = (ContentCode) target;
		if (contCode.getId() == null) {// 如果是创建
			validateCode(contCode, errors);
		}
	}

	/**
	 * 内容源标识唯一校验
	 * 
	 * @param target
	 * @param errors
	 */
	private void validateCode(ContentCode contCode, Errors errors) {

		if (contentCodeDao.findByCode(contCode.getCode()).size() > 0) {
			errors.rejectValue("code", null, "内容源标识已存在！");
		}
	}
}
