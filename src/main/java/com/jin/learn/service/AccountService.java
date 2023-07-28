package com.jin.learn.service;

import com.jin.learn.entity.Account;

public interface AccountService {

    void nestingTransaction();

    Account findByUsername(String username);

    void create(Account account);

    /**
     * 从用户账户中借出
     * @param userId
     * @param money
     */
    void debit(String userId, int money);

}
