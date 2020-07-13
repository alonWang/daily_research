package com.github.alonwang.transport.core.protocol;

/**
 * @author alonwang
 * @date 2020/7/13 17:56
 * @detail
 */
public interface Message {
    int TYPE_REQUEST = 0;
    int TYPE_RESPONSE = 1;

    /**
     * 信息id
     *
     * @return
     */
    int messageId();

    /**
     * 创建时间
     *
     * @return
     */
    long createTime();

}
