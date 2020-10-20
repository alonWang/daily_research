package com.github.alonwang.core.protocol;

import com.google.protobuf.ByteString;

/**
 * 响应
 *
 * @author alonwang
 * @date 2020/7/13 17:23
 * @detail
 */
public abstract class AbstractResponse extends AbstractMessage<ByteString> {
    @Override
    public ResponseHeader header() {
        return (ResponseHeader) super.header();
    }

    @Override
    public ByteString body() {
        return super.body();
    }
}
