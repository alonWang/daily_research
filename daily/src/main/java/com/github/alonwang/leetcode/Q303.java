package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/6 21:28
 */
public class Q303 {
    /**
     * 累加+面积截取
     */
    class NumArray {
        private int[] sums;

        public NumArray(int[] nums) {
            if (nums.length==0){
                return;
            }
            sums = new int[nums.length];
            sums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sums[i] += sums[i - 1] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sums[j] - (i == 0 ? 0 : sums[i - 1]);
        }
    }
}
