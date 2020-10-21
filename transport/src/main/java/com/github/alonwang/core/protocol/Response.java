package com.github.alonwang.core.protocol;

import com.google.protobuf.ByteString;

/**
 * 响应,标识发送的{@link Message}
 *
 * @author alonwang
 * @date 2020/7/13 17:23
 * @detail
 */
public abstract class Response extends Message<ByteString> {
    @Override
    public ResponseHeader header() {
        return (ResponseHeader) super.header();
    }

}
