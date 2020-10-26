package com.github.alonwang.core.protocol.annotation;

import com.github.alonwang.core.protocol.message.Message;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记字段为 {@link Message#getPayload()}有效载荷对应的数据结构
 *
 * @author alonwang
 * @date 2020/10/22 4:21 下午
 * @detail
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Payload {
}
