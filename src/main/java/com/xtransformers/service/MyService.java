package com.xtransformers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 * @date 2022-04-16
 */
@Service
public class MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);

    final public void foo() {
        LOGGER.info("foo()");
        bar();
    }

    public void bar() {
        LOGGER.info("bar()");
    }
}
