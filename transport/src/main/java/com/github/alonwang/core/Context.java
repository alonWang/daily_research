package com.github.alonwang.core;

import com.github.alonwang.core.core.MessageMethodRegistry;
import com.github.alonwang.core.core.SessionManager;
import com.github.alonwang.core.protocol.factory.MessageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * @author alonwang
 * @date 2020/7/22 17:45
 * @detail
 */
@Component
public class Context implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static MessageMethodRegistry methodRegistry;
    private static MessageFactory messageFactory;
    private static SessionManager sessionManager;
    private static MessageSource messageSource;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }



    public static MessageMethodRegistry getMethodRegistry() {
        return methodRegistry;
    }

    @Autowired
    public void setMethodRegistry(MessageMethodRegistry methodRegistry) {
        Context.methodRegistry = methodRegistry;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Context.applicationContext = applicationContext;
    }

    public static MessageFactory getMessageFactory() {
        return messageFactory;
    }

    @Autowired
    public void setMessageFactory(MessageFactory messageFactory) {
        Context.messageFactory = messageFactory;
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }

    @Autowired
    public void setSessionManager(SessionManager sessionManager) {
        Context.sessionManager = sessionManager;
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        Context.messageSource = messageSource;
    }
}
