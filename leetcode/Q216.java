package com.velo.algorithm4.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q216 {
        static boolean []v=new boolean[10];
        static List<List<Integer>> ans=new ArrayList<>();
        public   List<List<Integer>> combinationSum3(int k, int n) {
            ans.clear();
            deepSearch(0,k,n);
            return ans;
        }

    /**
     *
     * @param idx 下一位取值范围[idx+1,min(n,9)]
     * @param k   剩余位数
     * @param n   剩余值
     */
        public static void deepSearch(int idx,int k,int n){
            if(0==k){
                if(0==n){
                    List<Integer> ls=new ArrayList<>();
                    for(int i=1;i<v.length;i++){
                        if(v[i])
                            ls.add(i);
                    }
                    ans.add(ls);
                }
                return;
            }
            for(int i=idx+1;i<=Math.min(n,9);i++){
                    n -= i;
                    v[i] = true;
                    deepSearch(i, k - 1, n);
                    n+=i;
                    v[i] = false;
            }

        }
}

