package com.github.alonwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alonwang
 * @date 2020/8/22 3:50 下午
 * @detail
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
