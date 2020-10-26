package com.github.alonwang.core.exception;

import javax.annotation.Nullable;

/**
 * 业务异常基类
 * <p>
 * 当业务执行异常(参数错误,不满足前置条件)时抛出
 *
 * <p>
 * //logic
 * //仅有错误码
 * throw new BusinessException(32)
 * //错误码+参数
 * throw new BusinessException(32,args1,args2);
 * // 仅有异常 system error
 * throw new BusinessException(Exception)
 *
 * @author alonwang
 * @date 2020/10/23 21:40
 */
public class BusinessException extends RuntimeException {
    /**
     * 错误码.标识错误的类型
     */
    private int errCode;
    @Nullable
    private Object[] args;

    public BusinessException(int errCode) {
        this.errCode = errCode;
    }

    public BusinessException(int errCode, Object... args) {
        this.errCode = errCode;
        this.args = args;
    }

    public int getErrCode() {
        return errCode;
    }

    @Nullable
    public Object[] getArgs() {
        return args;
    }
}
