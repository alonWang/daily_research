package com.github.alonwang.transport.core.protocol;

import com.github.alonwang.transport.protobuf.Base;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;

/**
 * @author alonwang
 * @date 2020/7/13 17:37
 * @detail
 */
public class ProtobufRequest implements Request {
    private final Base.Request delegateRequest;
    private final RequestHeader delegateHeader;

    public ProtobufRequest(Base.Request request) {
        delegateHeader = new ProtobufRequestHeader(request.getHeader());
        delegateRequest = request;
    }

    @Override
    public RequestHeader header() {
        return delegateHeader;
    }

    @Override
    public ByteString body() {
        return delegateRequest.getBody();
    }
}
