package com.velo.algorithm4.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q46 {
    List<List<Integer>> ans = new ArrayList<>();
    int[] record = new int[100];
    boolean[] flag = new boolean[100];

    public void deepSearch(int[] nums, int idx) {
        if (idx == nums.length) {
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                ls.add(record[i]);
            }
            ans.add(ls);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (false == flag[i]) {
                record[idx] = nums[i];
                flag[i] = true;
                deepSearch(nums, idx + 1);
                flag[i] = false;

            }

        }

    }

    public List<List<Integer>> permute(int[] nums) {
        deepSearch(nums, 0);
        return ans;
    }

}
