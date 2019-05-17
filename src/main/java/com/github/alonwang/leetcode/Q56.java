package com.github.alonwang.leetcode;

/**
 * @author weilong.wang Created on 2018/8/16
 */

import java.util.Arrays;

/**
 * 将左区间和右区间分别排序, 如果 starts[i+1]>ends[i] 说明不能合并 大头在排序上 时间复杂度O(NlogN) 空间复杂度 O(N)
 */
public class Q56 {

	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return new int[0][0];
		}
		final int N = intervals.length;
		int[] starts = new int[N];
		int[] ends = new int[N];
		for (int i = 0; i < N; i++) {
			starts[i] = intervals[i][0];
			ends[i] = intervals[i][1];
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int resultLen = 0;
		for (int i = 0, j = 0; i < N; i++) {
			if (i == N - 1 || starts[i + 1] > ends[i]) {
				intervals[resultLen++] = new int[] { starts[j], ends[i] };
				j = i + 1;
			}
		}
		return Arrays.copyOf(intervals, resultLen);

	}
}