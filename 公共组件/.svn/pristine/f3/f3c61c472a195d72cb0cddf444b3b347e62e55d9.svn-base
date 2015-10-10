package com.cplatform.security.encrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cplatform.security.SecurityTestCase;

public abstract class AbsEncryptTest extends SecurityTestCase {

	private EncryptProvider provider;

	@Before
	public void beforeTest() throws Exception {
		provider = initProvider();
		provider.setCharset(getCharset());
	}

	public EncryptProvider getProvider() {
		return provider;
	}

	public abstract EncryptProvider initProvider() throws Exception;

	@Test
	public void testDecrypt() throws Exception {
		String rawText = generateRawText(1024);
		byte[] rawBytes = rawText.getBytes(getCharset());
		List<byte[]> encryptDatas = new ArrayList<>();

		encryptDatas.add(provider.encrypt(rawBytes));
		encryptDatas.add(provider.encrypt(rawText));
		encryptDatas.add(provider.encrypt(rawText, getCharset()));
		encryptDatas.add(Base64.decodeBase64(provider.encryptBase64(rawBytes)));
		encryptDatas.add(Base64.decodeBase64(provider.encryptBase64(rawText)));
		encryptDatas.add(Base64.decodeBase64(provider.encryptBase64(rawText, getCharset())));
		encryptDatas.add(Hex.decodeHex(provider.encryptHex(rawBytes).toCharArray()));
		encryptDatas.add(Hex.decodeHex(provider.encryptHex(rawText).toCharArray()));
		encryptDatas.add(Hex.decodeHex(provider.encryptHex(rawText, getCharset()).toCharArray()));

		for (int i = 0; i < encryptDatas.size(); i++) {
			byte[] bs0 = encryptDatas.get(i);
			byte[] bs1 = provider.decrypt(bs0);
			boolean b = Arrays.equals(bs1, rawBytes);
			Assert.assertTrue("解密异常", b);
		}
	}

	@Test
	public void testDecryptPerformace() throws Exception {
		String rawText = generateRawText(1024);
		String dataHex = provider.encryptHex(rawText);
		int count = 2000;
		for (int i = 0; i < 100; i++) {
			provider.decryptHexToString(dataHex);
		}
		double tm0 = System.nanoTime();
		for (int i = 0; i < count; i++) {
			provider.decryptHexToString(dataHex);
		}
		double tm1 = System.nanoTime();
		logger.info("性能测试结果-解密: {}次, {}ms, 平均每个{}ms", count, String.format("%.2f", (tm1 - tm0) / 1000 / 1000), String.format("%.2f", (tm1 - tm0) / 1000 / 1000 / count));
	}

	@Test
	public void testEncrypt() throws Exception {
		String rawText = generateRawText(1024);
		byte[] rawBytes = rawText.getBytes(getCharset());

		byte[] dataBytes = provider.encrypt(rawBytes);
		String dataHex = Hex.encodeHexString(dataBytes);
		String dataBase64 = Base64.encodeBase64String(dataBytes);

		Assert.assertArrayEquals("解密异常", rawBytes, provider.decrypt(dataBytes));
		Assert.assertArrayEquals("解密异常", rawBytes, provider.decryptBase64(dataBase64));
		Assert.assertArrayEquals("解密异常", rawBytes, provider.decryptHex(dataHex));
		Assert.assertEquals("解密异常", rawText, provider.decryptBase64ToString(dataBase64, getCharset()));
		Assert.assertEquals("解密异常", rawText, provider.decryptHexToString(dataHex));
		Assert.assertEquals("解密异常", rawText, provider.decryptHexToString(dataHex, getCharset()));
		Assert.assertEquals("解密异常", rawText, provider.decryptToString(dataBytes));
		Assert.assertEquals("解密异常", rawText, provider.decryptToString(dataBytes, getCharset()));
	}

	@Test
	public void testEncryptPerformace() throws Exception {
		String rawText = generateRawText(1024);
		int count = 2000;
		for (int i = 0; i < 100; i++) {
			provider.encryptHex(rawText, getCharset());
		}
		double tm0 = System.nanoTime();
		for (int i = 0; i < count; i++) {
			provider.encryptHex(rawText, getCharset());
		}
		double tm1 = System.nanoTime();
		logger.info("性能测试结果-加密: {}次, {}ms, 平均每个{}ms", count, String.format("%.2f", (tm1 - tm0) / 1000 / 1000), String.format("%.2f", (tm1 - tm0) / 1000 / 1000 / count));
	}
}
