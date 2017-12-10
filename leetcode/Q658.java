/**
 * 1....M....N
 * 结果肯定是从i....j 的连续序列
 * 由此将问题转化为寻找开始下标.
 * 最大下标为 N-k
 * 因此下标范围为0-N-k
 * 如果x<=arr[mid],那么下标范围为begin~mid-1
 * 如果x>arr[mid]
 * 分为两种情况
 * 1. arr[mid]-x>arr[mid+k]-x,此时右边离x更近,下标右移一位
 * 2. arr[mid]-x<=arr[mid+k]-x,此时用到约束条件:优先选取更小者,下标范围为begin~mid-1;
 */

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res=new ArrayList<>();
        int begin=0,end=arr.length-k;
        while(begin<end){
            int mid=begin+(end-begin)/2;
            if(x>arr[mid]){
                if(x-arr[mid]>arr[mid+k]-x){
                    begin=mid+1;
                }else{
                    end=mid;
                }
            }else{
                end=mid;
            }
        }
        int idx=begin;
        while(k--!=0){
            res.add(arr[idx++]);
        }
        return res;
    }
}