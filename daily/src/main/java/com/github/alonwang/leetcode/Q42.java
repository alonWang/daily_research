package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/5 4:29 下午
 */
public class Q42 {
    class Solution {
        public int trap(int[] height) {
            if(height==null||height.length<=2){
                return 0;
            }
            int ans=0;
            int[] leftMax=new int[height.length];
            leftMax[0]=height[0];
            for (int i = 1;i<height.length;i++){
                leftMax[i]=Math.max(leftMax[i-1],height[i]);
            }
            int[] rightMax=new int[height.length];
            rightMax[height.length-1]=height[height.length-1];
            for(int j=height.length-2;j>=0;j--){
                rightMax[j]=Math.max(rightMax[j+1],height[j]);
            }
            for(int i=1;i<height.length-1;i++){
                ans+=Math.min(rightMax[i],leftMax[i])-height[i];
            }

            return ans;
        }
    }
}
