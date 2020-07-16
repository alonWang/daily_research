package com.github.alonwang.transport.core.protocol;

/**
 * 响应
 *
 * @author alonwang
 * @date 2020/7/13 17:23
 * @detail
 */
public interface Response extends Message {
    ResponseHeader header();
}
