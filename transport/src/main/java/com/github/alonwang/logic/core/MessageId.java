package com.github.alonwang.logic.core;

/**
 * Message标识
 *
 * @author alonwang
 * @date 2020/10/20 6:54 下午
 * @detail
 */
public interface MessageId {
    interface Hello {
        int moduleId = 1;
        /**
         * hello请求
         */
        int hello = 1;
        /**
         * meto响应
         */
        int meTo = 2;
    }
}
