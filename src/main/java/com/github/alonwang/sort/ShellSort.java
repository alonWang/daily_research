package com.github.alonwang.sort;

/**
 * shell排序
 * 平均 N^1.3
 * <p>
 * <p>
 * <p>
 * 以h为间隔将数组分为h个数组,分别使用插入排序使之有序,为更小的h做准备
 */
public class ShellSort extends CompareTemplate {
    public static void main(String[] args) {
        Integer a[] = new Integer[]{1, 5, 3, 4, 7, 2, 4, 6};
        ShellSort s = new ShellSort();
        s.sort(a);
        System.out.println(s.isSorted(a));
        s.show(a);
    }

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        //N 过小 时生效
        int h = 1;
        while (h < N / 3) h = h * 3 + 1;
        while (h >= 1) {
            //选定第h个数组
            for (int i = h; i < N; i++) {
                //从这个数组的第二个元素开始执行插入排序
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }
}
