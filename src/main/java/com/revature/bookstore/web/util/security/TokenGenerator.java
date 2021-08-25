package com.revature.bookstore.web.util.security;

import com.revature.bookstore.web.dtos.Principal;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenGenerator {

    private final JwtConfig jwtConfig;

    public TokenGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Principal subject) {

        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setId(subject.getId())
                                      .setSubject(subject.getUsername())
                    //                .claim("role", subject.getRole().toString()) // specify the role of the user for whom this token is for
                                      .setIssuer("revature")
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return jwtConfig.getPrefix() + tokenBuilder.compact();

    }

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

}
