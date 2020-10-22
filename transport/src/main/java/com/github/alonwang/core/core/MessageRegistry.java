package com.github.alonwang.core.core;

import com.github.alonwang.core.protocol.Message;
import com.github.alonwang.core.protocol.MessageId;
import com.github.alonwang.logic.LogicPackageMarker;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 管理@MessageWrapper,@MessageHandler的映射
 *
 * @author alonwang
 * @date 2020/7/22 18:28
 * @detail
 */
@Slf4j
@Component
public class MessageRegistry {
    private Map<String, Class<? extends Message<?>>> messages;


    /**
     * 扫描,检查并记录所有带有协议{@link MessageId}标识的{@link Message}
     */
    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() throws Exception {
        Reflections reflections = new Reflections(LogicPackageMarker.class.getPackage().getName());
        Map<String, Class<? extends Message<?>>> tempMessageMap = new HashMap<>();
        Set<Class<?>> wrappersClasses = reflections.getTypesAnnotatedWith(MessageId.class);
        for (Class<?> wrapperClazz : wrappersClasses) {
            Preconditions.checkArgument(!Modifier.isAbstract(wrapperClazz.getModifiers()), "{} illegal," +
                    "@MessageWrapper annotated class can't be abstract", wrapperClazz.getSimpleName());
            Preconditions.checkArgument(Message.class.isAssignableFrom(wrapperClazz), "{} illegal," +
                            "@MessageWrapper annotated class must be sub type of AbstractCSMessage",
                    wrapperClazz.getSimpleName());
            MessageId wrapperAnnotation = wrapperClazz.getAnnotation(MessageId.class);
            Preconditions.checkArgument(wrapperAnnotation.moduleId() > 0 && wrapperAnnotation.commandId() > 0);
            String key = getKey(wrapperAnnotation.moduleId(), wrapperAnnotation.commandId());
            if (tempMessageMap.containsKey(key)) {
                Class<? extends Message<?>> old = tempMessageMap.get(key);
                throw new IllegalArgumentException(old.getSimpleName() + " and " + wrapperClazz.getSimpleName() + " " +
                        "conflict,please check @MessageId's moduleId and commandId");
            }
            tempMessageMap.put(key, (Class<? extends Message<?>>) wrapperClazz);
        }
        messages = Collections.unmodifiableMap(tempMessageMap);


    }

    private String getKey(int moduleId, int commandId) {
        return moduleId + "_" + commandId;
    }

    public Class<? extends Message<?>> getMessage(int moduleId, int commandId) {
        return messages.get(getKey(moduleId, commandId));
    }

    public Map<String, Class<? extends Message<?>>> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Class<? extends Message<?>>> messages) {
        this.messages = messages;
    }
}
