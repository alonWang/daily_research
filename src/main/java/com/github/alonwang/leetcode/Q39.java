package com.github.alonwang.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Q39 {
    //单个排列
    int[] path = new int[100];
    //path的有效长度
    int len = 0;
    //结果
    List<List<Integer>> ans = new ArrayList<>();


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        deepSearch(0, candidates, target);
        return ans;
    }

    public void deepSearch(int idx, int[] c, int t) {
        //终止条件
        if (t < 0) {
            return;
        }
        if (t == 0) {
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                ls.add(path[i]);
            }
            ans.add(ls);
            return;
        }

        //回溯主体
        if (idx < c.length) {
            //不使用candidates[idx]
            deepSearch(idx + 1, c, t);

            //使用,这意味着可以重复使用同一个数字
            path[len] = c[idx];
            len++;
            deepSearch(idx, c, t - c[idx]);
            len--;
        }
    }


}
