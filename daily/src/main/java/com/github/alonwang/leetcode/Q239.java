package com.github.alonwang.leetcode;

import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author alonwang
 * @date 2021/2/19 9:44 下午
 */
public class Q239 {
    class Solution {
        /**
         * 借助PriorityQueue维护窗口内的大小次序,并通过窗口优化队列移除.O(nlogn)
         *
         * @param nums
         * @param k
         * @return
         */
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

        /**
         * 事实: 只需要知道窗口中的最大值
         * 思路: 自己维护窗口中的最大值
         * 实现: 通过双端队列 队头 最大值下标 <- 队尾 最小值下标
         * 窗口右侧右移,新增元素,从队尾向队头,将所有小于新增值的元素移除掉,之后在队尾加上新增元素
         * 窗口左侧右移,移除元素 如果队头元素等于移除元素,移除队头.
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow2(int[] nums, int k) {
            if (k == 1) {
                return nums;
            }
            int[] result = new int[nums.length - k + 1];
            //最大<-最小 元素的下标
            Deque<Integer> queue = new LinkedList<>();
            int left = 0, right = 0;
            while (right < nums.length) {
                int added = nums[right];
                while (!queue.isEmpty() && nums[queue.peekLast()] < added) {
                    queue.pollLast();
                }
                queue.addLast(right);
                right++;
                if (right - left == k) {
                    result[left] = nums[queue.peekFirst()];
                    if (left == queue.peekFirst()) {
                        queue.pollFirst();
                    }
                    left++;
                }
            }
            return result;
        }
    }

}
