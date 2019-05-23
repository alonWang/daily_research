package com.github.alonwang.leetcode;

/**
 * quick sort, reverse order,   step by step
 */
class Q274 {
    public int hIndex(int[] c) {
        if (c.length == 0) return 0;
        int l = 0, h = c.length - 1;
        while (l < h) {
            int pos = partition(c, l, h);
            if (c[pos] >= pos + 1) {
                l = pos + 1;
            } else {
                h = pos - 1;
            }
        }
        //all satisfy
        if (l == c.length) {
            return l;
        }

        //after    l = pos + 1, loop end
        if (c[l] >= l + 1) {
            return l + 1;
        } else {//after h = pos - 1, loop end
            return l;
        }

    }


    int partition(int[] c, int s, int e) {
        int pivot = c[e];
        int j = s;
        for (int i = s; i < e; i++) {
            if (c[i] > pivot) swap(c, i, j++);
        }
        swap(c, j, e);
        return j;
    }

    void swap(int[] c, int i, int j) {
        int t = c[i];
        c[i] = c[j];
        c[j] = t;
    }
}