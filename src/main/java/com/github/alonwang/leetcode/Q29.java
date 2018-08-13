package com.github.alonwang.leetcode;

/**
 时间复杂度 O(logn)
*/
class Q29 {

    public int divide(int dividend, int divisor) {
        //-2147483648/-1 很特殊，按照数学规则应该等于2147483648,由于Integer的范围限制，只能等于2147483647
        if(dividend==Integer.MIN_VALUE&&divisor==-1)
            return Integer.MAX_VALUE;
        return divide((long)dividend,(long)divisor);
    }

    public int divide(long dividend, long divisor){
        int sign=1;
        //统一转化为正数
        if(dividend<0){
            dividend=-dividend;
            sign=-sign;
        }
        if(divisor<0){
            divisor=-divisor;
            sign=-sign;
        }
        //快速幂
        int ans=0;
        long pow=divisor;
        while(divisor<=dividend){
            int count=1;
            while(dividend>=(pow<<1)){
                count=count<<1;
                pow=pow<<1;
            }

            dividend-=pow;
            pow=divisor;
            ans+=count;
        }
        return sign==1?ans:-ans;
    }
}