package com.github.alonwang.transport.core;

import com.github.alonwang.transport.TransportStarter;
import com.github.alonwang.transport.protocol.AbstractCSMessage;
import com.github.alonwang.transport.protocol.MessageHandler;
import com.github.alonwang.transport.protocol.MessageWrapper;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理@MessageWrapper,@MessageHandler的映射
 * TODO 更清楚的解析
 *
 * @author alonwang
 * @date 2020/7/22 18:28
 * @detail
 */
@Slf4j
@Component
public class MessageRegistry implements InitializingBean {
    private Map<String, Class<? extends AbstractCSMessage>> messages;
    private Map<Class<? extends AbstractCSMessage>, MethodWrapper> messageMethods;

    @Override
    public void afterPropertiesSet() throws Exception {
        Reflections reflections = new Reflections(TransportStarter.class.getPackage().getName());
        //记录所有的Message
        Map<String, Class<? extends AbstractCSMessage>> tempMessageMap = new HashMap<>();
        Set<Class<?>> wrappersClasses = reflections.getTypesAnnotatedWith(MessageWrapper.class);
        for (Class<?> wrapperClazz : wrappersClasses) {
            Preconditions.checkArgument(!Modifier.isAbstract(wrapperClazz.getModifiers()), "{} illegal,@MessageWrapper annotated class can't be abstract", wrapperClazz.getSimpleName());
            Preconditions.checkArgument(AbstractCSMessage.class.isAssignableFrom(wrapperClazz), "{} illegal,@MessageWrapper annotated class must be sub type of AbstractCSMessage", wrapperClazz.getSimpleName());
            MessageWrapper wrapperAnnotation = wrapperClazz.getAnnotation(MessageWrapper.class);
            Preconditions.checkArgument(wrapperAnnotation.moduleId() > 0 && wrapperAnnotation.commandId() > 0);
            String key = getKey(wrapperAnnotation.moduleId(), wrapperAnnotation.commandId());
            if (tempMessageMap.containsKey(key)) {
                Class<? extends AbstractCSMessage> old = tempMessageMap.get(key);
                throw new IllegalArgumentException(old.getSimpleName() + " and " + wrapperClazz.getSimpleName() + " conflict,please check @MessageWrapper's moduleId and commandId");
            }
            tempMessageMap.put(key, (Class<? extends AbstractCSMessage>) wrapperClazz);
        }
        messages = Collections.unmodifiableMap(tempMessageMap);

        //解析所有message对应的方法
        Map<Class<? extends AbstractCSMessage>, MethodWrapper> tempMethodWrappers = new HashMap<>();
        Set<Class<?>> handlerClasses = reflections.getTypesAnnotatedWith(MessageHandler.class, true);
        for (Class<?> handlerClazz : handlerClasses) {
            Preconditions.checkArgument(Modifier.isInterface(handlerClazz.getModifiers()), "{} illegal,@MessageHandler annotated class must be interface");
            Object bean = Context.applicationContext().getBean(handlerClazz);
            Preconditions.checkNotNull(bean, "{} annotated with @MessageHandler but no instance");
            var methods = handlerClazz.getDeclaredMethods();
            for (Method method : methods) {
                var parameterTypes = method.getParameterTypes();
                List<Class<?>> satisfyParameters = Arrays.stream(parameterTypes).filter(AbstractCSMessage.class::isAssignableFrom).filter(type -> messages.containsValue(type)).collect(Collectors.toList());
                if (satisfyParameters.isEmpty()) {
                    continue;
                }
                Preconditions.checkArgument(satisfyParameters.size() == 1, "method {} signature illegal,parameters should only contain exactly one AbstractCSMessage", method);
                Class<? extends AbstractCSMessage> messageClazz = (Class<? extends AbstractCSMessage>) satisfyParameters.get(0);
                Preconditions.checkArgument(!tempMethodWrappers.containsKey(messageClazz), "parameter illegal,{} appear in different methods", messageClazz);
                MethodWrapper methodWrapper = new MethodWrapper(method, bean);
                tempMethodWrappers.put(messageClazz, methodWrapper);
            }
        }
        messageMethods = Collections.unmodifiableMap(tempMethodWrappers);


    }

    private String getKey(int moduleId, int commandId) {
        return moduleId + "_" + commandId;
    }

    public Class<? extends AbstractCSMessage> getMessage(int moduleId, int commandId) {
        return messages.get(getKey(moduleId, commandId));
    }

    public MethodWrapper getWrapper(Class<? extends AbstractCSMessage> clazz) {
        return messageMethods.get(clazz);
    }


}
