package com.velo.algorithm4.leetcode;

import java.util.Arrays;


public class Q329 {
    int[][] mat;
    int m,n;
    //缓存,distance[i][j]=deepSearch(i,j)
    int distance[][];

    /**
     * 确保满足下标约束和递增要求
     * @param x
     * @param y
     * @param i
     * @param j
     * @return
     */
    public boolean vertify(int x,int y,int i,int j){
        if(0<=x+i&&x+i<m&&0<=y+j&&y+j<n){
            if(mat[x+i][y+j]>mat[x][y])
                return true;
        }
        return false;
    }

    /**
     * 找出从当前点能到达的最大距离
     * @param x
     * @param y
     * @return
     */
    public int deepSearch(int x,int y){
        int maxLen=0;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(Math.abs(i+j)==1){
                    if(vertify(x,y,i,j)){
                        if(distance[x+i][y+j]==-1)
                                distance[x+i][y+j]=deepSearch(x+i,y+j);
                        maxLen=Math.max(distance[x+i][y+j]+1,maxLen);
                    }
                }
            }
        }
        return maxLen;
    }

    /**
     * 以每一个点为起点开始搜索,找出最大距离
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        mat=matrix;
        m=mat.length;
        if(m==0)
            return 0;
        n=mat[0].length;
        distance=new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(distance[i],-1);
        }
        int maxLen=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                maxLen=Math.max(maxLen,deepSearch(i,j)+1);
            }
        }
        return maxLen;
    }
    public static void main(String[] args){
        new Q329().longestIncreasingPath(new int[][]{{1,1,1},{1,1,1},{2,3,1}});
    }
}
