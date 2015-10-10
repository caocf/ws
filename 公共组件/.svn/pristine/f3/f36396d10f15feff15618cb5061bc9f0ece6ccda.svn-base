package com.cplatform.security.signature;

public class SignatureEccTest extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() throws Exception {
		EccSignatureProvider provider = new EccSignatureProvider();
		provider.setLocalPrivateKeyFile("./key/ecc_160_private.der");
		provider.setRemotePublicKeyFile("./key/ecc_160_public.der");
		return provider;
	}

}
