package com.github.alonwang.other;

class Template{
    public double quickPower(double x, int n){
        if(n==0)
            return 1;
        else if(n%2==0){
            return quickPower(x, n/2);
        }else{
            double A=quickPower(x, n/2);
            return A*A*x;
        }
    }
}