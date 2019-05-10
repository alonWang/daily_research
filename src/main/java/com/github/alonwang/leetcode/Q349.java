package com.github.alonwang.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Q349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums1) {
            set.add(n);
        }
        Set<Integer> result = new HashSet<>();
        for (int n : nums2) {
            if (set.contains(n)) {
                result.add(n);
            }
        }
        int[] nums = new int[result.size()];
        int i = 0;
        for (int n : result) {
            nums[i++] = n;
        }
        return nums;
    }
}
