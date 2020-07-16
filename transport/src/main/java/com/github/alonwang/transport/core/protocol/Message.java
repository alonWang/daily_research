package com.github.alonwang.transport.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 17:56
 * @detail
 */
public interface Message {

    MessageHeader header();

    Object body();

}
