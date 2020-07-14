package com.github.alonwang.transport.core.protocol;

import com.google.protobuf.MessageLite;

/**
 * @author alonwang
 * @date 2020/7/13 17:37
 * @detail
 */
public class ProtobufRequest implements Request<MessageLite> {

    @Override
    public RequestHeader header() {
        return null;
    }

    @Override
    public MessageLite body() {
        return null;
    }
}
