package com.revature.bookstore.web.util.security;

import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;

@Component
public class JwtConfig {

    private final Logger logger = LoggerFactory.getLogger(JwtConfig.class);

    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.prefix}")
    private String prefix;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
    private SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private Key signingKey;

    public JwtConfig() {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary("Revature");
        signingKey = new SecretKeySpec(secretBytes, sigAlg.getJcaName());
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSecret() {
        return secret;
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }

}
