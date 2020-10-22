package com.github.alonwang.core.protocol;

import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link Message}的protobuf实现,提供自动编解码功能
 * <p>
 * 当子类有多个protobuf字段时,需要手动标记解析字段
 *
 * @author alonwang
 * @date 2020/10/22 10:21 上午
 * @detail
 */
@Slf4j
public abstract class ProtobufMessage extends Message<ByteString> {
    @Override
    public void decode() {
        ProtobufCodecDelegate.decode(this);
    }

    @Override
    public void encode() {
        ProtobufCodecDelegate.encode(this);
    }
}
