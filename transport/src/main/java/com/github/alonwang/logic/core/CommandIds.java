package com.github.alonwang.logic.core;

/**
 * 命令标识汇总
 *
 * @author alonwang
 * @date 2020/10/20 6:54 下午
 * @detail
 */
public interface CommandIds {
    int HelloModule = 1;

    interface Hello {
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
