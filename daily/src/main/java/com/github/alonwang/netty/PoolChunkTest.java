package com.github.alonwang.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author alonwang
 * @date 2021/1/3 10:52
 */
public class PoolChunkTest {
    public static void main(String[] args) {
        ByteBufAllocator allocator=ByteBufAllocator.DEFAULT;
        ByteBuf buf=allocator.buffer(8192);
        ByteBuf buf1=allocator.buffer(8192);

    }
}
