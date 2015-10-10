package com.cplatform.security.signature;

public class SignatureRsaSha512Test extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() throws Exception {
		RsaSha512SignatureProvider provider = new RsaSha512SignatureProvider();
		provider.setLocalPrivateKeyFile("./key/rsa_1024_private.der");
		provider.setRemotePublicKeyFile("./key/rsa_1024_public.der");

		return provider;
	}

}
