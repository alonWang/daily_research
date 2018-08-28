package com.github.alonwang.leetcode;

import java.util.Arrays;

class Q274 {
	public int hIndex(int[] citations) {
		if (citations == null || citations.length == 0)
			return 0;
		final int len = citations.length;
		Arrays.sort(citations);
		if (citations[0] >= len)
			return len;

		int h = 0;
		int curH = 0;
		for (int i = 0; i < len; i++) {
			curH = Math.min(citations[i], len - i);
			h = Math.max(curH, h);
		}
		return h;
	}
}