package com.cplatform.security;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityTestCase {

	protected static final char[] CHARS_ZH = "减出划则利力加务动包化协单占原双发受司合同向告响商回因团国土在场块城增外大头好季完定实家容小少就尼属工币市布常幅年度建归当影总情".toCharArray();

	protected static char[] RANDOM_CHARS;

	static {
		String s0 = RandomStringUtils.randomAlphabetic(256);
		String s1 = RandomStringUtils.randomNumeric(256);
		String s2 = RandomStringUtils.random(256, CHARS_ZH);
		RANDOM_CHARS = (s0 + s1 + s2).toCharArray();
	}

	private String charset = "UTF-8";

	/** 日志记录器 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public String generateRawText(int length) {
		String rawText = RandomStringUtils.random(length, RANDOM_CHARS);
		return rawText;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
