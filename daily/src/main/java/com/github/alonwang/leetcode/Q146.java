package com.github.alonwang.leetcode;


import java.util.HashMap;
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
    static class LRUCacheS  {
        private final int capacity;
        private Map<Integer,LRUNode> cache;
        private LRUNode head;
        private LRUNode tail;


        public LRUCacheS(int capacity) {
            this.capacity=capacity;
            cache=new HashMap<>();
            head=new LRUNode();
            tail=new LRUNode();
            head.next=tail;
            tail.prev=head;
        }

        public int get(int key) {
            LRUNode node=cache.get(key);
            return node==null?-1:node.value;
        }

        public void put(int key, int value) {
            LRUNode oldNode=cache.get(key);
            if (oldNode!=null){
                removeNode(oldNode);
            }
            LRUNode newNode=new LRUNode();
            newNode.value=value;

            insertNode(newNode);
        }

        private void insertNode(LRUNode node) {
        head.next.prev=node;
        node.next=head.next;
        head.next=node;
        node.prev=head;
        }

        private void removeNode(LRUNode node) {
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }

        private class LRUNode {
            int value;
            LRUNode prev;
            LRUNode next;
        }
    }
    public static void main(String[] args) {
        LRUCacheS cache=new LRUCacheS(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        System.out.println(cache.get(2));


    }
}
