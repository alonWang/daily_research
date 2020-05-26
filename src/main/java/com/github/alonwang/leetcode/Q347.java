package com.github.alonwang.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alonwang
 * @date 2020/5/26 21:12
 * @detail
 */
public class Q347 {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }
        //散列表记录
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.compute(num, (key, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }

        int[] result = new int[k + 1];
        int n = k + 1;
        int pos = 1;
        //构造维护一个小顶堆
        for (Integer key : counts.keySet()) {
            if (pos < n + 1) {
                result[pos++] = key;
            } else {

            }
        }
        return Arrays.copyOfRange(result, 1, n);
    }
}
