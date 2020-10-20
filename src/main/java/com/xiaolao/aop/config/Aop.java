package com.xiaolao.aop.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: WuJianGuo
 * @Date: 2020/9/18 16:21
 * @Description:
 */
@Aspect
@Component
public class Aop {

    private static  final Logger log = LoggerFactory.getLogger("Aop");

    private static ThreadLocal threadLocal = new ThreadLocal();

    @Pointcut("execution(* com.xiaolao.aop.controller..*.*(..))")
    public void pointCut(){

    }

    /**
     * @Author WuJianGuo.
     * @Description  前置通知
     * @Date 16:44 2020/9/18
     * @Param []
     * @return void
     **/
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable{
        threadLocal.set(System.currentTimeMillis());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        log.info("请求方式:"+request.getMethod());
        log.info("请求url:"+request.getRequestURL().toString());
        log.info("请求参数和值"+JSON.toJSONString(request.getParameterMap()));
        if(request.getMethod().toUpperCase().equals("POST")){



        }


        log.info("前置通知");
    }

    /**
     * @Author WuJianGuo.
     * @Description  遇到异常继续执行
     * @Date 16:59 2020/9/18
     * @Param []
     * @return void
     **/
    @After("pointCut()")
    public void after() {
        Long startTime = Long.valueOf(threadLocal.get().toString());
        log.info("耗时:"+(System.currentTimeMillis()-startTime)+"ms");
        log.info("最终通知");
    }

    /**
     * @Author WuJianGuo.
     * @Description  遇到异常终止运行
     * @Date 16:59 2020/9/18
     * @Param []
     * @return void
     **/
    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("后置通知");
    }

    @AfterThrowing(throwing = "throwable",pointcut = "pointCut()")
    public void afterThrowing(Throwable throwable) throws Throwable {
        if(throwable!=null){
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            for(StackTraceElement stackTraceElement:stackTrace){
                stringBuilder.append(stackTraceElement);
            }
        }
        log.info("异常通知");
    }


    /**
     * @Author WuJianGuo.
     * @Description  遇到异常不往下执行 环绕通知=前置通知+目标方法执行+后置通知，可以用该方法对某个方法做拦截器的作用
     * @Date 17:47 2020/9/18
     * @Param [proceedingJoinPoint]
     * @return java.lang.Object
     **/
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        log.info("around统计耗时:"+(System.currentTimeMillis()-startTime));
        return obj;
    }



}
