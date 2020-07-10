package com.github.alonwang.leetcode;

/**
 * 基于快排思想,左边以下标0(左边第一个为偶数的下标)开始,查找奇数.
 * 右边以下标j(右边最后一个为奇数的下标),查找偶数, 交换.
 * 循环结束后 偶数下标上全部为偶数,奇数下标上全部为奇数.
 *
 * 时间复杂度 O(N)
 * 空间复杂度 O(1)
 */
public class Q992 {
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
