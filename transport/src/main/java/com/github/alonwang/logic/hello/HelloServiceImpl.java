package com.github.alonwang.logic.hello;

import com.github.alonwang.core.Context;
import com.github.alonwang.core.server.task.User;
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
    public void hello(User user, HelloRequest request) {
        System.out.println(request.getBody().getMsg());
        HelloResponse response = Context.getMessageFactory().createResponse(1, 2);
        MeToMessage me = MeToMessage.newBuilder().setMsg(request.getBody().getMsg()).build();
        response.setMessage(me);
        user.sendMessage(response);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
