package com.github.alonwang.transport.core.protocol;

import com.google.protobuf.MessageLite;

/**
 * @author alonwang
 * @date 2020/7/13 21:24
 * @detail
 */
public class ProtobufResponse<T> implements Response<MessageLite> {

    @Override
    public MessageHeader header() {
        //TODO
        return null;
    }

    @Override
    public MessageLite body() {
        //TODO
        return null;
    }


}
