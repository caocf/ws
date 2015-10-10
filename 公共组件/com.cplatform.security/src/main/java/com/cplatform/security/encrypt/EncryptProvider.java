package com.cplatform.security.encrypt;

public interface EncryptProvider {

	byte[] decrypt(byte[] data) throws Exception;

	byte[] decryptBase64(String base64Text) throws Exception;

	String decryptBase64ToString(String base64Text, String charset) throws Exception;

	byte[] decryptHex(String hexText) throws Exception;

	String decryptHexToString(String hexText) throws Exception;

	String decryptHexToString(String hexText, String charset) throws Exception;

	String decryptToString(byte[] data) throws Exception;

	String decryptToString(byte[] data, String charset) throws Exception;

	byte[] encrypt(byte[] data) throws Exception;

	byte[] encrypt(String text) throws Exception;

	byte[] encrypt(String text, String charset) throws Exception;

	String encryptBase64(byte[] data) throws Exception;

	String encryptBase64(String text) throws Exception;

	String encryptBase64(String text, String charset) throws Exception;

	String encryptHex(byte[] data) throws Exception;

	String encryptHex(String text) throws Exception;

	String encryptHex(String text, String charset) throws Exception;

	String getAlgorithm();

	String getCharset();

	void setCharset(String charset);

}
