package com.github.alonwang.leetcode;

/**
 * https://leetcode.com/problems/largest-perimeter-triangle/discuss/290049/Java-2ms-or-37MB
 *
 * 关键 : 当 a1<=a2<=a3时,a1+a2>a3就能确定可以构成三角形,如果 a1+a2>a3 不成立,那么对于a4<=a3 更不会成立
 * 思想基于堆排序,在最大堆构建完成后.排序的过程中.
 * 在排序过程中,如果
 * 1. a[i-2]+a[i-1]>a[i]不成立,那么
 * 2. a[i-3]+a[i-1]>a[i]也不会成立
 * 这时不需要再进行 2 ,直接进行
 * 3. a[i-3]+a[i-2]>a[i-1] 即可
 *
 * 大头都在堆的构造过程 时间复杂度 O(N)
 * 原地排序 空间复杂度O(1)
 */
public class Q975 {
    class Solution {
        public int largestPerimeter(int[] A) {

            for (int i = A.length / 2 - 1; i >= 0; i--) {
                adjustHeap(A, i, A.length);
            }

            for (int j = A.length - 1; j > 0; j--) {
                swap(A, 0, j);

                if (j <= A.length - 3) {
                    if (A[j] + A[j + 1] > A[j + 2]) {
                        return A[j] + A[j + 1] + A[j + 2];
                    }
                }
                adjustHeap(A, 0, j);
            }
            if (A[0] + A[1] > A[2]) {
                return A[0] + A[1] + A[2];
            }
            return 0;

        }

        private void adjustHeap(int[] arr, int i, int length) {
            int temp = arr[i];
            int k = 2 * i + 1;
            int j = i;
            while (k < length) {
                if (k + 1 < length && arr[k] < arr[k + 1]) {
                    k = k + 1;
                }
                if (temp < arr[k]) {
                    arr[j] = arr[k];
                    j = k;
                } else {
                    break;
                }
                k = k * 2 + 1;
            }
            arr[j] = temp;

        }

        private void swap(int[] arrays, int i, int j) {
            int temp = arrays[i];
            arrays[i] = arrays[j];
            arrays[j] = temp;
        }
    }
}
