package com.revature.bookstore.web.util.security;

import com.revature.bookstore.util.exceptions.AuthenticationException;
import com.revature.bookstore.util.exceptions.AuthorizationException;
import com.revature.bookstore.web.dtos.Principal;
import com.revature.bookstore.web.intercom.AuthServiceClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class SecurityAspect {

    private final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

    private AuthServiceClient authClient;

    @Autowired
    public SecurityAspect(AuthServiceClient authClient) {
        this.authClient = authClient;
    }

    @Around("@annotation(com.revature.bookstore.web.util.security.Secured)")
    public Object secureEndpoint(ProceedingJoinPoint pjp) throws Throwable {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Secured securedAnno = method.getAnnotation(Secured.class);
        List<String> allowedUsers = Arrays.asList(securedAnno.allowedUsers());

        HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String token = req.getHeader("Authorization");

        System.out.println(token);

        String authority = authClient.getTokenAuthorities(token);

        if (!allowedUsers.contains(authority)) {
            throw new AuthorizationException("A forbidden request was made");
        }

        return pjp.proceed();

    }

}
