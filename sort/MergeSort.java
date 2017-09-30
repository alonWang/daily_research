package com.velo.algorithm4.sort;


public class MergeSort extends CompareTemplate {
    public static Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux=new Comparable[a.length];
        sort(a,0,a.length-1);
    }
    //左右全闭合
    public void sort(Comparable[] a,int lo,int hi){
        if(hi<=lo)return;
        int mid=lo+(hi-lo)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);

    }
    public void merge(Comparable[] a,int lo,int mid,int hi){
        //前后半段分别处理
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++)
            aux[k]=a[k];
        /*
        如果前半段已处理完,处理后半段
        否则 如果后半段已处理完,处理前半段
        否则 如果后半段小于前半段,处理后半段
        否则 处理前半段
         */
        for(int k=lo;k<=hi;k++){
            if(i>mid) a[k]=aux[j++];
            else if(j>hi) a[k]=aux[i++];
            else if(less(aux[j],aux[i]))a[k]=aux[j++];
            else a[k]=aux[i++];
        }
    }
    public static void main(String[] args){
        Integer a[]=new Integer[]{1,5,3,4,7,2,4,6};
        MergeSort ms=new MergeSort();
        ms.sort(a);
        System.out.println(ms.isSorted(a));
        ms.show(a);

    }
}
