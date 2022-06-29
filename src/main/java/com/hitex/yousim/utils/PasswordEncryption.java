package com.hitex.yousim.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryption {
    public static String encryteBCryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }

    public static boolean bCryptPasswordEncoder(String password, String passwordEncryte){
        return new BCryptPasswordEncoder().matches(password, passwordEncryte);
    }
}
