package com.github.alonwang.leetcode;

/**
 * 以第一个字符串为前缀,不断缩短长度,与剩余字符串比较.
 */
class Q14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length<1)
            return "";
        String prefix=new String(strs[0]);

        while(prefix.length()>0){
            boolean flag=true;
            for(int i = 1; i<strs.length; i++){
                if(!strs[i].startsWith(prefix)){
                    flag=false;
                    break;
                }
            }
            if(flag)
                return prefix;


            if(prefix.length()==1)
                prefix="";
            else
                prefix=prefix.substring(0,prefix.length()-1);

        }
        return prefix;
    }

}