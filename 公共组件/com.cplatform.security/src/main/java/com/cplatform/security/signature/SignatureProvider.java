package com.cplatform.security.signature;

public interface SignatureProvider {

	byte[] createSignature(byte[] data) throws Exception;

	byte[] createSignature(String text) throws Exception;

	byte[] createSignature(String text, String charset) throws Exception;

	String createSignatureBase64(byte[] data) throws Exception;

	String createSignatureBase64(String text) throws Exception;

	String createSignatureBase64(String text, String charset) throws Exception;

	String createSignatureHex(byte[] data) throws Exception;

	String createSignatureHex(String text) throws Exception;

	String createSignatureHex(String text, String charset) throws Exception;

	String getCharset();

	String getSignatureAlgorithm();

	void setCharset(String charset);

	boolean verifySignature(byte[] data, byte[] sign) throws Exception;

	boolean verifySignatureBase64(byte[] data, String signBase64) throws Exception;

	boolean verifySignatureBase64(String text, String signBase64) throws Exception;

	boolean verifySignatureBase64(String text, String charset, String signBase64) throws Exception;

	boolean verifySignatureHex(byte[] data, String signHex) throws Exception;

	boolean verifySignatureHex(String text, String signHex) throws Exception;

	boolean verifySignatureHex(String text, String charset, String signHex) throws Exception;

}
