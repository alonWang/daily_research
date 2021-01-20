package com.github.alonwang.algorithm.sort;


public class HeapSort extends CompareTemplate {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        //建大顶堆
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private void sink(Comparable[] a, int i, int n) {
        //使以当前节点为根的子树满足大顶堆
        //找到当前节点的最大的子节点,父子节点互换
        //从子节点重复这一流程,直到无子节点
        while (i * 2 < n) {
            int j = i * 2;
            if (j < n && less(a[j], a[j + 1])) {
                j=j+1;
            }
            if (!less(a[i], a[j])) {
                break;
            }
            exch(a, i, j);
            i = j;
        }

    }

}
