package com.t3h.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;
import java.util.zip.CRC32;

/**
 * Created by tudv on 2019/09/27
 */

public class EncryptUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String getBcryptPasswordEncoder(String pass) {
        return bCryptPasswordEncoder.encode(pass);
    }

    public static String crc32PasswordEncoder(String pass) {
        CRC32 crc32 = new CRC32();
        crc32.update(pass.getBytes());
        return Long.toHexString(crc32.getValue());
    }


    public static String decodeString(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        String dataDecode = new String(decodedBytes);
        return dataDecode;
    }
}
