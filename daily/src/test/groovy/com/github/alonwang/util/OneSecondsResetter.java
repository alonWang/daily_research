package com.github.alonwang.util;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 时间间隔超过10秒钟就重置
 * @author alonwang
 * @date 2020/12/5 1:18 下午
 */


public class OneSecondsResetter extends DataPeriodicResetter<OneSecondsResetter.Pojo> {
    public static class Pojo {
        public int a;
    }
    public OneSecondsResetter() {
    }

    @Override
    protected Supplier<Pojo> initialSupplier() {
        return Pojo::new;
    }

    @Override
    protected boolean shouldReset(long lastModifyTime) {
        return Math.abs(System.currentTimeMillis() - lastModifyTime) > TimeUnit.SECONDS.toMillis(1);
    }
}
