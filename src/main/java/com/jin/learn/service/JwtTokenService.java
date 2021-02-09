package com.jin.learn.service;

import com.jin.learn.dto.PayloadDto;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;

import java.text.ParseException;

public interface JwtTokenService {


    String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException;


    PayloadDto verifyTokenByHMAC(String token, String secret) throws JOSEException, ParseException;


    String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException;


    PayloadDto verifyTokenByRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException;

    RSAKey getDefaultRSAKey();
}
