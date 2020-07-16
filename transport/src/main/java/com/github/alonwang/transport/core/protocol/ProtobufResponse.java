package com.github.alonwang.transport.core.protocol;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;

/**
 * @author alonwang
 * @date 2020/7/13 21:24
 * @detail
 */
public class ProtobufResponse implements Response{

    @Override
    public ResponseHeader header() {
        //TODO
        return null;
    }

    @Override
    public ByteString body() {
        //TODO
        return null;
    }


}
