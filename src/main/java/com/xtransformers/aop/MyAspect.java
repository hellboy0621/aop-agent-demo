package com.xtransformers.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 此切面并未被 Spring 管理
 *
 * @author daniel
 * @date 2022-04-16
 */
@Aspect
public class MyAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* com.xtransformers.service.MyService.*())")
    public void before() {
        LOGGER.info("before()");
    }
}
