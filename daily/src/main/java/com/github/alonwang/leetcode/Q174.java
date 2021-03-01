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

        public int calculateMinimumHP(int[][] dungeon) {
            M=dungeon.length;
            N=dungeon[0].length;
            if(M==1&&N==1){
                return Math.abs(Math.min(dungeon[0][0],0))+1;
            }
            min=Integer.MIN_VALUE;
            map=dungeon;

            int x=0,y=0;
            int curHp=dungeon[0][0];
            int curMin=Math.min(dungeon[0][0],0);
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
            if(x==M-1&&y==N-1){
                min=Math.max(min,curMin);
                return;
            }

            //中途发现当前的最小值小于已知的最小值,不用再继续了
            if(curMin<min){
                return;
            }
            deepSearch(curHp,curMin,x,y+1);
            deepSearch(curHp,curMin,x+1,y);
        }
    }

    /**
     * 动态规划解法
     * dp[i][j] 从(i,j)到终点所需最小hp.
     * dp[i][j]=max(1,min(dp[i][j+1],dp[i+1,j])-dungeon[i][j] )
     * 按照这个定义,终点的右侧和下侧只需要为最小血量1.而dp[~][N+1]和dp[M][~]只需要为一个最大的无效值,确保其不被选择到即可
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        int M = dungeon.length;
        int N = dungeon[0].length;
        //从(i,j)到终点所需最小HP 从右下开始计算
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 0; i < M; i++) {
            dp[i][N] = Integer.MAX_VALUE;
        }
        for (int j = 0; j < N; j++) {
            dp[M][j] = Integer.MAX_VALUE;
        }
        //终点的右/下 无效,用1表示从终点走到那里只需要1
        dp[M][N - 1] = dp[M - 1][N] = 1;
        for(int i=M-1;i>=0;i--){
            for (int j=N-1;j>=0;j--){
                //最优解必定来自右侧和下侧中的最小者.
                //如果当前格子扣血(<0) 需要的最小值是增加的,反之相同,所以直接减去格子值即可
                //最后用1保证加血时不能超加
                int min=Math.min(dp[i][j+1],dp[i+1][j]);
                dp[i][j]=Math.max(min-dungeon[i][j],1);
            }
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        int[][] map={{1,-3,3},{0,-2,0},{-3,-3,-3}};
        System.out.println(new Solution().calculateMinimumHP(map));
    }
}
