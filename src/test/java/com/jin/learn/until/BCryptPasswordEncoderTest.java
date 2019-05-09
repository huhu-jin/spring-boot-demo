package com.jin.learn.until;

import com.jin.learn.until.bcrypt.BCryptPasswordEncoder;
import org.junit.Test;

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
