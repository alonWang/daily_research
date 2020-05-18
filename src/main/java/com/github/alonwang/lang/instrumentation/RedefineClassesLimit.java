package com.github.alonwang.lang.instrumentation;

import com.github.alonwang.lang.StringBufferAndBuilderBenchmark;

/**
 * @author alonwang
 * @date 2020/5/18 19:41
 * @detail
 */
public class RedefineClassesLimit {
    private static final String field3 = "123";

    static {
        RedefineClassesLimit l1 = new RedefineClassesLimit();
        System.out.println(l1.field1 + " " + l1.field2);
    }

    public int field1;
    public Integer field2;

    public void test() {
        int[] fb = new int[51];
        fb[1] = 1;
        fb[2] = 1;

        for (int i = 3; i <= 50; i++) {
            if (i == 45) {
                continue;
            }
            fb[i] = fb[i - 1] + fb[i - 2];
        }
        System.out.println("XXXXXXXXXXXX");
        new StringBufferAndBuilderBenchmark().benchmarkAll();
    }


}
