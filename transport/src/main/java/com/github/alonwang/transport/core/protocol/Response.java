package com.github.alonwang.transport.core.protocol;

/**
 * 响应
 *
 * @author alonwang
 * @date 2020/7/13 17:23
 * @detail
 */
public interface Response {
    /**
     * 命令id
     *
     * @return
     */
    int commandId();

    /**
     * 响应时间
     *
     * @return
     */
    long reqTimestamp();

    /**
     * 错误码
     * @return
     */
    int errorCode();
}
