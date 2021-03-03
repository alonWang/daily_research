package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2021/3/3 4:42 下午
 */
public class Q32 {
    class Solution {
        /**
         * 最长有效括号的动态规划解法
         * dp[i] 表示以下标i结尾的字符串的最长有效长度,字符串的最长有效长度即dp[i]的最大者
         * 对于字符串s中的某个字符s[i]
         * 如果为'(',dp[i]必定为0.
         * 如果为')',考虑以下几种情况
         * 1. 无间隔匹配
         * "()"   i=1
         * 2. 无间隔不匹配
         * "((" i=2
         * 3. 间隔匹配
         * "(())" i=3
         * 4. 间隔不匹配
         * ")())" i=3
         * 5. 匹配且之前有匹配段
         * "()()()" i=5
         * 使dp[i]>0(有效)的条件就是映射匹配点为'('
         * 所谓映射匹配点,举例来看
         * 对于情况1"()",j=0
         * 对于情况2"((",j=0
         * 对于情况3"(())",j=0
         * 对于情况4")())", j=0
         * 对于情况5"()()()", j=4
         * 上面例子可以分成下面两种情况:
         * 如果中间存在有效段如3,4,j=i-dp[i-1]-1
         * 如果中间不存在有效段,如1,2,5 j=i-1
         * 由于中间不存在有效段时dp[i-1]必定为0,
         * 总结出公式 j=i-dp[i-1]-1 (要注意越界问题)
         *
         * 最后要注意情况5,[0,3]是有效的,所以要将dp[3]算入dp[i],
         * 这里算出3的公式为 i - dp[i - 1] - 2
         * 状态转移公式为
         * dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
         * @param s
         * @return
         */
        public int longestValidParentheses(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            int longest = 0;
            int[] dp = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    continue;
                }
                if (i - 1 < 0) {
                    continue;
                }
                int flip = i - dp[i - 1] - 1;

                if (flip < 0 || s.charAt(flip) != '(') {
                    continue;
                }
                int oldLen = i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0;
                dp[i] = dp[i - 1] + 2 + oldLen;

                longest = Math.max(longest, dp[i]);
            }
            return longest;


        }
    }
}
