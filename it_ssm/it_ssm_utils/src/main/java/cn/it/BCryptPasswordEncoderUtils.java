package cn.it;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    public static String passwordEncoder(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        return encode;
    }

    public static void main(String[] args) {
        String s = passwordEncoder("admin");
        System.out.println(s);
    }
}
