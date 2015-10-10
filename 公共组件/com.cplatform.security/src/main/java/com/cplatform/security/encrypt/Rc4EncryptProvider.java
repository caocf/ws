package com.cplatform.security.encrypt;

public class Rc4EncryptProvider extends SymmetryEncryptProvider {

	private int adjustKeySize(int keySize) {
		if (keySize < 40) {
			return 40;
		} else if (keySize > 1024) {
			return 1024;
		} else {
			return keySize;
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
		return "RC4";
	}
}
