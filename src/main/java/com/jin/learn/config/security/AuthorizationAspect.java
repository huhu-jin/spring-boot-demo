package com.jin.learn.config.security;


import com.jin.learn.exception.ExceptionCode;
import com.jin.learn.exception.SystemException;
import com.jin.learn.service.AuthorizationService;
import com.jin.learn.until.CacheUtil;
import com.jin.learn.until.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Set;


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final AuthorizationService authorizationService;

    private final CacheUtil cacheUtil;

    private static final String PRE = "AUTH";

    @Before("@annotation(com.jin.learn.config.security.Authorize)")
    public void checkPermission(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 查询用户拥有权限
        Long accountId = JWTUtil.getUserIdFromRequest(request);
        Set<String> authorization = cacheUtil.getAllSet(PRE + accountId);
        if(authorization==null){
            authorization = authorizationService.findByAccountId(accountId);
            cacheUtil.save(PRE + accountId, authorization);
        }
        Authorize authorize = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Authorize.class);
        String[] needAuthorization = authorize.value();
        if (needAuthorization.length == 0)  return;
        if (authorization!=null && !authorization.isEmpty()) {
            if (!authorization.containsAll(Arrays.asList(needAuthorization))){
            // 无操作权限
                throw new SystemException(ExceptionCode.NO_PERMISSION);
            }
        } else {
            throw new SystemException(ExceptionCode.NO_PERMISSION);
        }
    }
}
