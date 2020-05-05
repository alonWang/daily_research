package com.github.alonwang.algorithm.sort;

/**
 * 初始时将数组分为多个,再逐步增加数组的粒度,应用简单插入排序,在宏观上得到分布更为均匀的数组,最终得到有序的数组. 将数组分为div个,
 * 那么第i个子数组就是 arr[i],arr[i+div],arr[i+2div]
 */
public class SimpleShellSort {
	public static void shellSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int div = arr.length / 2; div >= 1; div /= 2) {
			for (int i = div; i < arr.length; i++) {
				int temp = arr[i];
				int j = i;
				while (j >= div) {
					if (arr[j] < arr[j - div]) {
						arr[j] = arr[j - div];
						j -= div;
					} else {
						break;
					}
				}
				arr[j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 5, 4, 3, 65, 2, 8 };
		shellSort(arr);
		for (int n : arr) {
			System.out.println(n);
		}
	}
}
