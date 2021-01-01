package com.github.alonwang.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
public class HandlerRegisterManager {
@Autowired
private ApplicationContext applicationContext;
    @PostConstruct
    public void init() throws Exception {
        applicationContext.getBeansOfType(HandlerRegister.class).values().forEach(register -> {
            Type type = register.getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<Handler<?>> handlerClazz = (Class<Handler<?>>) parameterizedType.getActualTypeArguments()[1];
            applicationContext.getBeansOfType(handlerClazz).values().forEach(handler -> {
                register.register(handler.id(), handler);
            });
        });
    }

}
