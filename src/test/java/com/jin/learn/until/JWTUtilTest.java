package com.jin.learn.until;

import com.jin.learn.entity.Account;
import org.junit.Test;

public class JWTUtilTest {

    @Test
    public void testGenerator() {
        Account account = new Account();
        account.setId(12123L);
        account.setUsername("zhangjin");
        String token = JWTUtil.doGenerateToken(account);
        System.out.println(token);
    }

    @Test
    public void testGetFrom(){
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjEyMTIzLFwidXNlcm5hbWVcIjpcInpoYW5namluXCJ9IiwiaWQiOjEyMTIzLCJleHAiOjE1NTExMTMxOTQsInVzZXJuYW1lIjoiemhhbmdqaW4ifQ.NIGnvsx7yIha-thRDzZm8_gwBJKzi_RhIey67S1otDzp_pD94ceNAa1nVx3NgzrCJpRikdQBJjhCHm76TgYN7g";
        String usernameFromRequest = JWTUtil.getUserIdFromToken(token);
        System.out.println(usernameFromRequest);

    }
}
