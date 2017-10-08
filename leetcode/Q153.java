class Solution {
    public int findMin(int[] nums) {
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
        //如果是单调递增,需要判断
        return nums[ans]<target?nums[ans]:target;
    }
}