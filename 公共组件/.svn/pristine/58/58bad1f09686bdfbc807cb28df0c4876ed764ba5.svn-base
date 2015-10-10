package com.cplatform.security.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public abstract class AbstractEncryptProvider implements EncryptProvider {

	private static final String DEFAULT_CHARSET = "GB18030";

	private String charset = DEFAULT_CHARSET;

	@Override
	public byte[] decryptBase64(String base64Text) throws Exception {
		return decrypt(Base64.decodeBase64(base64Text));
	}

	@Override
	public String decryptBase64ToString(String base64Text, String charset) throws Exception {
		return new String(decrypt(Base64.decodeBase64(base64Text)), charset);
	}

	@Override
	public byte[] decryptHex(String hexText) throws Exception {
		return decrypt(Hex.decodeHex(hexText.toCharArray()));
	}

	@Override
	public String decryptHexToString(String hexText) throws Exception {
		return decryptHexToString(hexText, getCharset());
	}

	@Override
	public String decryptHexToString(String hexText, String charset) throws Exception {
		return new String(decrypt(Hex.decodeHex(hexText.toCharArray())), charset);
	}

	@Override
	public String decryptToString(byte[] data) throws Exception {
		return decryptToString(data, getCharset());
	}

	@Override
	public String decryptToString(byte[] data, String charset) throws Exception {
		return new String(decrypt(data), charset);
	}

	@Override
	public byte[] encrypt(String text) throws Exception {
		return encrypt(text, getCharset());
	}

	@Override
	public byte[] encrypt(String text, String charset) throws Exception {
		return encrypt(text.getBytes(charset));
	}

	@Override
	public String encryptBase64(byte[] data) throws Exception {
		return Base64.encodeBase64String(encrypt(data));
	}

	@Override
	public String encryptBase64(String text) throws Exception {
		return encryptBase64(text, getCharset());
	}

	@Override
	public String encryptBase64(String text, String charset) throws Exception {
		return encryptBase64(text.getBytes(charset));
	}

	@Override
	public String encryptHex(byte[] data) throws Exception {
		return Hex.encodeHexString(encrypt(data));
	}

	@Override
	public String encryptHex(String text) throws Exception {
		return encryptHex(text, getCharset());
	}

	@Override
	public String encryptHex(String text, String charset) throws Exception {
		return encryptHex(text.getBytes(charset));
	}

	@Override
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
