package com.github.alonwang.lang.generic;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取获取复杂泛型类型的 {@link Type},可用于fastjson反序列化,提升性能
 *
 * 参见 {@link TypeHelper#int2intMap}
 * @author alonwang
 * @date 2020/12/1 12:01
 */
public class TypeHelper<T> {
    private final Type type;

    public TypeHelper() {
        Type superClazz = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClazz).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }

    public static final Type int2IntMap = new TypeHelper<Map<Integer, Integer>>(){}.getType();

    public static void main(String[] args) {
        //制造数据
        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,1);
        String str=JSON.toJSONString(map);


        //反序列化
        map=JSON.parseObject(str,int2IntMap);
    }
}
