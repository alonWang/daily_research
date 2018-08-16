package com.github.alonwang.leetcode;

import java.util.Arrays;

/**
 * @author weilong.wang Created on 2018/8/16
 */
class Q75 {
	public void sortColors(int[] nums) {
		if (nums.length == 0) {
			return;
		}
		int[] colorCounts = new int[3];
		for (Integer num : nums) {
			colorCounts[num]++;
		}
		int redCount = colorCounts[0];
		int whiteCount = colorCounts[1];
		int blueCount = colorCounts[2];
		Arrays.fill(nums, 0, redCount, 0);
		Arrays.fill(nums, redCount, redCount + whiteCount, 1);
		Arrays.fill(nums, redCount + whiteCount,
				redCount + whiteCount + blueCount, 2);
	}
}
