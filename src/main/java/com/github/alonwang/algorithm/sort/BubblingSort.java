package com.github.alonwang.algorithm.sort;

public class BubblingSort {
    public static void main(String[] args) {
        int[] arr = new int[]{43, 25, 3, 76, 33, 75, 22, 3454, 5};
        sort(arr);
        for (int n : arr) {
            System.out.println(n);
        }
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}
