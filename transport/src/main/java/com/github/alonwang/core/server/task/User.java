package com.github.alonwang.core.server.task;

import com.github.alonwang.core.core.DefaultTaskExecutor;
import com.github.alonwang.core.protocol.Message;
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
public class User extends DefaultTaskExecutor<User> {
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
        User user = (User) o;
        return Objects.equals(channel, user.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }

    public void sendMessage(Message message) {
        message.encode();
        if (inThread()) {
            channel.writeAndFlush(message);
        } else {
            execute((user -> {
                channel.writeAndFlush(message);
            }));
        }
    }
}
