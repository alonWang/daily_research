package com.github.alonwang.transport.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 21:17
 * @detail
 */
public interface ResponseHeader extends MessageHeader {
    int errorCode();
}
