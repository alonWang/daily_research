package com.github.alonwang.core.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 异常消息映射管理
 *
 * @author alonwang
 * @date 2020/10/26 11:40 上午
 */
@Component
public class ExceptionMessageHelper {
    @Autowired
    private MessageSource messageSource;

    /**
     * 获取异常对应的错误信息
     * @param exception
     * @return
     */
    public String getExceptionMessage(BusinessException exception) {
        return messageSource.getMessage(String.valueOf(exception.getErrCode()), exception.getArgs(), Locale.CHINA);
    }
}
