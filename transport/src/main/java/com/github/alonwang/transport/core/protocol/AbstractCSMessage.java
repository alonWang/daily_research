package com.github.alonwang.transport.core.protocol;

/**
 * 请求抽象
 *
 * @author alonwang
 * @date 2020/7/13 17:15
 * @detail
 */
public abstract class AbstractCSMessage<T> implements Message {
    private final int moduleId;
    private final int commandId;
    private final T message;

    public AbstractCSMessage(int moduleId, int commandId, T message) {
        this.moduleId = moduleId;
        this.commandId = commandId;
        this.message = message;
    }

    public int moduleId() {
        return moduleId;
    }

    public int commandId() {
        return commandId;
    }


}
