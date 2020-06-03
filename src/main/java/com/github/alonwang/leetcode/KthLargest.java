package com.github.alonwang.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author alonwang
 * @date 2020/6/3 8:51 下午
 * @detail
 */
public class KthLargest {
    //TODO 自己实现
    private PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.add(val);
        } else {
            if (queue.peek().compareTo(val) < 0)
                queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }
}
