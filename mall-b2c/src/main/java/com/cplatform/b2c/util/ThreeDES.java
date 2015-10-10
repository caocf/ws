package com.cplatform.b2c.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class ThreeDES {

	// private static final String PASSWORD_CRYPT_KEY = "MYKEY112";
	private static final String IV = "1234567-";

	/**
	 * DESCBC加密
	 * 
	 * @param src
	 *            数据溿
	 * @param key
	 *            密钥，长度必须是8的怦տ
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static String encryptDESCBC(String src, String key) throws Exception {

		// --生成key,同时制定是des还是DESede,两key长度要求不同
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		// --加密向量
		IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

		// --通过Chipher执行加密得到的是byte的数绿Cipher.getInstance("DES")就是采用ECB模式,cipher.init(Cipher.ENCRYPT_MODE,
		// secretKey)就可以了.
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] b = cipher.doFinal(src.getBytes("UTF-8"));

		// --通过base64,将加密数组转换成字符丿
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b);
	}

	/**
	 * DESCBC解密
	 * 
	 * @param src
	 *            数据
	 * @param key
	 *            密钥，长度必须是8的
	 * @return 返回解密后的原妾数据
	 * @throws Exception
	 */
	public static String decryptDESCBC(String src, String key) throws Exception {
		// --通过base64,将字符串转成byte数组
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytesrc = decoder.decodeBuffer(src);

		// --解密的key
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		// --向量
		IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

		// --Chipher对象解密Cipher.getInstance("DES")就是采用ECB模式,cipher.init(Cipher.DECRYPT_MODE,
		// secretKey)就可以了.
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);

		return new String(retByte);

	}

	// 3DESECB加密,key必须是长度大于等亿3*8 = 24 位哈
	public static String encryptThreeDESECB(String src, String key) throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		byte[] b = cipher.doFinal(src.getBytes());

		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");

	}

	// 3DESECB解密,key必须是长度大于等亿3*8 = 24 位哈
	public static String decryptThreeDESECB(String src, String key) throws Exception {
		// --通过base64,将字符串转成byte数组
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytesrc = decoder.decodeBuffer(src);
		// --解密的key
		DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey securekey = keyFactory.generateSecret(dks);

		// --Chipher对象解密
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, securekey);
		byte[] retByte = cipher.doFinal(bytesrc);

		return new String(retByte);
	}

}