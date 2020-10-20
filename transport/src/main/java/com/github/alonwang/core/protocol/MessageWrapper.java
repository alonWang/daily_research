package com.github.alonwang.core.protocol;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author alonwang
 * @date 2020/7/16 17:41
 * @detail
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MessageWrapper {
    int moduleId();

    int commandId();
}
