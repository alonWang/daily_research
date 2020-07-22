package com.github.alonwang.transport.core.protocol;

import java.lang.annotation.*;

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
