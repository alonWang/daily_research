package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/9 6:16 下午
 */
public class Q746 {
    class Solution {
        /**
         * 走到位置i所需最小体力
         * dp[i]=Math.min(dp[i-2],dp[i-1])+cost[i]
         *
         * 顶点格为cost.length+1 因此结果为dp[cost.length] 顶点格的消耗为0
         * @param cost
         * @return
         */
        public int minCostClimbingStairs(int[] cost) {
            int[] dp=new int[cost.length+1];
            dp[0]=cost[0];
            dp[1]=cost[1];
            for(int i=2;i<=cost.length;i++){
                dp[i]=Math.min(dp[i-1],dp[i-2])+ (i!=cost.length?cost[i]:0);
            }
            return dp[cost.length];
        }
    }
}
