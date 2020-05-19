package com.pp.ppgraduate.utils.Aspect;

import com.alibaba.fastjson.JSON;
import com.pp.ppgraduate.utils.EmptyUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Another on 2017/7/26.
 */
@Aspect
@Component
public class AspectLog {

    private static Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Pointcut("execution(* com.pp.ppgraduate.controller..*.*(..))")
    public void log() {
    }

    /**
     * 进入Controller获取参数
     *
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        logger.info("==============================start==============================");

        //url
        logger.info("url=" + request.getRequestURL());

        //methon
        logger.info("method=" + request.getMethod());

        //ip
        logger.info("ip=" + request.getRemoteHost());

        //类方法
        logger.info("class_method=" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        Object[] objs = joinPoint.getArgs();
        List args = new ArrayList();
        for (Object obj : objs) {
            if ((obj instanceof HttpServletRequest) || (obj instanceof HttpServletResponse) || obj instanceof BindingResult) {
                continue;
            }
            args.add(obj);
        }
        if (!EmptyUtil.isEmpty(args)) {
            logger.info("args=" + JSON.toJSONString(args));
        }
    }

    @After("log()")
    public void doAfter() {

    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void adAfterReturning(Object object) {
       /* ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletContainer.getRequest();
        HttpServletResponse response = servletContainer.getResponse();
        logger.info(JSON.toJSONString(response));*/
        logger.info("resopnse=" + JSON.toJSONString(object));
        logger.info("==============================end==============================");
    }


}
