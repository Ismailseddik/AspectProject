package com.example.AspectProject.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);
    private static final long WARNING_THRESHOLD_MS = 500;

    @Around("execution(* com.example.AspectProject.services..(..)) || execution( com.example.AspectProject.controllers..*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long durationMs = (System.nanoTime() - start) / 1_000_000;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringType().getSimpleName() + "." + signature.getName();

        if (durationMs > WARNING_THRESHOLD_MS) {
            logger.warn("⚠  Method {} took {} ms (slow)", methodName, durationMs);
        } else {
            logger.info("⏱ Method {} executed in {} ms", methodName, durationMs);
        }

        return result;
    }
}
