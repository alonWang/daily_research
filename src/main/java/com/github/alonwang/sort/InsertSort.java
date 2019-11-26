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
        Integer a[] = new Integer[]{1, 5, 34, 443, 7553, 23, 4, 6};
        InsertSort is = new InsertSort();
        is.sort(a);
        System.out.println(is.isSorted(a));
        is.show(a);
    }

    @Override
    public void sort(Comparable[] a) {
        final int len = a.length;
        for (int i = 1; i < len; i++) {
            Comparable targetVal = a[i];
            int j = i;
            for (; j > 0; j--) {
                if (less(targetVal, a[j - 1])) {
                    a[j] = a[j - 1];
                } else {
                    break;
                }
            }
            a[j] = targetVal;
        }
    }
}
