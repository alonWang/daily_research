package com.github.alonwang.leetcode;

class Q6 {
    public String convert(String s, int numRows) {
        if(numRows<1)
            return "";

        StringBuilder []sb=new StringBuilder[numRows];
        for(int i = 0; i<numRows; i++)
            sb[i]=new StringBuilder();

        int index=0;
        while(index<s.length()){
            for(int i = 0; i<numRows&&index<s.length(); i++)
                sb[i].append(s.charAt(index++));
            for(int i = numRows-2; i>0&&index<s.length(); i--)
                sb[i].append(s.charAt(index++));


        }
        for(int i=1; i<numRows; i++)
            sb[0].append(sb[i]);
        return sb[0].toString();
    }
}