package com.github.alonwang.sort;

/**
 * 哨兵&平行移位
 */
public class SimpleInsertSort {
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int sentinel = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > sentinel) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = sentinel;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{13, 3, 55, 76, 567, 44, 7};
        insertSort(arr);
        for (int n : arr) {
            System.out.println(n);
        }
    }

}
