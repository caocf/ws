package com.cplatform.security.encrypt;

public class AesEncryptProvider extends SymmetryEncryptProvider {

	private int adjustKeySize(int keySize) {
		if (keySize <= 128) {
			return 128;
		} else if (keySize <= 192) {
			return 192;
		} else {
			return 256;
		}
	}

	@Override
	public byte[] generateKeyBytes(int keySize) throws Exception {
		return super.generateKeyBytes(adjustKeySize(keySize));
	}

	@Override
	public String generateKeyHex(int keySize) throws Exception {
		return super.generateKeyHex(adjustKeySize(keySize));
	}

	@Override
	public String getAlgorithm() {
		return "AES";
	}
}
