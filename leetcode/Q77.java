package com.velo.algorithm4.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q77 {
    static List<List<Integer>> ans = new ArrayList();
    static boolean[] flag = new boolean[1000];
    static int[] path = new int[1000];

    public List<List<Integer>> combine(int n, int k) {
        ans.clear();
        deepSearch(0, n, k);
        return ans;
    }

    public static void deepSearch(int idx, int n, int k) {
        if (idx == k) {
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                ls.add(path[i]);
            }
            ans.add(ls);
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (false == flag[i]) {
                if (0 == idx || path[idx - 1] < i) {
                    path[idx] = i;
                    flag[i] = true;
                    deepSearch(idx + 1, n, k);
                    flag[i] = false;
                }

            }
        }
    }
}
