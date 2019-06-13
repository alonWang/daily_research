package com.github.alonwang.leetcode;

import java.util.Arrays;

public class Q324 {
    public void wiggleSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int N = nums.length;
        int medium = (N + 1) / 2;
        int l = 0, r = N - 1;
        while (l < r) {
            int pivot = partition(nums, l, r);
            if (pivot == medium) {
                break;
            } else if (pivot < medium) {
                l++;
            } else {
                r--;
            }
        }

        int i = 1, j = N % 2 == 0 ? N - 2 : N - 1;

//        while(i<j){
//            swap(nums,i,j);
//            i+=2;
//            j-=2;
//        }


    }

    private int partition(int[] nums, int l, int r) {
        //哨兵
        int pivot = nums[r];
        int j = l;
        for (int i = l; i < r; i++) {
            if (nums[i] < pivot) swap(nums, i, j++);
        }
        //归位
        swap(nums, j, r);
        return j;

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Q324 q324 = new Q324();
        int[] src = new int[]{1, 3, 2, 2, 1, 3};
        q324.wiggleSort(src);
        System.out.println(Arrays.toString(src));
    }
}
