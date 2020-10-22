package com.github.alonwang.core.protocol;

/**
 * 消息抽象,包含消息头和消息体两部分。
 * 消息头容纳了标识,错误等元信息,消息体是具体的数据
 *
 * @param <T> 消息体的数据类型 例如ByteString,byte[]
 */
public abstract class Message<T> {
    /**
     * 消息头
     */
    private MessageHeader header;
    /**
     * 消息数据
     */
    private T body;

    public MessageHeader header() {
        return header;
    }

    public T body() {
        return body;
    }

    public abstract void decode();

    public abstract void encode();

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
