package com.revature.bookstore.util;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect // This allows AspectJWeaver to see this component
@Component // This allows Spring to take and work with this component.
public class LoggingAspect{

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.revature..*") // Pointcut Expression Language
    public void logAll() {}

    // Aspect methods encapsulate "advice" logic
    @Before("logAll()")
    public void logMethodStart(JoinPoint jp) {

        // The joinPoint object represents the point in which we are injecting this logic.
        String methodSig = extractMethodSignature(jp);
        String argStr = Arrays.toString(jp.getArgs());
        logger.info("{} invoked at {}", methodSig, LocalDateTime.now());
        logger.info("Input arguments: {}", argStr);
    }

    @AfterReturning(pointcut = "logAll()", returning = "returnedObj")
    public void logMethodReturn(JoinPoint jp, Object returnedObj){
        String methodSig = extractMethodSignature(jp);
        String argStr = Arrays.toString(jp.getArgs());
        logger.info("{} invoked at {}", methodSig, LocalDateTime.now());
        logger.info("Input arguments: {}", argStr);
    }

    @AfterThrowing(pointcut = "logAll()", throwing = "e")
    public void logMethodException(JoinPoint jp, Throwable e){
        String methodSig = extractMethodSignature(jp);
        String exceptionName = e.getClass().getSimpleName();
        logger.warn("{} was thrown in method {} at {} with message: {}", exceptionName, methodSig, LocalDateTime.now(), e.getMessage());
    }

    // This is not an advice method; just a helper, and an unnecessary one.
    private String extractMethodSignature(JoinPoint jp) {
        return jp.getTarget().getClass().toString() + "#" + jp.getSignature().getName();
    }
}
