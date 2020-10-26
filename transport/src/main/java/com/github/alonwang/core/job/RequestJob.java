package com.github.alonwang.core.job;

import com.github.alonwang.core.Context;
import com.github.alonwang.core.exception.BusinessException;
import com.github.alonwang.core.job.JobExecutor.Job;
import com.github.alonwang.core.netty.Session;
import com.github.alonwang.core.protocol.message.Message;
import com.github.alonwang.core.protocol.message.Request;
import com.github.alonwang.core.protocol.message.RequestMethodWrapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * 网络请求任务,封装了请求数据和处理方法
 * <p>
 * 如果处理方法指定了响应(有返回值):
 * 正常执行结束后将响应发送给客户端
 * 异常执行结束后发送异常响应给客户端
 * <p>
 * 例如
 * <pre/>
 * {@code HelloResponse hello(Session session,HelloRequest request);}
 * </pre>
 * 正常执行结束后会将HelloResponse发送给客户端
 * 异常执行结束会生成一个带有异常错误码和错误消息但是没有荷载的HelloResponse发送给客户端
 * @author alonwang
 * @date 2020/7/28 11:11
 */
@Slf4j
public class RequestJob implements Job<Session> {
    /**
     * 网络请求对应的处理方法
     */
    private final RequestMethodWrapper wrapper;
    private final Request request;

    public RequestJob(RequestMethodWrapper wrapper, Request request) {
        this.wrapper = wrapper;
        this.request = request;
    }

    /**
     * @param session
     */
    @Override
    public void run(Session session) {
        try {
            Object retVal = wrapper.invoke(session, request);
            if (retVal != null && wrapper.hasResponse()) {
                session.sendMessage((Message<?>) retVal);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            Throwable cause = e.getCause();
            if (cause instanceof BusinessException) {
                if (wrapper.hasResponse()) {
                    sendMessageOnException(session, (BusinessException) cause);
                }
                throw (BusinessException) cause;
            }
            log.error("request job invoke error", e);
        }


    }

    /**
     * 发送异常响应,异常响应的payload是无效的
     *
     * @param session
     * @param bzException
     */
    private void sendMessageOnException(Session session, BusinessException bzException) {
        Message<?> returnMessage = Context.getMessageFactory().createMessage(wrapper.returnType());
        returnMessage.getHeader().setErrCode((bzException.getErrCode()));
        String errMsg = Context.getExceptionMessageHelper().getExceptionMessage(bzException);
        returnMessage.getHeader().setErrMsg(errMsg);
        session.sendMessage(returnMessage);

    }

    @Override
    public String description() {
        return wrapper.getMethod().toString();
    }
}
