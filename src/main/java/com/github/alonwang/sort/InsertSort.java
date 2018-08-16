package com.github.alonwang.sort;

/**
 * 插入排序
 * <p>
 * 平均 N^2/4次比较 N^2/4次交换
 * 最好 N-1次比较 0次交换  (有序数组)
 * 稳定
 */
public class InsertSort extends CompareTemplate {
    public static void main(String[] args) {
        Integer a[] = new Integer[]{1, 5, 3, 4, 7, 2, 4, 6};
        InsertSort is = new InsertSort();
        is.sort(a);
        System.out.println(is.isSorted(a));
        is.show(a);
    }

    @Override
    public void sort(Comparable[] a) {
        final int len = a.length;
        for (int idx = 1; idx < len; idx++) {
            int inboundIdx = idx;
            do {
                /**
                 exchange if the latter is smaller then the former by index
                 it guarantee [0-inboundIdx-1] is ordered and at least exec once
                 **/
                if (less(a[inboundIdx], a[inboundIdx - 1])) {
                    exch(a, inboundIdx, inboundIdx - 1);
                }
                inboundIdx--;
            } while (inboundIdx >= 1);
        }
    }
}
