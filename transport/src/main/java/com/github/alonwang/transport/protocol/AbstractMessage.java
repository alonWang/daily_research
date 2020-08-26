package com.github.alonwang.transport.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 17:15
 * @detail
 */
public abstract class AbstractMessage {
    private MessageHeader header;
    private Object body;

    public MessageHeader header() {
        return header;
    }

    public Object body() {
        return body;
    }

    public abstract void decode() throws Exception;

    public abstract void encode();

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AbstractMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
