package com.github.alonwang.logic.hello;

import com.github.alonwang.core.Context;
import com.github.alonwang.core.exception.BusinessException;
import com.github.alonwang.core.exception.GlobalErrorCode;
import com.github.alonwang.core.server.task.Session;
import com.github.alonwang.logic.core.MessageIds;
import com.github.alonwang.logic.hello.message.HelloRequest;
import com.github.alonwang.logic.hello.message.HelloResponse;
import org.springframework.stereotype.Service;

import static com.github.alonwang.logic.protobuf.Hello.MeToMessage;

/**
 * @author alonwang
 * @date 2020/7/24 11:47
 * @detail
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public HelloResponse hello(Session session, HelloRequest request) {
        System.out.println("收到客户端信息: " + request.getReq().getMsg());
        HelloResponse response = Context.getMessageFactory().createMessage(MessageIds.HelloModule,
                MessageIds.Hello.meTo);
        MeToMessage me = MeToMessage.newBuilder().setMsg(request.getReq().getMsg()).build();
        response.setMessage(me);
        throw new BusinessException(GlobalErrorCode.PARAMETER_ERROR,"buddy, your are wrong");
      //  return response;

    }
}
