package com.github.alonwang.transport.core;

import com.github.alonwang.transport.server.service.RequestDispatchService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static RequestDispatchService requestDispatchService;
    private static MessageRegistry messageRegistry;

    public static ApplicationContext applicationContext() {
        return applicationContext;
    }

    public static RequestDispatchService getRequestDispatchService() {
        return requestDispatchService;
    }

    @Autowired
    public void setRequestDispatchService(RequestDispatchService requestDispatchService) {
        Context.requestDispatchService = requestDispatchService;
    }

    public static MessageRegistry getMessageRegistry() {
        return messageRegistry;
    }

    @Autowired
    public void setMessageRegistry(MessageRegistry messageRegistry) {
        Context.messageRegistry = messageRegistry;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Context.applicationContext = applicationContext;
    }
}
