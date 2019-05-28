package com.github.alonwang.sort;

/**
 * @description: 查找第k大/小的数字
 * @author: alonwang
 * @create: 2019-05-27 09:01
 **/
public class FindKthNum {
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
        System.out.println(findKthNum(new int[]{3, 2, 45, 33, 6, 88, 5}, 0, 6, 2));
    }

}
