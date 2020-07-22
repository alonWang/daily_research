package com.github.alonwang.transport.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 21:14
 * @detail
 */
public abstract class AbstractCSMessageHeader {
    private final int moduleId;
    private final int commandId;

    public AbstractCSMessageHeader(int moduleId, int commandId) {
        this.moduleId = moduleId;
        this.commandId = commandId;
    }

    public int moduleId() {
        return moduleId;
    }

    public int commandId() {
        return commandId;
    }
}
