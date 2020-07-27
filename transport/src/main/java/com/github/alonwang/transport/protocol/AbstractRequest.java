package com.github.alonwang.transport.protocol;

import com.google.protobuf.ByteString;

/**
 * @author alonwang
 * @date 2020/7/22 17:16
 * @detail
 */
public abstract class AbstractRequest extends AbstractMessage {
    @Override
    public RequestHeader header() {
        return (RequestHeader) super.header();
    }

    @Override
    public ByteString body() {
        return (ByteString) super.body();
    }

}
