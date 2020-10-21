package com.github.alonwang.logic.core;

/**
 * 消息id汇总
 * <p>
 * 添加模块时遵照以下原则: 模块id作为静态变量,对应的命令id放在一个新建一个内部接口中,参照{@link Hello}
 *
 * @author alonwang
 * @date 2020/10/20 6:54 下午
 * @detail
 */
public interface MessageIds {
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
