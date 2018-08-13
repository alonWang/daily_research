package com.github.alonwang.leetcode;

class Q35 {
    public int searchInsert(int[] nums, int target) {
        int n=nums.length;
        if(target>nums[n-1])
            return n;
        else if(target<=nums[0])
            return 0;
        int pos=guess(0,n,target,nums);
        return nums[pos]==target?pos:pos+1;


    }

    //存在时为实际位置,不存在时为最接近的左侧元素位置
    public int guess(int L, int R, int target, int[]nums){
        int ans=0;
        while(L<R){
            int mid=(L+R)/2;
            if(nums[mid]<=target){
                ans=mid;
                L=mid+1;
            }else{
                R=mid;
            }
        }
        return ans;
    }
}