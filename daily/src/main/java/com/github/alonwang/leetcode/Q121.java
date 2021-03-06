package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/6 4:41 下午
 */
public class Q121 {
    /**
     * 缓存右侧极大值,跟接雨水逻辑相似
     */
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices.length<=1){
                return 0;
            }
            int f1=prices[prices.length-1];
            int ans=0;
            for(int i=prices.length-2;i>=0;i--){
                ans=Math.max(ans,f1-prices[i]);
                f1=Math.max(prices[i],f1);
            }
            return ans;

        }
    }
}
