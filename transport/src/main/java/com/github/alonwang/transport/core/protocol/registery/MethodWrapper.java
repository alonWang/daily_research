package com.github.alonwang.transport.core.protocol.registery;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author alonwang
 * @date 2020/7/23 14:49
 * @detail
 */
@AllArgsConstructor
@Data
public class MethodWrapper {
    private final Method method;
    private final Object target;

    public Object execute(Object... args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }

}
