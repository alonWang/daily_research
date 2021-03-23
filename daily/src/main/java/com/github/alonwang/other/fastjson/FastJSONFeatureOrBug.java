package com.github.alonwang.other.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1.2.58版本中
 * 如果类中有方法形如,并且未添加JSONField(serialized=false,deserialized=false)
 * Map<X,X> getXXX(){...}
 * 在序列化时 XXX会被存储下来.
 * 在反序列化时,由于找不到字段XXX,就会通过 getXXX()方法获取个Map,再将存储的数据XXX放置到这个Map中.
 *
 */
public class FastJSONFeatureOrBug {
    static class MapIssue{
        Map<Integer,Integer> a;

        //忘记添加@JSONField 注解
        public Map<Integer,Integer> getB(){
            return a;
        }
    }
    static class ConCurrentIssue{
        CopyOnWriteArrayList<Integer> a;

    }
    public static void main(String[] args) {
        MapIssue o1=new MapIssue();
        o1.a=new HashMap<>();
        o1.a.put(1,1);
        String str=JSON.toJSONString(o1);
        MapIssue o2=JSON.parseObject(str,MapIssue.class);
        //旧版本中,a的值会是"{1:1}"
        System.out.println(o2.a);
    }
}
