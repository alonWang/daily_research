class Solution {
    public boolean search(int[] nums, int target) {
       int n=nums.length;
       if(n<1)
           return false;
       //分割点
       int i=0;
       while(i<n-1&&nums[i]<=nums[i+1])
           i++;
     
       int ans=guess(0,i+1,target,nums);
       if(ans==-1)
           ans=guess(i+1,n,target,nums);
       return ans==-1?false:true;
   }
   //二分搜索
  public int guess(int L,int R,int target,int[]nums){
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
}