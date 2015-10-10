package com.cplatform.b2c.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 验证常用的信息. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-4-18 上午9:55:31
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Component
public class ValidateUtil {

	/**
	 * 验证是否是手机号
	 * 
	 * @param mobiles
	 * @return
	 */
	public boolean isMobile(String mobiles) {
		if (StringUtils.isBlank(mobiles)) {
			return false;
		} else {
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			return m.matches();
		}
	}

	/**
	 * 是否中移动手机号码
	 * 
	 * @param value
	 * @return
	 */
	public boolean isChinaMobile(String value) {
		if (StringUtils.isBlank(value)) {
			return false;
		} else {
			Pattern p = Pattern.compile("^(13[4-9]|15[012789]|147|18[23478])\\d{8}$");
			Matcher m = p.matcher(value);
			return m.matches();
		}
	}

}
