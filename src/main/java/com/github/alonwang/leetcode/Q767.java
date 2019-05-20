package com.github.alonwang.leetcode;

public class Q767 {
	public String reorganizeString(String S) {
		if (S == null || S.length() == 0) {
			return "";
		}
		int N = S.length();
		int ALPHA = 26;
		int[] buckets = new int[ALPHA];
		for (char c : S.toCharArray()) {
			buckets[c - 'a']++;
		}
		for (int bucket : buckets) {
			// other>=current-1
			if (bucket - 1 > N / 2) {
				return "";
			}
		}
		char[] result = new char[N];
		int idx = 0;
		int i = 0;
		int j = i + 1;
		while (j < ALPHA) {

			if (buckets[i] > 0 && buckets[j] > 0) {

			}
		}

	}
}
