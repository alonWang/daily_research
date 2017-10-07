class Solution {
    boolean guess(int m,int k,int[][]matrix){
        int n=matrix[0].length;
        //小于m的数字的总数
        int sum=0;
        for(int i=0;i<n;i++){
            int L=0,R=n-1;
            int ans=-1;
            while(L<=R){
                int mid=(L+R)/2;
                if(matrix[i][mid]<m){
                    ans=mid;
                    L=mid+1;
                }else{
                    R=mid-1;
                }
            }
            sum+=(ans+1);
        }
       
        return k>sum;
        
    }
    public int kthSmallest(int[][] matrix, int k) {
        int n=matrix[0].length;
        
        int L=matrix[0][0];
        int R=matrix[n-1][n-1];
        int ans=-1;
        while(L<=R){
            int mid=(int)(((long)L+R)/2);
            if(guess(mid,k,matrix)){
                ans=mid;
                L=mid+1;
            }else{ 
                R=mid-1;
            }
        }
        return ans;
    }
}