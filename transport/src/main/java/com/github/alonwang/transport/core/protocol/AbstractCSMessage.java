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
    private MessageHeader header;
    private T data;

    public AbstractCSMessage(int moduleId, int commandId) {
        this.moduleId = moduleId;
        this.commandId = commandId;
    }

    public int moduleId() {
        return moduleId;
    }

    public int commandId() {
        return commandId;
    }

    public MessageHeader header() {
        return header;
    }

}
