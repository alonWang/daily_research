package com.github.alonwang.core.protocol;

/**
 * 消息抽象,包含消息头和有效载荷两部分。
 * 消息头容纳了标识,错误等元信息,有效载荷是具体的数据
 *
 * @param <T> 有效载荷的数据类型 例如ByteString,byte[]
 */
public abstract class Message<T> {
    /**
     * 消息头
     */
    private MessageHeader header;
    /**
     * 有效载荷
     */
    private T payload;

    public MessageHeader getHeader() {
        return header;
    }

    public T getPayload() {
        return payload;
    }

    public abstract void decode();

    public abstract void encode();

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                ", payload=" + payload +
                '}';
    }
}
