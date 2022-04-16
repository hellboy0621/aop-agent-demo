package com.xtransformers;

import com.xtransformers.service.MyService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 1. 版本选择了 java 8，因为目前的 aspectj-maven-plugin 1.14.0 最高支持到 java 16
 * 2. 运行时需要在 VM options 里加入 -javaagent:${MAVEN_REPO}/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar
 * -javaagent:/Users/daniel/java/maven/repository/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar
 *
 * 在类加载阶段增强 MyService，所以对于 MyService#bar() 方法也被增强了。
 * 如果是通过代理实现的，因为在 MyService#foo() 方法中通过 this.bar(); 调用 bar() 方法，不是使用代理对象调用，不会被增强。
 */
@SpringBootApplication
public class AopAgentDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopAgentDemoApplication.class);

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(AopAgentDemoApplication.class, args);
        MyService myService = context.getBean(MyService.class);

        LOGGER.info("MyService: {}", myService.getClass());
        myService.foo();

        // 休眠5分钟，方便使用 Arthas 反编译增强类
        TimeUnit.MINUTES.sleep(5);

        context.close();
    }
}
