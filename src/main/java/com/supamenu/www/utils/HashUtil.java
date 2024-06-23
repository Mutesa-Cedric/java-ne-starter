package com.supamenu.www.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashUtil {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, hashedPassword);
    }
}
