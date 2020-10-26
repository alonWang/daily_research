package com.github.alonwang.core.core;

import com.github.alonwang.core.protocol.Message;
import com.github.alonwang.core.protocol.MessageController;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.server.task.RequestMethodWrapper;
import com.github.alonwang.logic.LogicPackageMarker;
import com.google.common.base.Preconditions;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * void hello(Session,HelloRequest)
 *
 * @author alonwang
 * @date 2020/10/20 5:29 下午
 * @detail
 */
@Component
public class MessageMethodRegistry {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MessageRegistry messageRegistry;
    private Map<Class<? extends Message<?>>, RequestMethodWrapper> messageMethods;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        //解析所有message对应的方法
        Reflections reflections = new Reflections(LogicPackageMarker.class.getPackage().getName());
        Map<Class<? extends Message<?>>, RequestMethodWrapper> tempMethodWrappers = new HashMap<>();
        Set<Class<?>> handlerClasses = reflections.getTypesAnnotatedWith(MessageController.class, true);
        for (Class<?> handlerClazz : handlerClasses) {
            Preconditions.checkArgument(Modifier.isInterface(handlerClazz.getModifiers()), "{} illegal," +
                    "@MessageHandler annotated class must be interface");
            Object bean = applicationContext.getBean(handlerClazz);
            Preconditions.checkNotNull(bean, "{} annotated with @MessageHandler but no instance");
            var methods = handlerClazz.getDeclaredMethods();
            for (Method method : methods) {
                var parameterTypes = method.getParameterTypes();
                List<Class<?>> satisfyParameters =
                        Arrays.stream(parameterTypes).filter(Request.class::isAssignableFrom).filter(type -> messageRegistry.getMessages().containsValue(type)).collect(Collectors.toList());
                if (satisfyParameters.isEmpty()) {
                    continue;
                }
                Preconditions.checkArgument(satisfyParameters.size() == 1, "method {} signature illegal,parameters " +
                        "should only contain exactly one AbstractCSMessage", method);
                Class<? extends Message<?>> messageClazz =
                        (Class<? extends Message<?>>) satisfyParameters.get(0);
                Preconditions.checkArgument(!tempMethodWrappers.containsKey(messageClazz), "parameter illegal,{} " +
                        "appear in different methods", messageClazz);
                RequestMethodWrapper methodWrapper = new RequestMethodWrapper(method, bean);
                tempMethodWrappers.put(messageClazz, methodWrapper);
            }
        }
        messageMethods = Collections.unmodifiableMap(tempMethodWrappers);
    }

    public RequestMethodWrapper getWrapper(Class<? extends Message<?>> clazz) {
        return messageMethods.get(clazz);
    }

    public RequestMethodWrapper getWrapper(int moduleId, int commandId) {
        return getWrapper(messageRegistry.getMessage(moduleId, commandId));
    }


}
