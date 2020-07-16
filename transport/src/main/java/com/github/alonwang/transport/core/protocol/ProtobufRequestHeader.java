package com.github.alonwang.transport.core.protocol;

import com.github.alonwang.transport.protobuf.Base;

/**
 * @author alonwang
 * @date 2020/7/16 16:08
 * @detail
 */
public class ProtobufRequestHeader implements RequestHeader {
    private Base.RequestHeader delegate;

    public ProtobufRequestHeader(Base.RequestHeader delegate) {
        this.delegate = delegate;
    }

    @Override
    public int moduleId() {
        return delegate.getModuleId();
    }

    @Override
    public int messageId() {
        return delegate.getMessageId();
    }

    @Override
    public long createTime() {
        return delegate.getCreateTime();
    }
}
