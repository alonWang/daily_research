package com.github.alonwang.lang;

import javax.annotation.Nonnull;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * {@link java.lang.reflect.Type}演示
 *
 * @author alonwang
 * @date 2020/5/5 19:34
 * @detail
 */
@Nonnull
public class GenericTest implements Supplier<Integer> {

    public static void main(String[] args) throws NoSuchMethodException {

        System.out.println("method:###########################################################");
        Method method = GenericTest.class.getMethod("testType", List.class, List.class, List.class, List.class, List.class, List.class, List.class, List.class, Map.class);
        System.out.println("TypeVariable: -------------------------------------------");
        TypeVariable<Method>[] typeVariables = method.getTypeParameters();
        for (TypeVariable<Method> typeVariable : typeVariables) {
            System.out.println(typeVariable);
        }
        System.out.println("GenericParameterTypes:-----------------------------------");
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            System.out.println(type.getClass() + ": " + type + " inner: " + (ParameterizedType.class.isInstance(type) ? (((ParameterizedType) type).getActualTypeArguments()[0].getClass()) + " " + ((ParameterizedType) type).getActualTypeArguments()[0] : "null"));
        }
        System.out.println("GenericParameterTypes actualTypeArguments:-------------------");
        for (Type type : types) {

            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types2 = parameterizedType.getActualTypeArguments();
            for (Type type1 : types2) {
                System.out.println(String.format(" %s  has Type:[clazz: %s type: %s ]", type, type1.getClass(), type1.getTypeName()));
            }
        }
        System.out.println("ParameterTypes: ----------------------------------------");
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> paraterType : parameterTypes) {
            System.out.println(paraterType);
        }
        System.out.println("GenericReturnType: --------------------------------------");
        System.out.println(method.getGenericReturnType().getClass() + " " + method.getGenericReturnType());
        System.out.println("ReturnType:----------------------------------------------");
        System.out.println(method.getReturnType().getClass() + " " + method.getReturnType());
        System.out.println("AnnotatedParameterTypes:-------------------------------------------");
        AnnotatedType[] annotatedTypes = method.getAnnotatedParameterTypes();
        for (AnnotatedType annotatedType : annotatedTypes) {
            System.out.println(annotatedType.getClass() + " : " + annotatedType);
        }


    }

    @Nonnull
    public <T, R> R testType(List<Integer> a, List<List<Integer>> b, List<ArrayList<Integer>> c, List<T> d, List<? extends Number> e, List<? super Integer> f, List<List<Integer>[]> g, List<ArrayList<Integer>[]> h, Map<Integer, String> i) {
        return null;
    }

    public Integer get() {
        return null;
    }
}
