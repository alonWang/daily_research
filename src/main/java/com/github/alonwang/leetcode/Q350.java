package com.github.alonwang.leetcode;

import java.util.Arrays;

public class Q350 {

	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null) {
			return null;
		}
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0, z = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				nums1[z++] = nums1[i];
				i++;
				j++;
			}
		}
		return Arrays.copyOfRange(nums1, 0, z);
	}

}
