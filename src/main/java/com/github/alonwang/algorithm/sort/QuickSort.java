package com.github.alonwang.algorithm.sort;

public class QuickSort extends CompareTemplate {
    public void sort(Comparable[] sources) {
        sort(sources, 0, sources.length - 1);
    }

    public void sort(Comparable[] sources, int low, int high) {
        if (high <= low) {
            return;
        }
        int partition = partition(sources, low, high);
        sort(sources, low, partition - 1);
        sort(sources, partition + 1, high);
    }

    private int partition(Comparable[] sources, int low, int high) {
        //prepare for ++,the init value is never used
        int i = low, j = high + 1;
        //default partition
        Comparable partition = sources[low];
        while (true) {
            //find bigger in left
            while (less(sources[++i], partition)) {
                if (i == high) {
                    break;
                }
            }
            //find smaller in right
			while (less(partition, sources[--j])) {
                if (j == low) {
                    break;
                }
            }

            //exchange if ok
            if (i >= j) {
                break;
            }
            //after an extra exchange,j is point at the last of left, it should be the pose of partition
            exch(sources, i, j);
        }
        exch(sources, low, j);
        return j;
    }

    public static void main(String[] args) {
		QuickSort quickSort = new QuickSort();
		Integer a[] = new Integer[] { -400, 3465, 55443, 5, 3, 4, 7, 2, 4, 6,
				1459 };
		quickSort.sort(a);
		System.out.println(quickSort.isSorted(a));
		quickSort.show(a);
    }
}
