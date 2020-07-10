package com.github.alonwang.lang.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * @author alonwang
 * @date 2020/5/21 9:48
 * @detail
 */
public abstract class AbstractSupplier<M extends CharSequence> implements Supplier<M> {
    Class<M> clazz;

    public AbstractSupplier() {
        // ParameterizedType StringSupplier<String>
        Type genericSuperClassType = getClass().getGenericSuperclass();
        // Class String
        Type actualTypeArgument = ((ParameterizedType) genericSuperClassType).getActualTypeArguments()[0];
        clazz = (Class) actualTypeArgument;
        System.out.println(clazz);
    }

    @Override
    public M get() {
        /*some logic*/
        return null;
    }
}
