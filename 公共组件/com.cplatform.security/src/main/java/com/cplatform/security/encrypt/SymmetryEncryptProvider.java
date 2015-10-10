package com.cplatform.security.encrypt;

import java.io.File;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

public abstract class SymmetryEncryptProvider extends AbstractEncryptProvider {

	private Key key;

	private byte[] keyBytes;

	private String keyFile;

	private String keyHex;

	@Override
	public byte[] decrypt(byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance(getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, getKey());
		return cipher.doFinal(data);
	}

	@Override
	public byte[] encrypt(byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance(getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, getKey());
		return cipher.doFinal(data);
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

}
