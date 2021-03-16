package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/16 5:09 下午
 */
public class Q53 {
    class Solution {
        /**
         * 只有前面累计的和对当前位置有用(>0)才把它算上
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            if (nums.length==1){
                return nums[0];
            }
            int max=nums[0];
            int sum=nums[0];
            for (int i=1;i<nums.length;i++){
                if (sum<=0){
                    sum=nums[i];
                }else{
                    sum+=nums[i];
                }
                max=Math.max(max,sum);
            }
            return max;


        }
    }
}
