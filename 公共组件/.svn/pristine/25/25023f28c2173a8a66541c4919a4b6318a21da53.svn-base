package com.cplatform.security.signature;

public class SignatureHmacSha256Test extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() throws Exception {
		HmacSha256SignatureProvider provider = new HmacSha256SignatureProvider();
		provider.setKeyBytes(provider.generateKeyBytes(256));

		return provider;
	}

}
