package com.github.alonwang.question;
/**
 * http://blog.csdn.net/v_JULY_v/article/details/6110269
 */
public class LCS {


    public static void main(String[] args) {
        String s1 = "ACBFGXXXXFXXXXXXXXX";
        String s2 = "AHUJCJKBHIFJIGVF";
        //trace[i][js means dp[i][j] is  `1` dp[i-1][j-1]    `2` dp[i][j-1]   `3`  dp[i-1][j] 
        int[][] trace = longestCommonSequence(s1, s2);
        showLcs(trace, s1.length(), s2.length(), s1);

    }


    public static int[][] longestCommonSequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        //dp[i][j] means s1[0~i]、s2[0~j] lcs
        int[][] dp = new int[m + 1][n + 1];
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
