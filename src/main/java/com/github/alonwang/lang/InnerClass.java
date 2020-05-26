package com.github.alonwang.lang;

import java.util.function.Supplier;

/**
 * 匿名内部类的.class文件生成规则
 * * 按照在源码中的相对顺序生成 InnerClass${number}.class
 * * lambda方式不会生成内部类
 *
 * @author alonwang
 * @date 2020/5/15 20:45
 * @detail
 */
public class InnerClass {
    Supplier<Integer> f1 = () -> 1;
    Supplier<Integer> f2 = new Supplier<Integer>() {
        @Override
        public Integer get() {
            return 2;
        }
    };

    public static void main(String[] args) {
        Supplier<Integer> f3 = () -> 3;
        Supplier<Integer> f4 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 4;
            }
        };
    }

    private void test() {
        Supplier<Integer> f5 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 5;
            }
        };
    }
}
