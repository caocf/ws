package com.cplatform.security.signature;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public abstract class AbstractSignatureProvider implements SignatureProvider {

	private static final String DEFAULT_CHARSET = "GB18030";

	private String charset = DEFAULT_CHARSET;

	@Override
	public byte[] createSignature(String text) throws Exception {
		return createSignature(text, getCharset());
	}

	@Override
	public byte[] createSignature(String text, String charset) throws Exception {
		return createSignature(text.getBytes(charset));
	}

	@Override
	public String createSignatureBase64(byte[] data) throws Exception {
		return Base64.encodeBase64String(createSignature(data));
	}

	@Override
	public String createSignatureBase64(String text) throws Exception {
		return Base64.encodeBase64String(createSignature(text, getCharset()));
	}

	@Override
	public String createSignatureBase64(String text, String charset) throws Exception {
		return Base64.encodeBase64String(createSignature(text, charset));
	}

	@Override
	public String createSignatureHex(byte[] data) throws Exception {
		return Hex.encodeHexString(createSignature(data));
	}

	@Override
	public String createSignatureHex(String text) throws Exception {
		return Hex.encodeHexString(createSignature(text, getCharset()));
	}

	@Override
	public String createSignatureHex(String text, String charset) throws Exception {
		return Hex.encodeHexString(createSignature(text, charset));
	}

	@Override
	public String getCharset() {
		return charset;
	}

	@Override
	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public boolean verifySignatureBase64(byte[] data, String signBase64) throws Exception {
		return verifySignature(data, Base64.decodeBase64(signBase64));
	}

	@Override
	public boolean verifySignatureBase64(String text, String signBase64) throws Exception {
		return verifySignature(text.getBytes(getCharset()), Base64.decodeBase64(signBase64));
	}

	@Override
	public boolean verifySignatureBase64(String text, String charset, String signBase64) throws Exception {
		return verifySignature(text.getBytes(charset), Base64.decodeBase64(signBase64));
	}

	@Override
	public boolean verifySignatureHex(byte[] data, String signHex) throws Exception {
		return verifySignature(data, Hex.decodeHex(signHex.toCharArray()));
	}

	@Override
	public boolean verifySignatureHex(String text, String signHex) throws Exception {
		return verifySignature(text.getBytes(getCharset()), Hex.decodeHex(signHex.toCharArray()));
	}

	@Override
	public boolean verifySignatureHex(String text, String charset, String signHex) throws Exception {
		return verifySignature(text.getBytes(charset), Hex.decodeHex(signHex.toCharArray()));
	}

}
