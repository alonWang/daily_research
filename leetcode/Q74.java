class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        if(m==0)
            return false;
        int n=matrix[0].length;
        if(n==0)
            return false;
        int ans=0;
        int L=0,R=m*n;
        while(L<R){
            int mid=(L+R)/2;
            if(matrix[mid/n][mid%n]<=target){
                ans=mid;
                L=mid+1;
            }else{
                R=mid;
            }
        }
        
        return matrix[ans/n][ans%n]==target?true:false;
    }

}