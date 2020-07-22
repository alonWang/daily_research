package com.github.alonwang.transport.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/22 16:51
 * @detail
 */
public class ResponseHeader extends AbstractCSMessageHeader {
    private final int errorCode;

    public ResponseHeader(int moduleId, int commandId, int errorCode) {
        super(moduleId, commandId);
        this.errorCode = errorCode;
    }

    public int errorCode() {
        return errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
