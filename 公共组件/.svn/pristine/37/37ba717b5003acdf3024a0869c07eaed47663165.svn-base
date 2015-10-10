package com.cplatform.security.encrypt;

public class DesEncryptTest extends AbsEncryptTest {

	@Override
	public EncryptProvider initProvider() throws Exception {
		Rc4EncryptProvider provider = new Rc4EncryptProvider();
		provider.setKeyBytes(provider.generateKeyBytes(512));
		return provider;
	}
}
