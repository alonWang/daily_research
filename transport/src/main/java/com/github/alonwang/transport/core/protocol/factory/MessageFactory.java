package com.github.alonwang.transport.core.protocol.factory;

import com.github.alonwang.transport.core.protocol.Message;

/**
 * @author alonwang
 * @date 2020/7/13 21:29
 * @detail
 */
public interface MessageFactory {
    Message create(int messageId);
}
