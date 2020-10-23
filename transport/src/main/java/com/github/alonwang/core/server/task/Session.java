package com.github.alonwang.core.server.task;

import com.github.alonwang.core.core.DefaultJobExecutor;
import com.github.alonwang.core.protocol.Message;
import groovy.transform.ToString;
import io.netty.channel.Channel;

import java.util.Objects;

/**
 * 实体
 *
 * @author alonwang
 * @date 2020/7/16 17:11
 * @detail 它有两个作用
 * * 唯一标识一个用户
 * * 异步串行无锁化
 */
@ToString
public class Session extends DefaultJobExecutor<Session> {
    /**
     * 用户关联的Channel
     */
    private volatile Channel channel;

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(Message<?> message) {
        message.encode();
        if (inThread()) {
            channel.writeAndFlush(message);
        } else {
            execute((user -> {
                channel.writeAndFlush(message);
            }));
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(channel, session.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }


}
