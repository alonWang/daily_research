package com.github.alonwang.core.protocol;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记字段为 {@link Message#getPayload()}有效载荷
 *
 * @author alonwang
 * @date 2020/10/22 4:21 下午
 * @detail
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface PayLoad {
}
