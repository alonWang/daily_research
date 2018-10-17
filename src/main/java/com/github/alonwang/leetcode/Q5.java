package com.github.alonwang.leetcode;

/**
 * @author weilong.wang Created on 2018/10/17
 */
public class Q5 {
	/**
	 * 遍历下标作为回文串中心,采用从中心向两边扩张的方式,并且对回文串长度为奇偶分别处理
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() <= 0) {
			return "";
		}

		final int len = s.length();
		// 最长回文串的中心坐标
		int centerOfMax = 0;
		// 最长回文串的长度
		int maxLen = 0;
		// 最长回文串长度为偶数
		boolean odd = false;

		// 从中间向两边扫描
		for (int center = 0; center < len; center++) {

			int left;
			int right;
			int curLen = 0;
			// 回文串长度为奇数
			while ((left = center - curLen) >= 0
					&& (right = center + curLen) < len) {
				if (s.charAt(left) != s.charAt(right)) {
					break;
				}
				curLen++;
			}
			// 上面循环curLen为0时left==right,比较的是同一字符,因此需要减一
			int oddLen = 2 * curLen - 1;
			if (oddLen > maxLen) {
				maxLen = oddLen;
				centerOfMax = center;
				odd = true;
			}

			// 回文串长度为偶数
			curLen = 0;
			while ((left = center - curLen) >= 0
					&& (right = center + curLen + 1) < len) {
				if (s.charAt(left) != s.charAt(right)) {
					break;
				}
				curLen++;
			}
			int evenLen = 2 * curLen;
			if (evenLen > maxLen) {
				maxLen = evenLen;
				centerOfMax = center;
				odd = false;
			}
		}
		int left = centerOfMax - maxLen / 2 + 1;
		// 与上面减一原因相同
		if (odd) {
			left--;
		}
		int right = centerOfMax + maxLen / 2;
		return s.substring(left, right + 1);
	}
}
