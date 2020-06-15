package com.github.alonwang.other.fastjson;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author alonwang
 * @date 2020/6/4 11:39
 * @detail
 */
public class FastJsonTester {
    static class Pojo {
        List<Integer> a;
        int b;
        boolean c;
        boolean isD;

        public boolean isAB() {
            return ThreadLocalRandom.current().nextBoolean();
        }

        public List<Integer> getA() {
            return a;
        }

        public void setA(List<Integer> a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public boolean isC() {
            return c;
        }

        public void setC(boolean c) {
            this.c = c;
        }


    }

    public static void main(String[] args) {
        Pojo p = new Pojo();
        p.setA(Lists.newArrayList(1, 2, 3));
        p.setB(3);
        String str = JSON.toJSONString(p);
        Pojo p2 = JSON.parseObject(str, Pojo.class);

        System.out.println(p2);
    }
}
