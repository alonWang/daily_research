package com.github.alonwang.algorithm.sort;

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
        int len = a.length;
        //default is the second index of the array if this is no enough element
        int childNum = 1;
        //array len less than 6 degenerate to insert sort
        while (childNum < len / 3) childNum = childNum * 3 + 1;
        while (childNum >= 1) {
            //idx is the second element of the first child array
            for (int idx = (0 + childNum); idx < len; idx++) {
                //sort the (idx%childNum) th child array with insert sort
                for (int idxOfChild = idx; idxOfChild >= childNum && less(a[idxOfChild], a[idxOfChild - childNum]); idxOfChild -= childNum) {
                    exch(a, idxOfChild, idxOfChild - childNum);
                }
            }
            //shrink child array util there is only one child array ,in other way one array.
            childNum /= 3;
        }
    }
}
