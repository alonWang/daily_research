package com.github.alonwang.transport.core.protocol;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author alonwang
 * @date 2020/7/13 17:15
 * @detail
 */
public abstract class AbstractCSMessage {
    private AbstractCSMessageHeader header;
    private Object body;

    public AbstractCSMessageHeader header() {
        return header;
    }

    public Object body() {
        return body;
    }
    public abstract void decode() throws InvalidProtocolBufferException;

    public abstract void encode();

    public void setHeader(AbstractCSMessageHeader header) {
        this.header = header;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
