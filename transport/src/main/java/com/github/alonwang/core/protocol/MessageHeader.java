package com.github.alonwang.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 21:14
 * @detail
 */
public class MessageHeader {
    private int moduleId;
    private int commandId;

    public MessageHeader() {
    }

    public MessageHeader(int moduleId, int commandId) {
        this.moduleId = moduleId;
        this.commandId = commandId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public int getCommandId() {
        return commandId;
    }

    @Override
    public String toString() {
        return "MessageHeader{" +
                "moduleId=" + moduleId +
                ", commandId=" + commandId +
                '}';
    }
}
