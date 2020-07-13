package com.github.alonwang.transport.core.protocol;

/**
 * 请求抽象
 *
 * @author alonwang
 * @date 2020/7/13 17:15
 * @detail
 */
public interface Request<T> {


    /**
     * 请求时间
     * @return
     */
    long reqTimestamp();


}
