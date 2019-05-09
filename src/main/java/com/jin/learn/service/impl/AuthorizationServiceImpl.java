package com.jin.learn.service.impl;

import com.jin.learn.dao.AuthorityMapper;
import com.jin.learn.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorityMapper authorityMapper;


    @Override
    public Set<String> findByAccountId(Long id) {
        return authorityMapper.findByAccountId(id);
    }
}
