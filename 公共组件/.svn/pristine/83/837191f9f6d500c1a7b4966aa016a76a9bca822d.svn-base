package com.cplatform.security.signature;

public class SignatureDsaTest extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() throws Exception {
		setCharset("GB18030");

		DsaSignatureProvider provider = new DsaSignatureProvider();
		provider.setLocalPrivateKeyFile("./key/dsa_512_private.der");
		provider.setRemotePublicKeyFile("./key/dsa_512_public.der");

		return provider;
	}

}
