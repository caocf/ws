package com.cplatform.security.signature;

public class EccSignatureProvider extends AsymmetrySignatureProvider {

	public static final String ALGORITHM = "EC";

	public static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";

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
