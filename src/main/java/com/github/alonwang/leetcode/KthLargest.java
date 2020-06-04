package com.github.alonwang.leetcode;

/**
 * @author alonwang
 * @date 2020/6/3 8:51 下午
 * @detail
 */
public class KthLargest {
    private final int k;
    private int size;
    //最小堆
    private int[] heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.size = 0;
        this.heap = new int[k];
        for (int num : nums) {
            add(num);
        }
    }

    private int size() {
        return size;
    }

    private int peek() {
        return heap[0];
    }

    private void poll() {
        heap[0] = heap[--size];
        highToLowHeapify(0, size);
    }

    private void highToLowHeapify(int i, int n) {
        while (true) {
            int minPos = i;
            if (i * 2 + 1 < n && heap[i * 2 + 1] < heap[minPos]) {
                minPos = i * 2 + 1;
            }
            if (i * 2 + 2 < n && heap[i * 2 + 2] < heap[minPos]) {
                minPos = i * 2 + 2;
            }
            if (minPos == i) {
                break;
            }
            swap(heap, i, minPos);
            i = minPos;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void offer(int value) {
        heap[size] = value;
        lowToHighHeapify(size, size + 1);
        size++;
    }

    private void lowToHighHeapify(int i, int n) {
        while ((i - 2) / 2 >= 0 && heap[i] < heap[(i - 2) / 2]) {
            swap(heap, i, (i - 2) / 2);

        }
    }

    public int add(int val) {
        if (size() < k) {
            offer(val);
        } else {
            if (peek() < val) {
                poll();
                offer(val);
            }
        }
        return peek();
    }


}
