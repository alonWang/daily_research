package com.github.alonwang.lang.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 泛型方法Type演示
 *
 * @author alonwang
 * @date 2020/5/5 19:34
 * @detail
 */
public class GenericMethodDemonstration {

    public static void main(String[] args) throws NoSuchMethodException {

        System.out.println("方法签名\n  " +
                "public <T extends Number, R> R testType(List<Integer> a, List<List<Integer>> b, List<ArrayList<Integer>> c, List<T> d, List<? extends Number> e, List<? super Integer> f, List<List<Integer>[]> g, List<ArrayList<Integer>[]> h, Map<Integer, String> i ,T j ,List<?> k))");
        Method method = Arrays.stream(GenericMethodDemonstration.class.getMethods()).filter(m -> m.getName().equals("testType")).findAny().orElse(null);
        System.out.println("类型变量: -------------------------------------------");
        TypeVariable<Method>[] typeVariables = method.getTypeParameters();
        for (TypeVariable<Method> typeVariable : typeVariables) {
            System.out.println(typeVariable + " 实现类:" + typeVariable.getClass().getSimpleName());
        }
        System.out.println("泛型参数(按照参数顺序):-----------------------------------");
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            System.out.print(type + "\t 实现类:" + type.getClass().getSimpleName());
            if (type instanceof ParameterizedType) {
                System.out.print("\n   内部 ");
                Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
                for (Type actualType : actualTypes) {
                    System.out.print(" actualType: " + actualType + " 实现类: " + actualType.getClass().getSimpleName());
                }

            }
            System.out.println();
        }

    }

    public <T extends Number, R> R testType(List<Integer> a, List<List<Integer>> b, List<ArrayList<Integer>> c, List<T> d, List<? extends Number> e, List<? super Integer> f, List<List<Integer>[]> g, List<ArrayList<Integer>[]> h, Map<Integer, String> i, T j, List<?> k) {
        return null;
    }
}
