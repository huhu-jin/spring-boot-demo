package com.jin.learn.until;


import com.alibaba.fastjson.JSON;
import com.jin.learn.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class JWTUtil {

    private static final String USERNAME = "username";

    private static final String USER_ID = "id";

    private static final String SECRET = "defaultSecurityCode";

    private static final String JWT_HTTP_HEADER = "Authorization";

    private static final String JWT_TOKEN_START = "Bearer ";

    private static final Integer EXPIRATION = 3600 * 1000 * 6;

    private JWTUtil() {

    }

    public static String doGenerateToken(Account account) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, account.getUsername());
        claims.put(USER_ID, account.getId());
        return doGenerateToken(claims);
    }


    private static String doGenerateToken(Map<String, Object> claims) {
        return JWT_TOKEN_START + Jwts.builder()
                .setClaims(claims)
                .setSubject(JSON.toJSONString(claims))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .compact();
    }


    private static Claims getClaimFromToken(String token) {
        Claims claims = null;
        if (StringUtils.isNotEmpty(token) && token.startsWith(JWT_TOKEN_START)) {
            token = token.substring(JWT_TOKEN_START.length());
            try {
                claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody();
            } catch (ExpiredJwtException e) {
                log.error("token已过期", e);
            } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
                log.error("error", e);
            }
        }
        return claims;
    }


    public static String getUsernameFromToken(String token) {
        return String.valueOf(getClaimFromToken(token).get(USERNAME));
    }

    public static String getUserIdFromToken(String token) {
        return String.valueOf(getClaimFromToken(token).get(USER_ID));
    }


    public static String getUsernameFromRequest(HttpServletRequest request) {
        return getUsernameFromToken(getTokenFromRequest(request));
    }

    public static Long getUserIdFromRequest(HttpServletRequest request) {
        return Long.parseLong(getUserIdFromToken(getTokenFromRequest(request)));
    }


    private static String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(JWT_HTTP_HEADER);
    }


    public static boolean containToken(HttpServletRequest request) {
        return StringUtils.isNotEmpty(request.getHeader(JWT_HTTP_HEADER));
    }

    /**
     * 校验Token是否有效
     *
     * @param request
     * @return
     */
    public static boolean verifyToken(HttpServletRequest request) {
        return Objects.nonNull(getClaimFromToken(getTokenFromRequest(request)));
    }
}
