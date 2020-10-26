package com.github.alonwang.core.protocol.message;

import com.github.alonwang.core.exception.GlobalErrorCode;

/**
 * 消息头,承载了消息的标识和状态
 *
 * @author alonwang
 * @date 2020/7/13 21:14
 * @detail
 */
public class MessageHeader {
    private int moduleId;
    private int commandId;
    /**
     * 错误码  {@link GlobalErrorCode#SUCCESS} 表示正常,其他均表示错误状态.
     * 错误状态下 {@link payload}无效. {@link errMsg}展示具体的错误信息
     */
    private int errCode;
    /**
     * 错误信息 {@link errCode}对应的详细描述
     */
    private String errMsg;

    public MessageHeader() {
    }

    public MessageHeader(int moduleId, int commandId) {
        this.moduleId = moduleId;
        this.commandId = commandId;
    }

    public MessageHeader(int moduleId, int commandId, int errCode, String errMsg) {
        this.moduleId = moduleId;
        this.commandId = commandId;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * 消息是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return errCode == GlobalErrorCode.SUCCESS;
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
