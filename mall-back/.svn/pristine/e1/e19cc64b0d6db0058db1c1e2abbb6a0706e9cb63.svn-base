package com.cplatform.mall.back.smsbuy.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.smsbuy.dao.SmsbuyHelpDao;
import com.cplatform.mall.back.smsbuy.entity.SmsbuyHelp;

/**
 * 短信购帮助信息校验类. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-17 下午2:23:03
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

@Component
public class SmsbuyHelpValidator implements Validator {

	@Autowired
	private SmsbuyHelpDao smsbuyHelpDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return SmsbuyHelp.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SmsbuyHelp smsbuyHelp = (SmsbuyHelp) target;
		validateCode(smsbuyHelp, errors);
	}

	/**
	 * @param smsbuyHelp
	 * @param errors
	 */
	private void validateCode(SmsbuyHelp smsbuyHelp, Errors errors) {
		String helpSpcode = smsbuyHelp.getRootSpcode() + smsbuyHelp.getHelpSpcode();
		String helpArea = smsbuyHelp.getHelpArea();
		if (smsbuyHelpDao.findByHelpSpcodeAndHelpArea(helpSpcode, helpArea) != null) {
			errors.rejectValue("helpSpcode", null, "帮助信息重复（特服号和区域已存在）！");
		}

	}
}
