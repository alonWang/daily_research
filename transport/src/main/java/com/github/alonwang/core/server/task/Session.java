package com.github.alonwang.core.server.task;

import com.github.alonwang.core.core.DefaultJobExecutor;
import com.github.alonwang.core.protocol.Message;
import io.netty.channel.Channel;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 实体
 *
 * @author alonwang
 * @date 2020/7/16 17:11
 * @detail 它有两个作用
 * * 唯一标识一个用户
 * * 异步串行无锁化
 */
public class Session extends DefaultJobExecutor<Session> {
    private static final AtomicLong idGenerator = new AtomicLong();
    private final long sessionId;
    /**
     * 用户关联的Channel
     */
    private volatile Channel channel;

    public Session() {
        this.sessionId = idGenerator.incrementAndGet();
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(Message<?> message) {

        if (inThread()) {
            doSendMessage(message);
        } else {
            execute((user -> {
                doSendMessage(message);
            }));
        }
    }

    private void doSendMessage(Message<?> message) {
        message.encode();
        channel.writeAndFlush(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(channel, session.channel);
    }

    public long getSessionId() {
        return sessionId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                '}';
    }
}
