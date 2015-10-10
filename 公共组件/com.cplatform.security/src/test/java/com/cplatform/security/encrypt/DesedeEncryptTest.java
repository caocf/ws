package com.cplatform.security.encrypt;

public class DesedeEncryptTest extends AbsEncryptTest {

	@Override
	public EncryptProvider initProvider() throws Exception {
		DesedeEncryptProvider provider = new DesedeEncryptProvider();
		provider.setKeyBytes(provider.generateKeyBytes(512));
		return provider;
	}
}
