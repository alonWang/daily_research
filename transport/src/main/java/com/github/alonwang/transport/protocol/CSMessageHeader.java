package com.github.alonwang.transport.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 21:14
 * @detail
 */
public  class CSMessageHeader {
    private final int moduleId;
    private final int commandId;

    public CSMessageHeader(int moduleId, int commandId) {
        this.moduleId = moduleId;
        this.commandId = commandId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public int getCommandId() {
        return commandId;
    }
}
