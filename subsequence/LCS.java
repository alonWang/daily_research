package com.velo.algorithm4.subsequence;

/**
 * 最长公共子序列lcs
 *自己尝试描述了一下,不是很满意,请看作者的讲解
 *  http://blog.csdn.net/v_JULY_v/article/details/6110269
 *
 */
public class LCS {


    public static void main(String[] args) {
        String s1 = "ACBFGXXXXFXXXXXXXXX";
        String s2 = "AHUJCJKBHIFJIGVF";
        int m = s1.length();
        int n = s2.length();

        //约定 1{-1,-1} 2{0,-1} 3{-1,0}
        int[][] trace= longestCommonSequence(s1, s2);
        showLcs(trace, m, n, s1);

    }

    public static int[][] longestCommonSequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        //dp[i][j]表示s1[0-i]、s2[0-j]时的最长公共子序列
        int[][] dp=new int[m + 1][n + 1];
        int[][] trace = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    trace[i][j] = 1;
                } else {
                    if (dp[i][j - 1] >= dp[i - 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                        trace[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        trace[i][j] = 3;
                    }

                }
            }
        }

        System.out.println(dp[m][n]);
        return trace;
    }

    public static void showLcs(int[][] trace, int i, int j, String s) {
        //递归
        /*if(i==0||j==0)return;
        else if(trace[i][j]==1){
            showLcs(i-1,j-1,s);
            System.out.print(s.charAt(i-1));
        }else{
            if(trace[i][j]==2)
                showLcs(i,j-1,s);
            else if(trace[i][j]==3)
                showLcs(i-1,j,s);
        }*/
        //非递归
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (trace[i][j] == 1) {
                sb.insert(0, s.charAt(i - 1));
                i--;
                j--;
            } else if (trace[i][j] == 2) {
                j--;
            } else if (trace[i][j] == 3) {
                i--;
            }
        }
        System.out.println(sb.toString());
    }
}
