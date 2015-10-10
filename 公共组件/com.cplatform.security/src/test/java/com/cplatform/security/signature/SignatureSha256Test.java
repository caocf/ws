package com.cplatform.security.signature;

public class SignatureSha256Test extends AbsSignatureTest {

	@Override
	public SignatureProvider initProvider() {
		setCharset("UTF-8");
		SignatureProvider provider = new Sha256SignatureProvider();
		return provider;
	}

}
