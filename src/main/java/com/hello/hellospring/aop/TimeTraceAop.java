package com.hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component    //스프링 빈에 등록. (이 방법보다 @Bean으로 스프링빈에 직접 등록하는 방법 선호)
@Aspect
public class TimeTraceAop {

    // 공통 관심사항을 어디세 적용할지 targetting
    @Around("execution(* com.hello.hellospring..*(..)) && !target(com.hello.hellospring.SpringConfig)")
//    @Around("execution(* com.hello.hellospring..*(..))")

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            Object result = joinPoint.proceed();    //다음 메소드로 진행
            return result;
        } finally {
            long finish = System. currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
