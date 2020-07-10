package com.github.alonwang.algorithm.sort;

/**
 * 将一维数组看做一颗二叉树 父节点和子节点的关系为 Nk~=N2k+1,N2k+2
 * 1. 构建堆
 * 2. 调整堆
 */
public class SimpleHeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6545, 1, 4456, 5, 4, 65, 63, 5445};
        heapSort(arr);
        for (int n : arr) {
            System.out.println(n);
        }
    }

    private static void heapSort(int[] arr) {
        //从最后一个节点开始扫描,构建最大堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //依次将堆顶最大值移到当前的末尾,构建有序数组
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);
            adjustHeap(arr, 0, j);
        }
    }

    private static void adjustHeap(int[] arr, int i, int length) {
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

    private static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}

