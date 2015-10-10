package com.cplatform.security.encrypt;

public class AesEncryptTest extends AbsEncryptTest {

	@Override
	public EncryptProvider initProvider() throws Exception {
		AesEncryptProvider provider = new AesEncryptProvider();
		provider.setKeyBytes(provider.generateKeyBytes(256));
		return provider;
	}
}
