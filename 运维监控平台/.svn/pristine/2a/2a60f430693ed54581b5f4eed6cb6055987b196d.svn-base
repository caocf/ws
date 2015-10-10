package com.cplatform.mall.dc.utils;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 采用AES/CBC/PKCS5Padding进行对称加密、解密
 *
 * 初始化向量集成在程序， key从配置文件读取
 *
 * 用于cookie token的加解密
 *
 */
@Component
public class AesWithCbcService {

    @Autowired
    AppConfig appConfig;

    // 内部iv编码
    private static byte[] IV = {5, 3, 3, 2, 1, 0, 14, 29, 24, 86, 42, 78, 49, 14, -87, -9};

    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private Key key;
    private AlgorithmParameterSpec ivspec;

    @PostConstruct
    public void init() {
        byte[] tt = appConfig.getCookiesKey().getBytes();
        key = new SecretKeySpec(tt, KEY_ALGORITHM);
        ivspec = new IvParameterSpec(IV);
    }

    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
        return Hex.encodeHexString(cipher.doFinal(text.getBytes(CharEncoding.UTF_8)));
    }

    public String decrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
        return new String(cipher.doFinal(Hex.decodeHex(text.toCharArray())), CharEncoding.UTF_8);
    }

}