package com.cplatform.security.signature;

public class SignatureHmacSha512Test extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() throws Exception {
		HmacSha512SignatureProvider provider = new HmacSha512SignatureProvider();
		provider.setKeyBytes(provider.generateKeyBytes(256));

		return provider;
	}

}
