package com.github.alonwang.other;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

/**
 * 简单的本地缓存实现. 利用FutureTask和put-if-absent避免重复计算
 *
 * @param <T>
 * @param <R>
 */
public class Memoizer<T, R> {
    private final ConcurrentHashMap<T, Future<R>> cache = new ConcurrentHashMap<>();

    public R get(final T key, final Function<T, R> computable) throws InterruptedException, ExecutionException {
        while (true) {
            Future<R> future = cache.get(key);
            if (future == null) {
                FutureTask<R> ft = new FutureTask<>(() -> computable.apply(key));
                future = cache.putIfAbsent(key, ft);
                if (future == null) {
                    future = ft;
                    ft.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(key, future);
            }
        }
    }
}
