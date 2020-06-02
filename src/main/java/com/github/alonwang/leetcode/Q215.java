package com.github.alonwang.leetcode;

/**
 * 下标从0开始的堆排序
 *
 * @author alonwang
 * @date 2020/6/2 19:45
 * @detail
 */
public class Q215 {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length <= 1) {
            return nums[0];
        }
        for (int i = (nums.length - 2) / 2; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }
        for (int i = 0; i < k - 1; i++) {
            nums[0] = nums[nums.length - i - 1];
            heapify(nums, 0, nums.length - i - 1);
        }
        return nums[0];
    }

    private void heapify(int[] nums, int i, int n) {
        int val = nums[i];
        int j = i;
        while (leftPos(j) < n) {
            int maxPos = leftPos(j);
            if (rightPos(j) < n && nums[rightPos(j)] > nums[maxPos]) {
                maxPos = rightPos(j);
            }
            if (nums[maxPos] > val) {
                nums[j] = nums[maxPos];
                j = maxPos;
            } else {
                break;
            }
        }
        nums[j] = val;
    }

    private int leftPos(int j) {
        return j * 2 + 1;
    }

    private int rightPos(int j) {
        return j * 2 + 2;
    }

    public static void main(String[] args) {
        new Q215().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
    }
}
