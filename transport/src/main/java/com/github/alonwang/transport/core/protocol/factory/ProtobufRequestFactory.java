package com.github.alonwang.transport.core.protocol.factory;

import com.github.alonwang.transport.core.protocol.Message;
import com.github.alonwang.transport.protobuf.Base;

/**
 * @author alonwang
 * @date 2020/7/14 8:11 上午
 * @detail
 */
public class ProtobufRequestFactory implements RequestFactory<Base.Request> {
    @Override
    public Message<Base.Request> create(int messageId) {
        return null;
    }
}
