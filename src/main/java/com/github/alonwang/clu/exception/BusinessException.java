package com.github.alonwang.clu.exception;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 16:13
 **/
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
