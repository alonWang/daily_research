class Solution {
    /**
     * 判断mid是否满足需要
     * 将m认为是m个相同箱子,mid箱子的最大容量,sum记录每个箱子的当前容量.
     * 依次放入nums中的元素,并进行如下判断
     * 
     * 1. 放入nums[i]时,如果它大于mid,说明没有箱子能放下
     * 2. 如果sum+nums[i]大于mid,说明当前箱子放不下,将当前箱子封存(m--),并将num[i]放到下一个箱子里.
     * 3. 否则将nums[i]放入当前箱子
     * 
     * 最后如果m>=1,即至少还有一个箱子说明能放下(因为循环结束时sum还有元素,最后一个箱子是留给它的),否则不能放下
     */
    boolean guess(long mid,int []nums,int m){
        long sum=0;
        for(int i=0;i<nums.length;i++){
             if(nums[i]>mid)
                    return false;
            if(sum+nums[i]>mid){
                m--;
                sum=nums[i];
            }else{
                sum+=nums[i];
            }
        }
        return m >= 1;
      
    }
    public int splitArray(int[] nums, int m) {
        int n=nums.length;
        long L=0,R=1;//[L,R]
        for(int i=0;i<n;i++)
            R+=nums[i];
        long ans=0;
        while(L<R){
            long mid=(L+R)/2;
            if(guess(mid,nums,m)){
                ans=mid;
                R=mid;
            }else{ 
                L=mid+1;
            }
        }
        
        return (int)ans;
    }
}