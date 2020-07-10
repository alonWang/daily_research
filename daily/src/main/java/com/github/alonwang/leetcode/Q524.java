package com.github.alonwang.leetcode;

import java.util.List;

/**
 * 核心 String.indexOf(int ch,int fromIndex)
 */
public class Q524 {
	public String findLongestWord(String s, List<String> d) {
		String res = "";
		for (String str : d) {
			if (canTransfer(s, str) && (res.length() < str.length()
					|| (res.length() == str.length()
							&& str.compareTo(res) < 0))) {
				res = str;
			}
		}
		return res;
	}

	private boolean canTransfer(String s, String d) {
		if (s.length() < d.length()) {
			return false;
		}
		int index = -1;
		for (char t : d.toCharArray()) {
			// core
			index = s.indexOf(t, index + 1);
			if (index == -1) {
				return false;
			}
		}
		return true;
	}
}
