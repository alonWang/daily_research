package com.github.alonwang.sort;

public class HeapSort extends CompareTemplate {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private void sink(Comparable[] a, int i, int n) {
        while (i * 2 < n) {
            int j = i * 2;
            if (j < n && less(a[j], a[j + 1])) {
                j++;
            }
            if (!less(a[i], a[j])) {
                break;
            }
            exch(a, i, j);
            i = j;
        }

    }

}
