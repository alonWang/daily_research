package com.github.alonwang.sort;

/**
 * 选择排序
 * <p>
 * 0到N-1的任意i都会进行一次交换和N-i-1次比较
 * 平均 N^2/2次比较和N次交换
 * 不稳定
 */
public class ChoseSort extends CompareTemplate {
    public static void main(String[] args) {
        ChoseSort choseSort = new ChoseSort();
        Integer a[] = new Integer[]{1, 5, 3, 4, 7, 2, 4, 6};
        choseSort.sort(a);
        System.out.println(choseSort.isSorted(a));
        choseSort.show(a);
    }

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = min + 1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }
}
