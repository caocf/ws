package com.cplatform.security.encrypt;

public class Rc4EncryptTest extends AbsEncryptTest {

	@Override
	public EncryptProvider initProvider() throws Exception {
		DesEncryptProvider provider = new DesEncryptProvider();
		provider.setKeyBytes(provider.generateKeyBytes(512));
		return provider;
	}
}
