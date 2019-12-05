package com.github.alonwang.clu.group;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-28 09:40
 **/
public class GroupManager {
    private final static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static ChannelGroup channelGroup() {
        return CHANNEL_GROUP;
    }
}
