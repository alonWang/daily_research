package com.github.alonwang.core.protocol;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 消息id,标识具体{@link Message}的唯一标识
 *
 * @author alonwang
 * @date 2020/7/16 17:41
 * @detail
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MessageId {
    /**
     * 模块id
     *
     * @return
     */
    int moduleId();

    /**
     * 命令id
     *
     * @return
     */
    int commandId();
}
