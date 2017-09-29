package com.velo.algorithm4.sort;

/**
 * 插入排序
 *
 * 平均 N^2/4次比较 N^2/4次交换
 * 最好 N-1次比较 0次交换  (有序数组)
 * 稳定
 */
public class InsertSort extends CompareTemplate {
    @Override
    public void sort(Comparable[] a) {
        int N=a.length;
        for(int i=1;i<N;i++){
            for(int j=i;j>0&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }

    public static void main(String[] args){
        Integer a[]=new Integer[]{1,5,3,4,7,2,4,6};
        InsertSort is=new InsertSort();
        is.sort(a);
        System.out.println(is.isSorted(a));
        is.show(a);
    }
}
