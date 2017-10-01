package com.velo.algorithm4.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q77 {
    /**
     * path 记录单个排列
     */
    static List<List<Integer>> ans = new ArrayList();
    static int[] path;

    public List<List<Integer>> combine(int n, int k) {
        ans.clear();
        path=new int[k];
        deepSearch(0, n, k);
        return ans;
    }


    /**
     *path[k-1]的取值范围[idx+1,n]
     * @param idx [0,n]
     * @param n 最大值
     * @param k 剩余个数
     */
    public static void deepSearch(int idx, int n, int k) {
        if (0==k) {
            List<Integer> ls=new ArrayList<>();
            for(int i=path.length-1;i>=0;i--){
                ls.add(path[i]);
            }
            ans.add(ls);
            return;
        }
        for (int i = idx+1; i <= n; i++) {
                    //k是递减的,只能用它做下标
                    path[k-1] = i;
                    deepSearch(i, n, k-1);
        }
    }
}
