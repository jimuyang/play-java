package muyi.play.web;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: Yang Fan
 * @date: 2019-05-05
 * @desc:
 */
@Component
@Aspect
public class HelloAspect {

    @Pointcut("@within(org.springframework.stereotype.Service)" +
            "|| @within(org.springframework.stereotype.Component)")
    public void servicePointcut() {
    }

    @Around("servicePointcut()")
    public Object serviceRuntimeExceptionCatch(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("hello aspect");
        return proceedingJoinPoint.proceed();
    }

}
