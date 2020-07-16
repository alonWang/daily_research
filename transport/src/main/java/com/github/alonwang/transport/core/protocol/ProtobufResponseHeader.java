package com.github.alonwang.transport.core.protocol;

import com.github.alonwang.transport.protobuf.Base;

/**
 * @author alonwang
 * @date 2020/7/16 16:10
 * @detail
 */
public class ProtobufResponseHeader implements ResponseHeader{
    private Base.ResponseHeader delegate;
    @Override
    public int errorCode() {
        return delegate.getErrorCode();
    }

    @Override
    public int moduleId() {
        return delegate.getErrorCode();
    }

    @Override
    public int messageId() {
        return delegate.getMessageId();
    }

    @Override
    public long createTime() {
        return 0;
    }
}
