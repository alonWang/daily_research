package com.github.alonwang.leetcode;

//O(nLogn)
class Solution {
    public int search(int[] nums, int target) {
        int n=nums.length;
        if(n<1)
            return -1;
        //分割点
        int pivot=findPivot(nums);

        int ans=guess(0,pivot,target,nums);
        if(ans==-1)
            ans=guess(pivot,n,target,nums);
        return ans;
    }

    //二分搜索
    public int guess(int L, int R, int target, int[]nums){
        int ans=-1;
        while(L<R){
            int mid=(L+R)/2;
            if(nums[mid]<target){
                L=mid+1;
            }else if(nums[mid]>target){
                R=mid;
            }else{
                ans=mid;
                break;
            }
        }
        return ans;
    }

    public int findPivot(int[] nums){
        int n=nums.length;

        int L=0,R=n;
        int target=nums[0];
        int ans=0;
        while(L<R){
            int mid=(L+R)/2;
            if(nums[mid]<target){
                ans=mid;
                R=mid;
            }else{
                L=mid+1;
            }
        }
        return ans;
    }
}