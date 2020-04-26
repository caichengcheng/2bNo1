package springall.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogProxy {
    @Pointcut("execution(* springall.aop.BusinessService.*(..))")
    public void pointMethod(){}
    @Around("pointMethod()")
    public Object arround(ProceedingJoinPoint point) throws Throwable{
        System.out.println("enter aop proxy");
        Object proceed = point.proceed();
        System.out.println("exit aop proxy");
        return proceed;
    }
}
