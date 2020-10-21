package com.github.alonwang.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/22 16:51
 * @detail
 */
public class ResponseHeader extends MessageHeader {
    private  int errorCode;


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
