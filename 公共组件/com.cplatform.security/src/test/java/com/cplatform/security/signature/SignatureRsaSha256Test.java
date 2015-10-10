package com.cplatform.security.signature;

public class SignatureRsaSha256Test extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() throws Exception {
		RsaSha256SignatureProvider provider = new RsaSha256SignatureProvider();
		provider.setLocalPrivateKeyFile("./key/rsa_1024_private.der");
		provider.setRemotePublicKeyFile("./key/rsa_1024_public.der");

		return provider;
	}

}
