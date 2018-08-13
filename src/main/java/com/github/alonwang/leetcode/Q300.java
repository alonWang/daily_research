package com.github.alonwang.leetcode;

//递归版本 n^2
class Solution {
    int[] p=new int[10000];
    int[] cache=new int[10000];

    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        for(int i = 0; i<nums.length; i++){
            p[i]=nums[i];
        }
        p[n]=100000;
        return robot(n)-1;
    }

    public int robot(int idx){
        if(-1==idx)
            return 0;
        if(0!=cache[idx])
            return cache[idx];
        int ans=0;
        for(int i = 0; i<idx; i++){
            if(p[idx]>p[i]){
                ans=Math.max(ans,robot(i));
            }
        }
        cache[idx]=ans+1;
        return ans+1;

    }
}

//非递归版本
class Solution {
    int[] p=new int[10000];
    int[] cache=new int[10000];

    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        if(0==n){
            return 0;
        }
        for(int i=0; i<nums.length; i++){
            p[i]=nums[i];
        }
        p[n]=100000;
        cache[0]=0;
        for(int idx=0; idx<=n; idx++){
            int ans=0;
            for(int i=0; i<idx; i++){
                if(p[idx]>p[i]){
                    ans=Math.max(ans,cache[i]);
                }
            }
            cache[idx]=ans+1;
        }
        return cache[n]-1;

    }

}
