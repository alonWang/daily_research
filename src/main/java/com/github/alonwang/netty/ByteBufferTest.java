package com.github.alonwang.netty;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * @author alonwang
 * @date 2020/7/1 17:03
 * @detail
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(100);
        System.out.println("创建一个容量为100的buffer. 此时只有写有意义." + indicator(buffer));
        buffer.put(1);
        buffer.put(2);
        System.out.println("write mode, write 1,2 to buffer. " + indicator(buffer));

        buffer.flip();
        System.out.println("enter read mode. " + indicator(buffer));
        buffer.get();
        buffer.get();
        System.out.println("read 2 element. " + indicator(buffer));

        buffer.rewind();
        System.out.println("enter read mode. " + indicator(buffer));
        buffer.get();
        buffer.get();
        System.out.println("read 2 element. " + indicator(buffer));

        buffer.rewind();
        System.out.println("enter write mode. " + indicator(buffer));
        buffer.put(1);
        buffer.put(2);
        System.out.println("write 1,2 to buffer. " + indicator(buffer));


        System.out.println("reset buffer default write mode is meaningful. " + indicator(buffer));

    }

    private static String indicator(Buffer buffer) {
        return String.format("{position: %s,limit:%s,capacity:%s}", buffer.position(), buffer.limit(), buffer.capacity());
    }
}
