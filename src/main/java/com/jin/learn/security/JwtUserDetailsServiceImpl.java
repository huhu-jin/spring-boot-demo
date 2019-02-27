package com.jin.learn.security;

import com.jin.learn.dao.AccountMapper;
import com.jin.learn.dao.AuthorityMapper;
import com.jin.learn.dao.RoleMapper;
import com.jin.learn.entity.Account;
import com.jin.learn.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现了UserDetailService 查出用户和相关权限
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.selectByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        // 拼成 权限
        List<Authority> authority = authorityMapper.selectByAccountId(account.getId());
        account.setAuthorities(authority);
        return account;
    }
}