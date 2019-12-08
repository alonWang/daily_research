package com.github.alonwang.leetcode;

class Q34 {
	public int[] searchRange(int[] nums, int target) {
		int n = nums.length;
		int lastPos = guessLast(0, n, target, nums);
		int firstPos = guessFirst(0, n, target, nums);
		if (-1 != lastPos && nums[lastPos] == target && -1 != firstPos
				&& nums[firstPos] == target) {
			return new int[] { firstPos, lastPos };
		} else
			return new int[] { -1, -1 };
    }

	// 最后一个符合条件的元素的下标
	public int guessLast(int L, int R, int target, int[] nums) {
		int ans = -1;
		while (L < R) {
			int mid = (L + R) / 2;
			if (nums[mid] <= target) {
				ans = mid;
				L = mid + 1;
			} else {
				R = mid;
			}
		}
		return ans;
	}

	public int guessFirst(int L, int R, int target, int[] nums) {
		int ans = -1;
		while (L < R) {
			int mid = (L + R) / 2;
			if (nums[mid] < target) {
				L = mid + 1;
			} else {
				ans = mid;
				R = mid;
            }
        }
        return ans;
    }
}