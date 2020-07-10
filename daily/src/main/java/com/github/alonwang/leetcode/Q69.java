package com.github.alonwang.leetcode;

class Q69 {
    boolean guess(long m, long x){
        return m*m <= x;
    }

    public int mySqrt(int x) {
        //这里的强转确保x为Integer.MAX_VALUE时不会溢出
        long l=0,r= (long)x+1;
        //暂存结果,防止死循环
        long ans=0;
        while(l<r){
            long mid=(l+r)/2;
            if(guess(mid,x)){
                ans=mid;
                l=mid+1;
            }else{
                r=mid;
            }
        }
        return (int)ans;
    }
}