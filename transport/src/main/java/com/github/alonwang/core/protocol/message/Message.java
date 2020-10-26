package com.github.alonwang.core.protocol.message;

import static com.github.alonwang.core.protocol.protobuf.Base.Protocol;

/**
 * 协议包抽象,包含消息头和有效载荷两部分。对应于{@link Protocol},参见base.proto#Protocol
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

    /**
     * 将有效荷载转换为消息中的特定数据结构.
     *
     */
    public abstract void decode();

    /**
     * 将消息中的特定数据结构转换为荷载
     */
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
