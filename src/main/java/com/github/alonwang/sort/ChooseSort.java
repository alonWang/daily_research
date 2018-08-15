package com.github.alonwang.sort;

/**
 * 选择排序
 * <p>
 * 0到N-1的任意i都会进行一次交换和N-i-1次比较
 * 平均 N^2/2次比较和N次交换
 * 不稳定
 */
public class ChooseSort extends CompareTemplate {
    public static void main(String[] args) {
        ChooseSort chooseSort = new ChooseSort();
        Integer a[] = new Integer[]{1, 5, 3, 4, 7, 2, 4, 6};
        chooseSort.sort(a);
        System.out.println(chooseSort.isSorted(a));
        chooseSort.show(a);
    }

    @Override
    public void sort(Comparable[] a) {
        final int len = a.length;
        for (int idx = 0; idx < len; idx++) {
            int minValIdx = idx;
            //determine the index of the minimum value
            for (int inboundIdx = minValIdx + 1; inboundIdx < len; inboundIdx++) {
                if (less(a[inboundIdx], a[minValIdx])) minValIdx = inboundIdx;
            }
            //put the (idx+1)th minimum value at current index
            exch(a, idx, minValIdx);
        }
    }
}
