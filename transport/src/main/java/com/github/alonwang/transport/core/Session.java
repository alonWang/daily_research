package com.github.alonwang.transport.core;

import io.netty.channel.Channel;

import java.util.Objects;

/**
 * @author alonwang
 * @date 2020/7/16 17:11
 * @detail
 */
public class Session extends DefaultMessageTaskExecutor<Session> {
    private volatile Channel channel;

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
