package com.github.alonwang.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 借助队列维护窗口内的大小次序,并通过窗口优化队列移除.
 *
 * @author alonwang
 * @date 2021/2/19 9:44 下午
 */
public class Q239 {
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (k == 1) {
                return nums;
            }
            int[] result = new int[nums.length - k + 1];
            //元素,出现次数
            Map<Integer, Integer> windows = new HashMap<>(k);
            //滑动过程中维护 大小
            PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.reverseOrder());
            int left = 0, right = 0;
            while (right < nums.length) {
                int added = nums[right];
                windows.compute(added, (key, v) -> {
                    if (v == null) {
                        v = 0;
                    }
                    return v + 1;
                });
                pq.add(added);
                right++;

                if (right - left == k) {
                    int removed = nums[left];

                    while (windows.getOrDefault(pq.peek(), 0) <= 0) {
                        pq.poll();
                    }
                    result[left] = pq.peek();
                    left++;
                    windows.compute(removed, (key, v) -> {
                        if (v == 1) {
                            return null;
                        }
                        return v - 1;
                    });


                }
            }
            return result;
        }
    }

}
