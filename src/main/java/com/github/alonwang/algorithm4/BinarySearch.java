package com.github.alonwang.algorithm4;

public class BinarySearch {
    public static int rank(int key, int[] targets) {
        int low = 0;
        int high = targets.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < targets[mid]) {
                high = mid - 1;
            } else if (key > targets[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] targets = new int[]{1, 2, 3, 4, 5};

    }
}
