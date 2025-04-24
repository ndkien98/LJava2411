package com.t3h.sercurity;

import com.t3h.utils.EncryptUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {   // tao ra password
        return EncryptUtils.crc32PasswordEncoder(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) { // so sanh pass duoc tao ra va chuoi truyen vao
        return s.equals(EncryptUtils.crc32PasswordEncoder(charSequence.toString()));
    }

    public static void main(String[] args) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();

        System.out.printf(customPasswordEncoder.encode("admin"));
    }
}
