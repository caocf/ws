package com.cplatform.security.signature;

import java.io.File;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

public abstract class AsymmetrySignatureProvider extends AbstractSignatureProvider {

	private byte[] localPrivateBytes;

	private String localPrivateHex;

	private PrivateKey localPrivateKey;

	private String localPrivateKeyFile;

	private PublicKey remotePublicKey;

	private byte[] remotePublicKeyBytes;

	private String remotePublicKeyFile;

	private String remotePublicKeyHex;

	@Override
	public byte[] createSignature(byte[] data) throws Exception {
		Signature signature = Signature.getInstance(getSignatureAlgorithm());
		signature.initSign(localPrivateKey);
		signature.update(data);
		return signature.sign();
	}

	public abstract String getAlgorithm();

	public byte[] getLocalPrivateBytes() {
		return localPrivateBytes;
	}

	public String getLocalPrivateHex() {
		return localPrivateHex;
	}

	public String getLocalPrivateKeyFile() {
		return localPrivateKeyFile;
	}

	public byte[] getRemotePublicKeyBytes() {
		return remotePublicKeyBytes;
	}

	public String getRemotePublicKeyFile() {
		return remotePublicKeyFile;
	}

	public String getRemotePublicKeyHex() {
		return remotePublicKeyHex;
	}

	public void setLocalPrivateBytes(byte[] localPrivateBytes) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(getAlgorithm());
		KeySpec keySpec = new PKCS8EncodedKeySpec(localPrivateBytes);
		localPrivateKey = keyFactory.generatePrivate(keySpec);
		this.localPrivateBytes = localPrivateBytes;
	}

	public void setLocalPrivateHex(String localPrivateHex) throws Exception {
		byte[] bytes = Hex.decodeHex(localPrivateHex.toCharArray());
		setLocalPrivateBytes(bytes);
		this.localPrivateHex = localPrivateHex;
	}

	public void setLocalPrivateKeyFile(String localPrivateKeyFile) throws Exception {
		byte[] bytes = FileUtils.readFileToByteArray(new File(localPrivateKeyFile));
		setLocalPrivateBytes(bytes);
		this.localPrivateKeyFile = localPrivateKeyFile;
	}

	public void setRemotePublicKeyBytes(byte[] remotePublicKeyBytes) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(getAlgorithm());
		KeySpec keySpec = new X509EncodedKeySpec(remotePublicKeyBytes);
		remotePublicKey = keyFactory.generatePublic(keySpec);
		this.remotePublicKeyBytes = remotePublicKeyBytes;
	}

	public void setRemotePublicKeyFile(String remotePublicKeyFile) throws Exception {
		byte[] bytes = FileUtils.readFileToByteArray(new File(remotePublicKeyFile));
		setRemotePublicKeyBytes(bytes);
		this.remotePublicKeyFile = remotePublicKeyFile;
	}

	public void setRemotePublicKeyHex(String remotePublicKeyHex) throws Exception {
		byte[] bytes = Hex.decodeHex(remotePublicKeyHex.toCharArray());
		setRemotePublicKeyBytes(bytes);
		this.remotePublicKeyHex = remotePublicKeyHex;
	}

	@Override
	public boolean verifySignature(byte[] data, byte[] sign) throws Exception {
		Signature signature = Signature.getInstance(getSignatureAlgorithm());
		signature.initVerify(remotePublicKey);
		signature.update(data);
		return signature.verify(sign);
	}

}
