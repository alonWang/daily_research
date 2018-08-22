package com.github.alonwang.leetcode;

/**
 * @author weilong.wang Created on 2018/8/22
 */
// TODO use linkedlist
public class Q164 {
	public int maximumGap(int[] nums) {
		final int len = nums.length;
		if (len < 2)
			return 0;
		int min = nums[0], max = nums[0];
		for (int i = 1; i < len; i++) {
			if (nums[i] < min)
				min = nums[i];
			if (nums[i] > max)
				max = nums[i];
		}
		final int offset = min;
		final int bucketLen = max - min + 1;
		if (bucketLen < 2)
			return 0;
		boolean[] bucket = new boolean[bucketLen];
		for (int i = 0; i < len; i++) {
			bucket[nums[i] - offset] = true;
		}
		int maxGap = 0;
		int curGap = 0;
		for (int i = 1; i < bucketLen; i++) {
			if (!bucket[i])
				curGap++;
			else
				curGap = 0;

			maxGap = curGap > maxGap ? curGap : maxGap;
		}
		return maxGap + 1;
	}
}
