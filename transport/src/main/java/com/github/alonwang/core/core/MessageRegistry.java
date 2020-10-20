package com.github.alonwang.core.core;

import com.github.alonwang.core.protocol.AbstractMessage;
import com.github.alonwang.core.protocol.MessageWrapper;
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
    private Map<String, Class<? extends AbstractMessage>> messages;



    @PostConstruct
    public void init() throws Exception {
        Reflections reflections = new Reflections(LogicPackageMarker.class.getPackage().getName());
        //记录所有的Message
        Map<String, Class<? extends AbstractMessage>> tempMessageMap = new HashMap<>();
        Set<Class<?>> wrappersClasses = reflections.getTypesAnnotatedWith(MessageWrapper.class);
        for (Class<?> wrapperClazz : wrappersClasses) {
            Preconditions.checkArgument(!Modifier.isAbstract(wrapperClazz.getModifiers()), "{} illegal," +
                    "@MessageWrapper annotated class can't be abstract", wrapperClazz.getSimpleName());
            Preconditions.checkArgument(AbstractMessage.class.isAssignableFrom(wrapperClazz), "{} illegal," +
                            "@MessageWrapper annotated class must be sub type of AbstractCSMessage",
                    wrapperClazz.getSimpleName());
            MessageWrapper wrapperAnnotation = wrapperClazz.getAnnotation(MessageWrapper.class);
            Preconditions.checkArgument(wrapperAnnotation.moduleId() > 0 && wrapperAnnotation.commandId() > 0);
            String key = getKey(wrapperAnnotation.moduleId(), wrapperAnnotation.commandId());
            if (tempMessageMap.containsKey(key)) {
                Class<? extends AbstractMessage> old = tempMessageMap.get(key);
                throw new IllegalArgumentException(old.getSimpleName() + " and " + wrapperClazz.getSimpleName() + " " +
                        "conflict,please check @MessageWrapper's moduleId and commandId");
            }
            tempMessageMap.put(key, (Class<? extends AbstractMessage>) wrapperClazz);
        }
        messages = Collections.unmodifiableMap(tempMessageMap);


    }

    private String getKey(int moduleId, int commandId) {
        return moduleId + "_" + commandId;
    }

    public Class<? extends AbstractMessage> getMessage(int moduleId, int commandId) {
        return messages.get(getKey(moduleId, commandId));
    }

    public Map<String, Class<? extends AbstractMessage>> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Class<? extends AbstractMessage>> messages) {
        this.messages = messages;
    }
}
