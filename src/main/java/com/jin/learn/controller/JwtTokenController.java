package com.jin.learn.controller;


import com.alibaba.fastjson.JSONObject;
import com.jin.learn.dto.ApiResponse;
import com.jin.learn.dto.PayloadDto;
import com.jin.learn.exception.ExceptionCode;
import com.jin.learn.service.JwtTokenService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("token")
public class JwtTokenController {

    byte[] screctKey = DigestUtils.md5Digest("testKey".getBytes());


    @Autowired
    private JwtTokenService jwtTokenService;

    @RequestMapping(value = "/hmac/generate", method = RequestMethod.GET)
    public ApiResponse generateTokenByHMAC() throws JOSEException {

        PayloadDto payloadDto = getDefaultPayloadDto();
        String token = jwtTokenService.generateTokenByHMAC(JSONObject.toJSONString(payloadDto), new String(screctKey));
        return ApiResponse.OK(token);

    }

    @PostMapping(value = "/hmac/verify")
    public ApiResponse verifyTokenByHMAC(@RequestBody String token) throws ParseException, JOSEException {
        PayloadDto payloadDto = jwtTokenService.verifyTokenByHMAC(token, new String(screctKey));
        return ApiResponse.OK(payloadDto);
    }


    @RequestMapping(value = "/rsa/publicKey", method = RequestMethod.GET)
    public Object getRSAPublicKey() {
        RSAKey key = jwtTokenService.getDefaultRSAKey();
        return new JWKSet(key).toJSONObject();
    }


    @RequestMapping(value = "/rsa/generate", method = RequestMethod.GET)
    public ApiResponse generateTokenByRSA() {
        try {
            PayloadDto payloadDto = getDefaultPayloadDto();
            String token = jwtTokenService.generateTokenByRSA(JSONObject.toJSONString(payloadDto), jwtTokenService.getDefaultRSAKey());
            return ApiResponse.OK(token);
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return ApiResponse.ERROR(ExceptionCode.FAILED);
    }

    @PostMapping(value = "/rsa/verify")
    public ApiResponse verifyTokenByRSA(@RequestBody String token) {
        try {
            PayloadDto payloadDto = jwtTokenService.verifyTokenByRSA(token, jwtTokenService.getDefaultRSAKey());
            return ApiResponse.OK(payloadDto);
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return ApiResponse.ERROR(ExceptionCode.FAILED);
    }


    private PayloadDto getDefaultPayloadDto() {

        Date now = new Date();
        Date exp = new Date(60 * 60 * 1000 * 24 + now.getTime());
        return PayloadDto.builder()
                .sub("macro")
                .iat(now.getTime())
                .exp(exp.getTime())
                .jti(UUID.randomUUID().toString())
                .username("macro")
                .authorities(Arrays.asList("ADMIN", "READONLY_ADMIN"))
                .build();
    }


}
