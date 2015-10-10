package com.cplatform.security.signature;

public class SignatureRsaSha1Test extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() throws Exception {
		RsaSha1SignatureProvider provider = new RsaSha1SignatureProvider();
		provider.setLocalPrivateKeyFile("./key/rsa_1024_private.der");
		provider.setRemotePublicKeyFile("./key/rsa_1024_public.der");

		return provider;
	}

}
