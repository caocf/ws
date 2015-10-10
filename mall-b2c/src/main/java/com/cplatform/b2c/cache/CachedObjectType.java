package com.cplatform.b2c.cache;

/**
 * User: cuikai Date: 13-7-30 Time: 下午2:57
 */
public enum CachedObjectType {

	VERIFY_PAY("verify_pay_:"),

	VERIFY_COIN("verify_coin_:"),

	VERIFY_BIND("verify_bind_:"),

	VERIFY_USERCENTER_BIND("verify_usercenter_bind_:"),

	VERIFY_USERCENTER_EDIT_STEP1("verify_usercenter_edit_step1:"),

	VERIFY_USERCENTER_EDIT_STEP2("verify_usercenter_edit_step2:"),

	VERIFY_CREATE_ORDER("verify_create_order_:");

	private String prefix;

	CachedObjectType(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}
}
