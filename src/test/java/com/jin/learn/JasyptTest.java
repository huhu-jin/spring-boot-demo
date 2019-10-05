package com.jin.learn;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;


public class JasyptTest {

    private static String salt = "abc";


    @Test
    public void testEncrySample() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword(salt);

        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("root123");
        System.out.println("username:"+username);
        System.out.println("password:"+password);

    }

    @Test
    public void testDecrySample() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword(salt);
        String decrypt = textEncryptor.decrypt("sQVyWjOwOZuEpJG4nzloZg==");
        System.out.println(decrypt);

    }
}
