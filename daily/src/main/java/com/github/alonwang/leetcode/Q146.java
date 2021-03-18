package com.github.alonwang.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author alonwang
 * @date 2021/3/18 11:01 上午
 */
public class Q146 {
    static class LRUCache extends LinkedHashMap<Integer,Integer> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity,0.75f,true);
            this.capacity=capacity;
        }

        public int get(int key) {
            return getOrDefault(key,-1);
        }

        public void put(int key, int value) {
            super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size()>capacity;
        }
    }

    public static void main(String[] args) {
        LRUCache cache=new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        System.out.println(cache.get(2));


    }
}
