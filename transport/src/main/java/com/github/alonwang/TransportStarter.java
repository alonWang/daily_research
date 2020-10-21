package com.github.alonwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alonwang
 * @date 2020/7/23 17:52
 * @detail
 */
@SpringBootApplication
public class TransportStarter {
    public static void main(String[] args) throws Exception {
        new SpringApplication().run(TransportStarter.class, args);

    }

}
