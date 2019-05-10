package com.jin.learn.service.impl;

import com.jin.learn.dao.AccountMapper;
import com.jin.learn.entity.Account;
import com.jin.learn.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void nestingTransaction() {
        Account account = new Account();
        account.setUsername("father");
        accountMapper.insert(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountMapper.selectByUsername(username);
    }


}
