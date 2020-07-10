package com.github.alonwang.algorithm.sort;

public class SimpleSelectSort {
    public static void sort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int mark = 0;
            int max = arr[0];
            for (int j = 1; j < i; j++) {
                if (max < arr[j + 1]) {
                    max = arr[j + 1];
                    mark = j + 1;
                }
            }
            swap(arr, i, mark);
        }
    }

    private static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{13, 3, 55, 76, 567, 44, 7};
        sort(arr);
        for (int n : arr) {
            System.out.println(n);
        }
    }
}
