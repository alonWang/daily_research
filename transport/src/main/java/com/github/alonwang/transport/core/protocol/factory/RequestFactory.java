package com.github.alonwang.transport.core.protocol.factory;

import com.github.alonwang.transport.core.protocol.Request;

/**
 * @author alonwang
 * @date 2020/7/13 17:26
 * @detail
 */
public interface RequestFactory {
    /**
     * 创建初始化的
     * @param commandId
     * @return
     */
    public Request create(int commandId);

    public Request create(int commandId, long reqTimestamp);

}
