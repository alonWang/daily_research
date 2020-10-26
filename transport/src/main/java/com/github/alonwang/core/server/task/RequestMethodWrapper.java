package com.github.alonwang.core.server.task;

import com.github.alonwang.core.protocol.Message;

import java.lang.reflect.Method;

/**
 * 专用于网络请求的方法封装
 * <p>
 * method方法签名形如
 * * HelloResponse hello(HelloRequest)
 * * void hello(HelloRequest)
 */
public class RequestMethodWrapper extends MethodWrapper {
    public RequestMethodWrapper(Method method, Object target) {
        super(method, target);
    }

    /**
     * 请求是否有对应的响应
     *
     * @return
     */
    public boolean hasResponse() {
        return Message.class.isAssignableFrom(returnType());
    }
}
