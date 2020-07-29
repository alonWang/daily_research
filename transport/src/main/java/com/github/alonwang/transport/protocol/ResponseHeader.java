package com.github.alonwang.transport.protocol;

/**
 * @author alonwang
 * @date 2020/7/22 16:51
 * @detail
 */
public class ResponseHeader extends MessageHeader {
    private  int errorCode;

    public ResponseHeader(int moduleId, int commandId) {
        super(moduleId, commandId);
    }

    public int errorCode() {
        return errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
