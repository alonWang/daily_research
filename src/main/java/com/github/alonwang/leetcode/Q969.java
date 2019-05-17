package com.github.alonwang.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择排序:找出最大值,与最后一个元素交换(使用反转完成这个过程)
 *
 * 时间复杂度 O(N^2)
 * 空间复杂度 O(1)
 */
public class Q969 {
    public List<Integer> pancakeSort(int[] A) {
        if(A==null||A.length==0){
            return new ArrayList<>();
        }
        List<Integer> result=new ArrayList<>();
        for(int i=A.length-1;i>0;i--){
            int pivot=A[0];
            int pos=0;
            for(int j=1;j<=i;j++){
                if(A[j]>pivot){
                    pivot=A[j];
                    pos=j;
                }
            }
            if(pos!=i){
                result.add(pos+1);
                reverseFirstK(A,pos);
                result.add(i+1);
                reverseFirstK(A,i);
            }
        }
        return result;
    }
    public void reverseFirstK(int[] A,int j){
        int i=0;
        while(i<j){
            swap(A,i,j);
            i++;
            j--;
        }
    }
    public void swap(int[] A,int i,int j){
        int temp=A[i];
        A[i]=A[j];
        A[j]=temp;
    }
}
