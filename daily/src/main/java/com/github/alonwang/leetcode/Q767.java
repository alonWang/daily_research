package com.github.alonwang.leetcode;

/**
 * 关键点:
 * 1. 桶排序
 * 2. 设字符串长度为N,字符串中某个字符出现次数为C, 如果 C>(N+1)/2 就无法满足题目要求,只需要判断出现次数最多的那个字符是否满足就可以了
 * 3. 先放置偶数位置,再放置奇数位置,**确保先放置出现次数最多的字符**.
 */
public class Q767 {
	public String reorganizeString(String S) {
		int n = S.length(), maxCount = -1;
		char maxLetter = 'a';
		char[] res = new char[n];
		int[] map = new int[26];

		for (char c : S.toCharArray()) {
			map[c - 'a']++;
			if (maxCount < map[c - 'a']) {
				maxCount = map[c - 'a'];
				maxLetter = c;
			}
		}
		if (maxCount > (n + 1) / 2)
			return "";
		int i = 0;
		while (map[maxLetter - 'a'] > 0) {
			res[i] = maxLetter;
			i += 2;
			map[maxLetter - 'a']--;
		}
		for (int j = 0; j < 26; j++) {
			while (map[j] > 0) {
				if (i >= n)
					i = 1;
				res[i] = (char) (j + 'a');
				i = i + 2;
				map[j]--;
			}
		}
		return new String(res);
	}
}
