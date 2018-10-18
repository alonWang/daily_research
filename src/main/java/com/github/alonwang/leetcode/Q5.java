package com.github.alonwang.leetcode;

/**
 * 主要参考 https://segmentfault.com/a/1190000003914228
 *
 * @author weilong.wang Created on 2018/10/17
 */
public class Q5 {

	/**
	 * 遍历下标作为回文串中心,采用从中心向两边扩张的方式,并且对回文串长度为奇偶分别处理 O(n^2)
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

	/**
	 * 使用动态规划 O(nlgn)
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome1(String s) {
		if (s == null || s.length() <= 0) {
			return "";
		}

		final int len = s.length();
		// palindromes[left][right]=true 说明s[left~right]为回文串
		boolean[][] palindromes = new boolean[len][len];
		int maxLen = 0, start = 0, end = 0;
		for (int right = 0; right < len; right++) {
			int left = right;
			while (left >= 0) {
				// 如果比对字符相当
				if (s.charAt(left) == s.charAt(right)
						// 并且相邻字符| 同一个字符 | 最大的子串是回文说明当前串是回文
						&& (left == right - 1 || left == right
								|| palindromes[left + 1][right - 1])) {
					palindromes[left][right] = true;
					if (right - left + 1 > maxLen) {
						maxLen = right - left + 1;
						start = left;
						end = right;
					}
				}
				left--;
			}
		}
		return s.substring(start, end + 1);
	}

	/**
	 * mannacher O(n)
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		if (s == null || s.length() <= 0) {
			return "";
		}
		s = "#" + String.join("#", s.split("")) + "#";
		final int len = s.length();
		int[] rl = new int[len];
		int maxRight = 0, pos = 0, maxLen = 0;
		for (int i = 0; i < len; i++) {
			if (i < maxRight) {
				rl[i] = Math.min(rl[pos - (i - pos)], maxRight - i);
			} else {
				rl[i] = 1;
			}
			while (i - rl[i] >= 0 && i + rl[i] < len
					&& s.charAt(i - rl[i]) == s.charAt(i + rl[i])) {
				rl[i]++;
			}
			if (rl[i] + i - 1 > maxRight) {
				maxRight = rl[i] + i - 1;
				pos = i;
			}
			maxLen = Math.max(maxLen, rl[i]);
		}

		int maxPos = 0;
		for (int i = 0; i < len; i++) {
			if (rl[i] == maxLen) {
				maxPos = i;
				break;
			}
		}
		maxLen = maxLen - 1;

		return s.substring(maxPos - maxLen, maxPos + maxLen + 1).replaceAll("#",
				"");
	}
}
