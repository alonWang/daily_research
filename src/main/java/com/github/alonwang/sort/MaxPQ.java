package com.github.alonwang.sort;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    private void swim(int n) {
        while (n > 1 && less(n / 2, n)) {
            exch(pq, n / 2, n);
            n = n / 2;
        }
    }

    public Key delMax() {
        Key max = pq[1];
        exch(pq, 1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private void sink(int i) {
        while (i * 2 < N) {
            //determine the max of son
            int j = i * 2;
            if (j < N && less(pq[j], pq[j + 1])) {
                j++;
            }
            if (!less(pq[i], pq[j])) {
                break;
            }
            exch(pq, i, j);
            i = j;
        }
    }

    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
