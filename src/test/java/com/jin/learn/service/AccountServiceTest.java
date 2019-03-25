package com.jin.learn.service;

import com.jin.learn.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;


    @Test
    public void testNestingTransaction () {
        accountService.nestingTransaction();
    }



}
