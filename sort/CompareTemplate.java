package com.velo.algorithm4.sort;

/*
比较模版,实现了判定/显示/比较功能
 */
public abstract class CompareTemplate {
    public abstract void sort(Comparable[] a);
    public boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    public void exch(Comparable[]a,int i,int j){
        Comparable t=a[i];a[i]=a[j];a[j]=t;
    }
    public void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++){
            if(less(a[i],a[i-1]))
                return false;
        }
        return true;
    }
}
