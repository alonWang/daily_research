package com.github.alonwang.leetcode;

import java.util.HashSet;
import java.util.Set;

class Q3 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set=new HashSet<Character>();

        int i=0,j=0;
        int n=s.length();
        int ans=0;
        while(i<n&&j<n){

            if(set.contains(s.charAt(j))){
                set.remove(s.charAt(i++));
            }else{
                set.add(s.charAt(j++));
                ans=Math.max(j-i,ans);
            }

        }
        return ans;
    }
}