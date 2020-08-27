package com.github.alonwang.handler;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Handler注册管理器， 使用Spring自动完成注册。
 *
 * @author alonwang
 * @date 2020/8/20 8:42 下午
 * @detail
 */
@Component
public class HandlerRegisterManager implements ApplicationContextAware, ApplicationRunner, Ordered {
    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    @Override
    public void run(ApplicationArguments args) throws Exception {
        applicationContext.getBeansOfType(HandlerRegister.class).values().forEach(register -> {
            Type type = register.getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<Handler<?>> handlerClazz = (Class<Handler<?>>) parameterizedType.getActualTypeArguments()[1];
            applicationContext.getBeansOfType(handlerClazz).values().forEach(handler -> {
                register.register(handler.id(), handler);
            });
        });
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
