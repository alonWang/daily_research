package com.github.alonwang.leetcode;

/**
 * @description: Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * @author: alonwang
 * @create: 2019-05-28 09:10
 **/
public class Q324 {
    public static void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int N = nums.length;
        //quickSort
        int mid = N / 2;
        int balance = findKthNum(nums, 0, N - 1, mid);
        int lastEvenPos = N % 2 == 0 ? N - 2 : N - 1;
        // 从左向右 奇数位(峰)  ,从右向左,偶位数(谷)   交换
        for (int i = 1, j = lastEvenPos; i < mid; i += 2, j -= 2) {
            swap(nums, i, j);
        }
        //将中位数交换到两边
        for (int i = 0, j = i; i < N; i += 2) {
            if (nums[i] == balance) {
                swap(nums, i, j);
                j += 2;
            }
        }

        for (int i = lastEvenPos, j = i; i >= 0; i -= 2) {
            if (nums[i] == balance) {
                swap(nums, i, j);
                j -= 2;
            }
        }
    }

    /**
     * @param nums
     * @param l    include
     * @param h    include
     * @param k
     * @return
     */
    public static int findKthNum(int[] nums, int l, int h, int k) {
        //单边排 必须确定哨兵位置
        int balance = nums[h];
        int i = l;
        for (int j = l; j < h; j++) {
            if (nums[j] < balance) {
                swap(nums, j, i++);
            }
        }
        swap(nums, i, h);
        if (i < k) {
            return findKthNum(nums, l + 1, h, k);
        } else if (i > k) {
            return findKthNum(nums, l, h - 1, k);
        } else {
            return nums[i];
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        wiggleSort(new int[]{1, 1, 2, 1, 2, 2, 1});
    }
}
