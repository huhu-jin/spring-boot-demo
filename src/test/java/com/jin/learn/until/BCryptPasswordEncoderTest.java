package com.jin.learn.until;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {

    @Test
    public void testEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String jin = bCryptPasswordEncoder.encode("jin");
        System.out.println(jin);
        boolean matches = bCryptPasswordEncoder.matches("jin", jin);
        System.out.println(matches);
    }
}
