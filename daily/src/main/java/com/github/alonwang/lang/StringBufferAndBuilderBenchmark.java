package com.github.alonwang.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * String,StringBuilder,StringBuffer的性能对比
 * 由于String是不可变对象，对String的修改都会生成一个新的对象，如果频繁修改，性能不高。
 * StringBuilder和StringBuffer是可变对象，它们的修改操作性能都很高，唯一的区别是： StringBuffer是线程安全的，而StringBuilder是非线程安全的
 */
public class StringBufferAndBuilderBenchmark {
    abstract class AbstractBenchmark {
        public void benchmark(List<String> tests) {
            long start = System.currentTimeMillis();
            join(tests);
            long elapse = System.currentTimeMillis() - start;
            System.out.println(name() + " spend time: " + elapse + " ms");

        }

        protected abstract String join(List<String> tests);

        protected abstract String name();
    }

    class StringBenchmark extends AbstractBenchmark {

        @Override
        protected String join(List<String> tests) {
            String str = new String("");
            for (String test : tests) {
                str += test;
            }
            return str;
        }

        @Override
        protected String name() {
            return "string";
        }
    }

    class StringBuilderBenchmark extends AbstractBenchmark {

        @Override
        protected String join(List<String> tests) {
            StringBuilder sb = new StringBuilder();
            for (String test : tests) {
                sb.append(test);
            }
            return sb.toString();
        }

        @Override
        protected String name() {
            return "stringbuilder";
        }
    }

    class StringBufferBenchmark extends AbstractBenchmark {

        @Override
        protected String join(List<String> tests) {
            StringBuffer sb = new StringBuffer();
            for (String test : tests) {
                sb.append(test);
            }
            return sb.toString();
        }

        @Override
        protected String name() {
            return "stringbuffer";
        }
    }

    public void benchmarkAll() {
        List<String> tests = new ArrayList<>();
        for (int i = -1; i < 10000; i++) {
            int len = ThreadLocalRandom.current().nextInt(1000) + 1;
            char[] chars = new char[len];
            IntStream.range(0, len).forEach(v -> chars[v] = (char) ThreadLocalRandom.current().nextInt(128));
            tests.add(String.valueOf(chars));
        }
        new StringBuilderBenchmark().benchmark(tests);
        new StringBufferBenchmark().benchmark(tests);
        new StringBenchmark().benchmark(tests);


    }


    public static void main(String[] args) {

        new StringBufferAndBuilderBenchmark().benchmarkAll();
    }
}
