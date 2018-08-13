package com.github.alonwang.stringatch;

import java.util.Map;
import java.util.HashMap;
public class HorspoolMatch{
    /**
     * 移动表
     * 按照如下公式计算
     * t(c)=
     *  如果c不存在于pattern中,等于pattern的长度
     *  否则等于最后一个c和最后一个字符的距离
     *
     */
    public static Map<Character,Integer> shiftMap(String pattern){
        int i=0;
        int n=pattern.length();
        Map<Character,Integer> shiftMap=new HashMap<Character,Integer>();
        for(i=0; i<n-1; i++){
            shiftMap.put(Character.valueOf(pattern.charAt(i)),Integer.valueOf(n-1-i));
        }
        return shiftMap;
    }

    public static int horspoolMatch(String source,String pattern){
        Map<Character,Integer> shiftMap=shiftMap(pattern);
        int n1=source.length();
        int n2=pattern.length();
        int pos=n2-1;
        int ans=-1;
        while(pos<n1){
            int j=0;
            while(j<n2&&source.charAt(pos-j)==pattern.charAt(n2-1-j)) j++;
            if(j==n2)
                return pos-j+1;
            else{
                if(shiftMap.containsKey(source.charAt(pos-j))){
                    pos+=shiftMap.get(source.charAt(pos-j));
                }else{
                    pos+=n2;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        String s="iamherfecaefvadvaewfwaiewaitingforyou";
        String p="wait";
        System.out.print(s.indexOf("wait",0)+"/n"+horspoolMatch(s,p));
    }
}