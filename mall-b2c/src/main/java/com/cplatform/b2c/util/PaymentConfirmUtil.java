package com.cplatform.b2c.util;

import java.util.HashMap;
import java.util.Map;

public class PaymentConfirmUtil {

	/** 省份编号 */
	// 江苏省
	public static final String JS_CODE = "320000";

	// 吉林省
	public static final String JL_CODE = "220000";

	// 陕西省
	public static final String SX_CODE = "610000";

	// 商户地址+用户手机号归属地，对应相应的支付页面
	public static Map<String, String> payment = new HashMap<String, String>();
	static {

		/** 江苏省 */
		// 江苏省_本省用户
		payment.put("320000_other", "payment-confirm-js-stranger");

		/** 吉林省 */
		// 吉林省_本省用户
		payment.put("220000_220000", "payment-confirm-jl-native");
		// 吉林省_外来用户
		payment.put("220000_other", "payment-confirm-jl-stranger");

		/** 陕西省 */
		// 陕西省_本省用户
		payment.put("610000_610000", "payment-confirm-sx-native");
		// 陕西省_外来用户
		payment.put("610000_other", "payment-confirm-sx-stranger");

		/** 没有省份编号 */
		payment.put("default_page", "payment-confirm-default");

	}
}
