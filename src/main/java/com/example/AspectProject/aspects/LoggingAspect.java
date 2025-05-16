package com.example.AspectProject.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Apply to all public methods in services and controllers
    @Around("execution(public * com.example.AspectProject.services..(..)) || execution(public * com.example.AspectProject.controllers..(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String arguments = Arrays.toString(joinPoint.getArgs());

        logger.info("➡ Entering {}.{} with args {}", className, methodName, arguments);

        long startTime = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            long durationMs = (System.nanoTime() - startTime) / 1_000_000;

            logger.info("✅ Exiting {}.{} - took {} ms", className, methodName, durationMs);
            return result;

        } catch (Throwable ex) {
            logger.error("❌ Exception in {}.{}: {}", className, methodName, ex.toString());
            throw ex; // rethrow to not break the app
        }
    }
}