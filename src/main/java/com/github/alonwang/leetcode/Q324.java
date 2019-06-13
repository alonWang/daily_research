package com.github.alonwang.leetcode;

/**
 * @description: Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * @author: alonwang
 * @create: 2019-05-28 09:10
 **/
public class Q324 {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int N = nums.length;
        //quickSort
        int mid = (N + 1) / 2;
        int balance = findKthNum(nums, 0, N - 1, mid);
        int lastEvenPos = N % 2 == 0 ? N - 2 : N - 1;
        int lastOddPos = N % 2 == 0 ? N - 1 : N - 2;
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

        for (int i = lastOddPos, j = i; i >= 0; i -= 2) {
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
    public int findKthNum(int[] nums, int l, int h, int k) {
        int base = nums[h];
        int L = l, H = h;
        for (; L <= H; ) {
            for (; L <= H && nums[H] > base; ) {
                H--;
            }
            for (; L <= H && nums[L] < base; ) {
                L++;
            }
            if (L <= H) {
                swap(nums, L, H);
                L++;
                H--;
            }

        }
        if (H - l + 1 >= k) {
            return findKthNum(nums, l, H, k);
        } else if (L - 1 - l + 1 >= k) {
            return base;
        } else {
            return findKthNum(nums, L, h, k - (L - 1 - l + 1));
        }

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Q324 q = new Q324();
        q.wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
    }
}
