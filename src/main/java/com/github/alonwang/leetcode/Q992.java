package com.github.alonwang.leetcode;

public class Q992 {
	// 左边扫描偶数,右边扫描奇数 基于快排思想
	public int[] sortArrayByParityII(int[] A) {
		if (A == null || A.length == 0) {
			return A;
		}
		int i = 0;
		int j = ((A.length - 1) % 2 != 0) ? A.length - 1 : A.length - 1 - 1;
		// 左边扫描偶数,右边扫描奇数
		while (true) {
			while (i < A.length && A[i] % 2 == 0) {
				i += 2;
			}
			while (j >= 0 && (A[j] % 2 != 0)) {
				j -= 2;
			}
			if (i < A.length && j >= 0) {
				swap(A, i, j);
			} else {
				break;
			}
		}
		return A;
	}

	public void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}
