package com.github.alonwang.leetcode;/*
linesToScans=rows+cols-1;
even : 
int x= s<rows?s:rows-1;
int y=s<rows?0:s-(rows-1)

odd:
int x=s<cols?0:s-(cols-1)
int y=s<cols?s:cols-1
*/

class Q498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return new int[0];
        int rows=matrix.length;
        int cols= matrix[0].length;
        int linesToScan=rows+cols-1;
        int index=0;
        int[] res=new int[rows*cols];
        for(int i=0;i<linesToScan;i++){
            if(i%2==0){
                int x=i<rows?i:rows-1;
                int y=i<rows?0:i-(rows-1);
                while(x>=0&&y<cols){
                    res[index++]=matrix[x--][y++];
                }
            }else{
                int x=i<cols?0:i-(cols-1);
                int y=i<cols?i:cols-1;
                while(x<rows&&y>=0){
                    res[index++]=matrix[x++][y--];
                }
            }
        }
        return res;
    }
}