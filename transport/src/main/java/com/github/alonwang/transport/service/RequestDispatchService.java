package com.github.alonwang.transport.service;

import com.github.alonwang.transport.core.Session;
import com.github.alonwang.transport.protocol.AbstractRequest;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * @author alonwang
 * @date 2020/7/24 15:15
 * @detail
 */
@Component
public class RequestDispatchService {
    public void dispatch(Session session, AbstractRequest request) {
        //分发到业务线程
    }
}
