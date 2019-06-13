package com.github.alonwang.other;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Java Concurrency in Practice
 * 通过改写interrupt方法将非标准的取消操作封装在线程中
 */
public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
        super.interrupt();
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1000];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    //...
                }

            }
        } catch (IOException e) {
            //允许线程退出
        }
    }
}
