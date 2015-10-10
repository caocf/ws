package com.cplatform.security.signature;

import java.io.File;
import java.security.Key;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

public abstract class MacSignatureProvider extends AbstractSignatureProvider {

	private Key key;

	private byte[] keyBytes;

	private String keyFile;

	private String keyHex;

	@Override
	public byte[] createSignature(byte[] data) throws Exception {
		Mac mac = Mac.getInstance(getSignatureAlgorithm());
		mac.init(getKey());
		mac.update(data);
		return mac.doFinal();
	}

	public byte[] generateKeyBytes(int keySize) throws Exception {
		KeyGenerator keyFactory = KeyGenerator.getInstance(getAlgorithm());
		keyFactory.init(keySize);
		SecretKey key = keyFactory.generateKey();
		return key.getEncoded();
	}

	public String generateKeyHex(int keySize) throws Exception {
		return Hex.encodeHexString(generateKeyBytes(keySize));
	}

	public abstract String getAlgorithm();

	public Key getKey() {
		return key;
	}

	public byte[] getKeyBytes() {
		return keyBytes;
	}

	public String getKeyFile() {
		return keyFile;
	}

	public String getKeyHex() {
		return keyHex;
	}

	public void setKeyBytes(byte[] keyBytes) throws Exception {
		key = new SecretKeySpec(keyBytes, getAlgorithm());
		this.keyBytes = keyBytes;
	}

	public void setKeyFile(String keyFile) throws Exception {
		byte[] bytes = FileUtils.readFileToByteArray(new File(keyFile));
		setKeyBytes(bytes);
		this.keyFile = keyFile;
	}

	public void setKeyHex(String keyHex) throws Exception {
		byte[] bytes = Hex.decodeHex(keyHex.toCharArray());
		setKeyBytes(bytes);
		this.keyHex = keyHex;
	}

	@Override
	public boolean verifySignature(byte[] data, byte[] sign) throws Exception {
		byte[] thisSign = createSignature(data);
		return Arrays.equals(thisSign, sign);
	}
}
