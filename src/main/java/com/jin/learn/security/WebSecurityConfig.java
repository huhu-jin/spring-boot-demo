package com.jin.learn.security;

import com.jin.learn.security.JWTAuthenticationFilter;
import com.jin.learn.security.JWTAuthenticationEntryPoint;
import com.jin.learn.security.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * https://github.com/spring-projects/spring-security/tree/master/samples 官方例子
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 报错信息
    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // 这个服务获取用户具体的权限粒度
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;


    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // security的认证是通过AuthenticationManager管理的
    // https://www.cnblogs.com/xz816111/p/8528896.html
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());
    }



    // 路由信息配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().
                and().csrf().disable() // csrf　不需要
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and() //　错误信息处理
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 不要产生session
                .authorizeRequests()
                //允许以下请求
                .antMatchers("/demo/free","/demo/authorize/**").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
