package com.jin.learn.security;

import com.jin.learn.dao.AccountMapper;
import com.jin.learn.dao.RoleMapper;
import com.jin.learn.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.selectByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        // 拼成 权限
        roleMapper.selectByPrimaryKey(account.getId());
        return account;
    }
}