package com.github.alonwang.transport.core.context;

import com.github.alonwang.transport.core.protocol.factory.MessageFactory;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author alonwang
 * @date 2020/7/22 17:45
 * @detail
 */
@Component
public class Context implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static MessageFactory messageFactory() {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Context.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
