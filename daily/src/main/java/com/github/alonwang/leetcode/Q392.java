package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/8 7:57 下午
 */
public class Q392 {
    class Solution {
        /**
         * dp[i][j] 在t的位置i,字符j出现的最小位置.
         * "abb"
         * dp[0]['b']=1
         * dp[1]['b']=1
         * @param s
         * @param t
         * @return
         */
        public boolean isSubsequence(String s, String t) {
            if (s.length() == 0) {
                return true;
            }
            final int LEN = t.length();
            int[][] dp = new int[LEN + 1][26];
            for (int i = 0; i < 26; i++) {
                dp[LEN][i] = t.length();
            }
            for (int i = LEN - 1; i >= 0; i--) {
                char c = t.charAt(i);
                for (int j = 0; j < 26; j++) {
                    if (c - 'a' == j) {
                        dp[i][j] = i;
                    } else {
                        dp[i][j] = dp[i + 1][j];
                    }
                }
            }
            int i = 0;
            int j = 0;
            while (i < s.length()) {
                char c = s.charAt(i);
                if (dp[j][c-'a'] < LEN) {
                    j = dp[j][c-'a']+1;
                    i++;
                } else {
                    return false;
                }
            }
            return true;

        }
    }
}
