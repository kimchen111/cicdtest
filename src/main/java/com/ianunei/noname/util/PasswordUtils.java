package com.ianunei.noname.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 帅小鸦
 * @date 2022/6/27
 */

public class PasswordUtils {
    private final static PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String encrypt(String pwd) {
        return PASSWORD_ENCODER.encode(pwd);
    }

    public static boolean matches(String pwd, String bCrypt) {
        return PASSWORD_ENCODER.matches(pwd, bCrypt);
    }
}
