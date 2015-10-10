package com.cplatform.security.signature;

import java.security.MessageDigest;
import java.util.Arrays;

public abstract class DigestSignatureProvider extends AbstractSignatureProvider {

	@Override
	public byte[] createSignature(byte[] data) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(getSignatureAlgorithm());
		return digest.digest(data);
	}

	@Override
	public boolean verifySignature(byte[] data, byte[] sign) throws Exception {
		byte[] thisSign = createSignature(data);
		return Arrays.equals(thisSign, sign);
	}
}
