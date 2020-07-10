package com.github.alonwang.algorithm.sort;


import java.util.Arrays;

public class MergeSort extends CompareTemplate {
    private static Comparable[] assist;

    public static void main(String[] args) {
        Integer a[] = new Integer[]{1, 5, 3, 4, 7, 2, 4, 6};
        MergeSort ms = new MergeSort();
        ms.sort(a);
        System.out.println(ms.isSorted(a));
        ms.show(a);

    }

    @Override
    public void sort(Comparable[] a) {
        assist = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    // mainly for control the invoke order of multi merge()
    public void sort(Comparable[] sources, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(sources, lo, mid);
        sort(sources, mid + 1, hi);
        merge(sources, lo, mid, hi);

    }

    /**
     * left and right must bu ordered individual
     *
     * @param sources
     * @param lo
     * @param mid
     * @param hi
     */
    public void merge(Comparable[] sources, int lo, int mid, int hi) {
        //前后半段分别处理
        int lIdx = lo, rIdx = mid + 1;
        assist = Arrays.copyOf(sources, sources.length);
        /*
            if  the left or right has over,then just add another side to assist by order
            nor find the smaller in two side of their current pos and put it in the assist
         */
        for (int assIdx = lo; assIdx <= hi; assIdx++) {
            if (lIdx > mid) sources[assIdx] = assist[rIdx++];
            else if (rIdx > hi) sources[assIdx] = assist[lIdx++];
            else if (less(assist[rIdx], assist[lIdx])) sources[assIdx] = assist[rIdx++];
            else sources[assIdx] = assist[lIdx++];
        }
    }
}
