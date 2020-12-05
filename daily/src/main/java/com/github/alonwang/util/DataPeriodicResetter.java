package com.github.alonwang.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.function.Supplier;

/**
 * 数据周期性重置器
 * * 定期重置数据 一小时,一天,一周
 * * 延迟重置 等到真正获取/修改数据时才充值数据
 *
 * @author alonwang
 * @date 2020/12/5 12:09 下午
 */
public abstract class DataPeriodicResetter<V> {
    /**
     * 荷载数据
     */
    private V data;
    /**
     * 最后一次修改时间戳,初始为创建时间
     */
    private long lastModifyTime;

    public DataPeriodicResetter() {
        this.data = initialSupplier().get();
        this.lastModifyTime = System.currentTimeMillis();
    }

    public DataPeriodicResetter(V data) {
        this(data, System.currentTimeMillis());

    }

    public DataPeriodicResetter(V data, long lastModifyTime) {
        this.data = data;
        this.lastModifyTime = lastModifyTime;
    }



    /**
     * 获取数据
     * 可能进行重置
     *
     * @return
     */
    public V get() {
        if (shouldReset(lastModifyTime)) {
            data = initialSupplier().get();
        }
        return data;
    }

    /**
     * 设置数据
     * 可能进行重置
     *
     * @param data
     */
    public void set(V data) {
        if (shouldReset(lastModifyTime)) {
            this.data = data;
            lastModifyTime = System.currentTimeMillis();
        }
    }

    /**
     * 仅更新
     * 在get()后修改了data的内部数据时需使用此方法,
     */
    public void update(){
        this.lastModifyTime=System.currentTimeMillis();
    }

    /**
     * 初始数据提供者
     *
     * @return
     */
    protected abstract Supplier<V> initialSupplier();

    /**
     * 是否应该重置数据
     *
     * @param lastModifyTime
     * @return true 应该重置 false 不应该重置
     */
    protected abstract boolean shouldReset(long lastModifyTime);

    /**
     * 创建一个resetter
     *
     * @param clazz reseter的实例类
     * @param <V>
     * @param <T>
     * @return
     */
    public static <V, T extends DataPeriodicResetter<V>> T create(Class<T> clazz) {
        if (!DataPeriodicResetter.class.isAssignableFrom(clazz) || Modifier.isAbstract(clazz.getModifiers())) {
            throw new IllegalArgumentException(clazz.getSimpleName() + " must be actual subclass of " + DataPeriodicResetter.class.getSimpleName());
        }
        try {
            Constructor<T> noArgConstructor = clazz.getConstructor( );
            return noArgConstructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(clazz.getSimpleName() + "'s default constructor doesn't match require", e);
        }
    }
}
