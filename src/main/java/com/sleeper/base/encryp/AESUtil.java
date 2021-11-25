package com.sleeper.base.encryp;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * @author sleeper
 * @date 2021-11-17
 */
public class AESUtil {

    private static final byte[] KEYS = "ADEQ123VDDEQ67XP".getBytes(StandardCharsets.UTF_8);

    /**
     * 加密
     * @param param
     * @return
     */
    public static String encrypt(String param) {
        AES aes = SecureUtil.aes(KEYS);
        return aes.encryptHex(param);
    }

    /**
     * 解密
     * @param param
     * @return
     */
    public static String decrypt(String param) {
        AES aes = SecureUtil.aes(KEYS);
        return aes.decryptStr(param);
    }
}

