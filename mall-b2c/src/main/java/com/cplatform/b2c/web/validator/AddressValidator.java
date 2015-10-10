package com.cplatform.b2c.web.validator;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.b2c.util.ValidateUtil;

/**
 * 验证收货地址信息. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-4-17 下午5:58:48
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Component
public class AddressValidator implements Validator {

	@Autowired
	private ValidateUtil validateUtil;

	// 配送地址为空
	private static final String NULL_DAREA_ERROR = "请选择配送地址";

	// 街道地址为空
	private static final String NULL_ADDRESS_ERROR = "请填写街道地址";

	// 收货人姓名为空
	private static final String NULL_NAME_ERROR = "请填写收货人姓名";

	// 收货人手机号码为空
	private static final String NULL_MOBILE_ERROR = "请填写收货人手机号码";

	// 收货人手机号码格式不正确
	private static final String VALIDATE_MOBILE_ERROR = "手机号码不正确，请重新填写";

	@Override
	public boolean supports(Class<?> clazz) {
		return TMemberAddress.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "region", null, NULL_DAREA_ERROR);
		if (errors.getAllErrors().size() <= 0) {
			ValidationUtils.rejectIfEmpty(errors, "address", null, NULL_ADDRESS_ERROR);
		}
		if (errors.getAllErrors().size() <= 0) {
			ValidationUtils.rejectIfEmpty(errors, "name", null, NULL_NAME_ERROR);
		}
		if (errors.getAllErrors().size() <= 0) {
			ValidationUtils.rejectIfEmpty(errors, "mobile", null, NULL_MOBILE_ERROR);
		}
		if (errors.getAllErrors().size() <= 0) {
			ValidateTerminal(errors);
		}
	}

	/**
	 * 验证手机号格式是否正确
	 * 
	 * @param errors
	 */
	private void ValidateTerminal(Errors errors) {
		Object value = errors.getFieldValue("mobile");
		if (!validateUtil.isMobile(ObjectUtils.toString(value))) {
			errors.rejectValue("mobile", null, VALIDATE_MOBILE_ERROR);
		}
	}
}
