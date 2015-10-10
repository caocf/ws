package com.cplatform.security.signature;

public class RsaSha512SignatureProvider extends AsymmetrySignatureProvider {

	public static final String ALGORITHM = "RSA";

	public static final String SIGNATURE_ALGORITHM = "SHA512withRSA";

	/**
	 * 获取 algorithm
	 * 
	 * @return algorithm
	 */
	@Override
	public String getAlgorithm() {
		return ALGORITHM;
	}

	/**
	 * 获取 signatureAlgorithm
	 * 
	 * @return signatureAlgorithm
	 */
	@Override
	public String getSignatureAlgorithm() {
		return SIGNATURE_ALGORITHM;
	}
}
