package com.cplatform.security.encrypt;

public class DesedeEncryptProvider extends SymmetryEncryptProvider {

	private int adjustKeySize(int keySize) {
		if (keySize < 168) {
			return 112;
		} else {
			return 168;
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
		return "DESede";
	}

}
