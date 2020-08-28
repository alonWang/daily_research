package com.github.alonwang.transport.protocol;

/**
 * 消息抽象
 *
 * @author alonwang
 * @date 2020/7/13 17:15
 * @detail 消息包含消息头和消息体两部分。
 * 消息头容纳了 moduleId，commandId等元信息
 * 消息体是具体的数据，需要根据moduleId和commandId解析
 */
public abstract class AbstractMessage<T> {
    private MessageHeader header;
    private T body;

    public MessageHeader header() {
        return header;
    }

    public T body() {
        return body;
    }

    public abstract void decode() throws Exception;

    public abstract void encode();

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public void setBody(T body) {
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
