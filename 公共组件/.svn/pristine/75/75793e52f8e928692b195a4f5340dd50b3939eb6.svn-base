package com.cplatform.security.signature;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cplatform.security.SecurityTestCase;

public abstract class AbsSignatureTest extends SecurityTestCase {

	private SignatureProvider provider;

	@Before
	public void beforeTest() throws Exception {
		provider = initProvider();
		provider.setCharset(getCharset());
	}

	public SignatureProvider getProvider() {
		return provider;
	}

	public abstract SignatureProvider initProvider() throws Exception;

	public void setProvider(SignatureProvider provider) {
		this.provider = provider;
	}

	@Test
	public void testSignature() throws Exception {
		String rawText = generateRawText(1024);
		byte[] rawBytes = rawText.getBytes(getCharset());
		List<byte[]> signs = new ArrayList<byte[]>();
		signs.add(provider.createSignature(rawBytes));
		signs.add(provider.createSignature(rawText));
		signs.add(provider.createSignature(rawText, getCharset()));
		signs.add(Base64.decodeBase64(provider.createSignatureBase64(rawBytes)));
		signs.add(Base64.decodeBase64(provider.createSignatureBase64(rawText)));
		signs.add(Base64.decodeBase64(provider.createSignatureBase64(rawText, getCharset())));
		signs.add(Hex.decodeHex(provider.createSignatureHex(rawBytes).toCharArray()));
		signs.add(Hex.decodeHex(provider.createSignatureHex(rawText).toCharArray()));
		signs.add(Hex.decodeHex(provider.createSignatureHex(rawText, getCharset()).toCharArray()));

		for (int i = 0; i < signs.size(); i++) {
			byte[] bs = signs.get(i);
			Assert.assertTrue("签名异常", provider.verifySignature(rawBytes, bs));
		}
	}

	@Test
	public void testSignaturePerformanceSignature() throws Exception {
		String rawText = generateRawText(1024);
		int count = 2000;
		for (int i = 0; i < 100; i++) {
			provider.createSignatureHex(rawText, getCharset());
		}
		double tm0 = System.nanoTime();
		for (int i = 0; i < count; i++) {
			provider.createSignatureHex(rawText, getCharset());
		}
		double tm1 = System.nanoTime();
		logger.info("性能测试结果-签名: {}次, {}ms, 平均每个{}ms", count, String.format("%.2f", (tm1 - tm0) / 1000 / 1000), String.format("%.2f", (tm1 - tm0) / 1000 / 1000 / count));
	}

	@Test
	public void testVerify() throws Exception {
		String rawText = generateRawText(1024);
		byte[] rawBytes = rawText.getBytes(getCharset());
		byte[] signBytes = provider.createSignature(rawBytes);
		String signBase64 = Base64.encodeBase64String(signBytes);
		String signHex = Hex.encodeHexString(signBytes);

		Assert.assertTrue("签名不符", provider.verifySignature(rawBytes, signBytes));
		Assert.assertTrue("签名不符", provider.verifySignatureBase64(rawBytes, signBase64));
		Assert.assertTrue("签名不符", provider.verifySignatureBase64(rawText, signBase64));
		Assert.assertTrue("签名不符", provider.verifySignatureBase64(rawText, getCharset(), signBase64));
		Assert.assertTrue("签名不符", provider.verifySignatureHex(rawBytes, signHex));
		Assert.assertTrue("签名不符", provider.verifySignatureHex(rawText, signHex));
		Assert.assertTrue("签名不符", provider.verifySignatureHex(rawText, getCharset(), signHex));
	}

	@Test
	public void testVerifyPerformance() throws Exception {
		String rawText = generateRawText(1024);
		byte[] rawBytes = rawText.getBytes(getCharset());
		byte[] signBytes = provider.createSignature(rawBytes);
		String signHex = Hex.encodeHexString(signBytes);
		//
		int count = 2000;
		for (int i = 0; i < 100; i++) {
			provider.verifySignatureHex(rawText, getCharset(), signHex);
		}
		double tm0 = System.nanoTime();
		for (int i = 0; i < count; i++) {
			provider.verifySignatureHex(rawText, getCharset(), signHex);
		}
		double tm1 = System.nanoTime();
		logger.info("性能测试结果-验证: {}次, {}ms, 平均每个{}ms", count, String.format("%.2f", (tm1 - tm0) / 1000 / 1000), String.format("%.2f", (tm1 - tm0) / 1000 / 1000 / count));
	}
}
