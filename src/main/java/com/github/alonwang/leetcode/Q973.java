package com.github.alonwang.leetcode;

import java.util.Arrays;

public class Q973 {
	// k smallest base on heap sort. if requires order ,this is the best choose
	public int[][] kClosest(int[][] points, int K) {

		for (int i = points.length / 2 - 1; i >= 0; i--) {
			adjustHeap(points, i, points.length);
		}
		for (int j = points.length - 1; j > points.length - 1 - K; j--) {
			swap(points, 0, j);
			adjustHeap(points, 0, j);
		}
		int[][] result = new int[K][];
		for (int j = 0; j < K; j++) {
			result[j] = points[points.length - 1 - j];
		}
		return result;
	}

	private void adjustHeap(int[][] arr, int i, int length) {
		int[] temp = arr[i];
		int k = 2 * i + 1;
		int j = i;
		while (k < length) {
			if (k + 1 < length && euclidean(arr[k]) > euclidean(arr[k + 1])) {
				k = k + 1;
			}
			if (euclidean(temp) > euclidean(arr[k])) {
				arr[j] = arr[k];
				j = k;
			} else {
				break;
			}
			k = k * 2 + 1;
		}
		arr[j] = temp;

	}

	private double euclidean(int[] arr) {
		return arr[0] * arr[0] + arr[1] * arr[1];
	}

	private void swap(int[][] arrays, int i, int j) {
		int[] temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}

	// base on quickSort or quickSelect, if don't requre order, this is the
	// fast.
	public int[][] kClosest1(int[][] points, int K) {
		int len = points.length, l = 0, r = len - 1;
		while (l <= r) {
			int mid = helper(points, l, r);
			if (mid == K)
				break;
			if (mid < K) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	private int helper(int[][] A, int l, int r) {
		int[] pivot = A[l];
		// A[l] ensure <= pivot(if loop never exec, then A[l] is pivot exactly)
		while (l < r) {
			// 1
			while (l < r && compare(A[r], pivot) >= 0)
				r--;
			A[l] = A[r];
			// 2
			while (l < r && compare(A[l], pivot) <= 0)
				l++;
			A[r] = A[l];
		}
		// 3
		A[l] = pivot;
		return l;
	}

	private int compare(int[] p1, int[] p2) {
		return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
	}
}
