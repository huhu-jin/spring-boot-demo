package com.jin.learn.security;

import com.alibaba.fastjson.JSONObject;
import com.jin.learn.dto.ApiResponse;
import com.jin.learn.until.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * 权限过滤器
 */
@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static List<String> notFilterUrls = new LinkedList<>();

    static {
        notFilterUrls.add("/demo/free");
        notFilterUrls.add("/demo/authorize/**");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    // 不需要验证的url
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        for (String urlPattern : notFilterUrls) {
            if (antPathMatcher.match(requestURI, urlPattern)) return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (JWTUtil.verifyToken(request)) {
            // 验证通过
            UserDetails userDetails;
            String username = JWTUtil.getUsernameFromRequest(request);
            try {
                userDetails = userDetailsService.loadUserByUsername(username);// 查出权限
            } catch (UsernameNotFoundException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
            // 让@PreAuthorize("hasAuthority('reborn')") 起作用
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            log.info("authorized user '{}', setting security context", username);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 放入上下文环境
            chain.doFilter(request, response);
        }
        // 验证失败
        ServletOutputStream out = response.getOutputStream();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String message = JSONObject.toJSONString(new ApiResponse(1001, "token无效", ""));
        out.write(message.getBytes(Charset.defaultCharset()));
        out.flush();


    }

}