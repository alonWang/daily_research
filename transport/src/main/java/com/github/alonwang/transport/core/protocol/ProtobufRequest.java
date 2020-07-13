package com.github.alonwang.transport.core.protocol;

import com.github.alonwang.transport.protobuf.Base;

/**
 * @author alonwang
 * @date 2020/7/13 17:37
 * @detail
 */
public class ProtobufRequest implements Request<Base.Request> {
    private Base.Request request;

    @Override
    public int commandId() {
        return request.getHeader().getCommandId();
    }

    @Override
    public long reqTimestamp() {
        return request.getHeader().getCommandId();
    }

}
