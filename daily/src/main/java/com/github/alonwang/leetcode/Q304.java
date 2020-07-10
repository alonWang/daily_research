package com.github.alonwang.leetcode;

/**
 * 将给定区域看做一个矩形
 * 计算总和矩阵时,将其拆分为A:左侧总和,B:上侧总和,C:[i][j]这一个元素的值.
 * 同时A,B有重合区域D,sumMatrix[i][j]=A+B+C-D;
 * 计算给定区域时
 * sumRegion=sum[i+1][j+1]-A-B+D
 *
 * 注意总和矩阵和原矩阵的对应关系
 */
class Q304 {
    //[i][j]:从[0,0]到[i-1,j-1]的总和
    int [][]sumMatrix=null;

    //计算总和矩阵
    public Q304(int[][] matrix) {
        int n=matrix.length;
        if(n==0)
            return;
        int m=matrix[0].length;
        sumMatrix=new int[n+1][m+1];

        int colsum=0,rowsum=0,mixed=0;
        for(int i = 0; i<n; i++){
            for(int j=0; j<m; j++){
                colsum=sumMatrix[i][j+1];
                rowsum=sumMatrix[i+1][j];
                mixed=sumMatrix[i][j];
                sumMatrix[i+1][j+1]=colsum+rowsum-mixed+matrix[i][j];
            }
        }

    }

    //计算给定区域总和
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(sumMatrix==null)
            return 0;

        int colsum=sumMatrix[row2+1][col1];
        int rowsum=sumMatrix[row1][col2+1];
        int mixed=sumMatrix[row1][col1];
        return sumMatrix[row2+1][col2+1]-colsum-rowsum+mixed;
    }
}

/**
 * Your Q304 object will be instantiated and called as such:
 * Q304 obj = new Q304(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */