package com.jin.learn.service;

import com.jin.learn.entity.Account;

public interface AccountService {

    void nestingTransaction();

    Account findByUsername(String username);

}
