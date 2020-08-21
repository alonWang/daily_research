package com.github.alonwang.handler;

import com.github.alonwang.register.HandlerRegister;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author alonwang
 * @date 2020/8/20 8:42 下午
 * @detail
 */
@Component
public class HandlerRegisterManager implements ApplicationContextAware, ApplicationRunner, Ordered {
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, HandlerRegister> registerMap = applicationContext.getBeansOfType(HandlerRegister.class);
        for (HandlerRegister register : registerMap.values()) {
            register.getClass().getGenericSuperclass();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
