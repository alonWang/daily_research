package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/2/22 23:27
 */
public class Q174 {
   static class Solution {
        int[][] map;
        int min;
        int M;
        int N;
        int dp[][];
        public int calculateMinimumHP(int[][] dungeon) {
            M=dungeon.length;
            N=dungeon[0].length;
            if(M==1&&N==1){
                return Math.abs(Math.min(dungeon[0][0],0))+1;
            }
            min=Integer.MIN_VALUE;
            map=dungeon;
            dp=new int[M][N];
            int x=0,y=0;
            int curHp=dungeon[0][0];
            int curMin=Math.min(dungeon[0][0],0);
            for(int i=0;i<M;i++){
                for(int j=0;j<N;j++){
                    dp[i][j]=Integer.MIN_VALUE;
                }
            }
            dp[0][0]=curMin;
            deepSearch(curHp,curMin,x,y+1);
            deepSearch(curHp,curMin,x+1,y);


            return Math.abs(min)+1;
        }
        void deepSearch(int curHp,int curMin,int x,int y){
            if(x>=M||y>=N){
                return;
            }
            curHp+=map[x][y];
            curMin=Math.min(curHp,curMin);
            dp[x][y]=Math.max(dp[x][y],curMin);
            if(x==M-1&&y==N-1){
                min=Math.max(min,curMin);
                return;
            }

            //中途发现当前的最小值小于已知的最小值,不用再继续了
            if(curMin<min){
                return;
            }
            if(x+1<M&&y+1<N&&curMin<dp[x+1][y]&&curMin<dp[x][y+1]){
                return;
            }else if(x+1<M&&curMin<dp[x+1][y]){
                return;
            }else if(y+1<N&&curMin<dp[x][y+1]){
                return;
            }
            deepSearch(curHp,curMin,x,y+1);
            deepSearch(curHp,curMin,x+1,y);



        }
    }

    public static void main(String[] args) {
        int[][] map={{1,-3,3},{0,-2,0},{-3,-3,-3}};
        new Solution().calculateMinimumHP(map);
    }
}
