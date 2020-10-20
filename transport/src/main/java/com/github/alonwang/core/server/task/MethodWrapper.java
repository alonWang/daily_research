package com.github.alonwang.core.server.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 封装方法的反射调用逻辑
 *
 * @author alonwang
 * @date 2020/7/23 14:49
 * @detail
 */
@AllArgsConstructor
@Data
public class MethodWrapper {
    private final Method method;
    private final Object target;

    public Object invoke(Object... args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }

}
