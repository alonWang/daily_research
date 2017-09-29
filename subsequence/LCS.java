package com.velo.algorithm4.subsequence;

/**
 * 最长公共子序列lcs
 * 时间复杂度 O(mn),空间复杂度 O(2mn) (如果把轨迹数组优化一下可以达到O(mn))
 *自己尝试描述了一下,不是很满意,请看作者的讲解
 *  http://blog.csdn.net/v_JULY_v/article/details/6110269
 *
 */
public class LCS {


    public static void main(String[] args) {
        String s1 = "ACBFGXXXXFXXXXXXXXX";
        String s2 = "AHUJCJKBHIFJIGVF";
        //trace[i][j]表示dp[i][j]是经  `1` dp[i-1][j-1]    `2` dp[i][j-1]   `3`  dp[i-1][j] 得到的
        int[][] trace= longestCommonSequence(s1, s2);
        showLcs(trace, s1.length(), s2.length(), s1);

    }

    /**
     *设置轨迹数组
     * @param s1
     * @param s2
     * @return 轨迹数组
     * 输出最长子序列长度
     */
    public static int[][] longestCommonSequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        //dp[i][j]表示s1[0~i]、s2[0~j]时的最长公共子序列
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

    /**
     *输出最长子序列
     * @param trace
     * @param i s1长度
     * @param j s2长度
     * @param s s1、s2任意
     */
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
