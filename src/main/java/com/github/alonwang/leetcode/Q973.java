package com.github.alonwang.leetcode;

public class Q973 {
	// k smallest base on heap sort.
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
}
