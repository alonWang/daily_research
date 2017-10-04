/*
时间复杂度O(m+n)
根据两个数组的总长度n,分为两种情况
n为偶数 中间值为(num[n/2-1]+num[n/2])/2
n为奇数 中间值为num[n/2];
注意时中间值是double型

思路 按照合并两个有序数组的思路,使用cur和prev代替辅助数组记录最后两个数.

*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;
        int n=n1+n2;
        int mid=n/2;
        
        int i=0,j=0,k=0,cur=0,prev=0;
        while(i<n1&&j<n2&&k++<=mid){
            prev=cur;
            
            if(nums1[i]<=nums2[j])
                cur=nums1[i++];
            else
                cur=nums2[j++];
        }
        //还没排完,其中一个数组已经迭代到尾部
        if(k-1!=mid){
            if(i==n1)
                while(k++<=mid){
                    prev=cur;
                    cur=nums2[j++];
                }
            else
                while(k++<=mid){
                    prev=cur;
                    cur=nums1[i++];
                }         
        }
        
        double ans=cur;
        if(n%2==0)
            ans=(ans+prev)/2;
           
        return ans;
        
    }
}