package com.github.alonwang.leetcode;

import java.util.Arrays;

public class Q767 {
	public String reorganizeString(String S) {
		if (S == null || S.length() == 0) {
			return "";
		}
		int N = S.length();
		int ALPHA = 26;
		int[][] buckets = new int[ALPHA][2];
		for (int i = 0; i < ALPHA; i++) {
			buckets[i][1] = 'a' + i;
		}
		for (char c : S.toCharArray()) {
			buckets[c - 'a'][0]++;
		}
		Arrays.sort(buckets, (a1, a2) -> a2[0] - a1[0]);
		if (buckets[0][0] > (N + 1) / 2) {
			return "";
		}
		char[] result = new char[N];
		int j = 0;
		for (int i = 0; i < ALPHA; i++) {
			for (int k = 0; k < buckets[i][0]; k++, j += 2) {
				if (j >= N) {
					j = 1;
				}
				result[j] = (char) buckets[i][1];
			}
		}
		return new String(result);
	}
}
