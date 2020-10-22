package com.github.alonwang.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 21:14
 * @detail
 */
public class MessageHeader {
    /**
     * 关联errCode,标记消息正常,无错误
     */
    public static final int ERR_CODE_SUCCESS = 0;
    private int moduleId;
    private int commandId;
    private int errCode;
    private String errMsg;

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


    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "MessageHeader{" +
                "moduleId=" + moduleId +
                ", commandId=" + commandId +
                '}';
    }
}
