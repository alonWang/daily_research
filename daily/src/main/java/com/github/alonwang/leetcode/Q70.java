package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/5 6:39 下午
 */
public class Q70 {
    class Solution {
        /**
         * dp[i]=dp[i-1]+1+dp[i-2]+1
         * @param n
         * @return
         */
        public int climbStairs(int n) {
            if (n==1){
                return 1;
            }
            if (n==2){
                return 2;
            }
            int f1=1,f2=2;
            int f3=0;
            int i=3;
            while (i<=n){
                f3=f1+f2;
                if (i!=n){
                    f1=f2;
                    f2=f3;
                }
                i++;
            }
            return f3;

        }
    }
}
