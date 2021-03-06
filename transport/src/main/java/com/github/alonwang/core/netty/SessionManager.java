package com.github.alonwang.core.netty;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 维护链接和用户的映射
 *
 * @author alonwang
 * @date 2020/10/23 5:30 下午
 */
@Slf4j
@Component
public class SessionManager {
    private Map<Channel, Session> channel2SessionMap = new ConcurrentHashMap<>();

    /**
     * 获取channel对应的Session,如果没有就新建一个
     *
     * @param channel
     * @return null if channel不活跃
     */
    public Optional<Session> getOrCreateSession(Channel channel) {
        if (!channel.isActive()) {
            log.debug("Channel: {} not active, can't get it's Session", channel);
            return Optional.empty();
        }
        Session session = channel2SessionMap.compute(channel, (c, s) -> {
            if (null == s) {
                Session createSession = new Session();
                createSession.setChannel(c);
                return createSession;
            } else {
                return s;
            }

        });
        return Optional.of(session);
    }

    /**
     * 移除session
     *
     * @param channel
     * @return 移除的session 可能为null
     */
    public Session removeSession(Channel channel) {
        return channel2SessionMap.remove(channel);
    }
}
