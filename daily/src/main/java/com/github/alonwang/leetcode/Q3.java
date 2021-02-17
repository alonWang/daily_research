package com.github.alonwang.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口法解决最长不重复子串问题
 * @author alonwang
 * @date 2021/2/17 10:44 下午
 */
public class Q3 {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if(s.length()==0){
                return 0;
            }
            //字符,下标
            Map<Character,Integer> windows=new HashMap<>();
            int max=0;

            int left=0,right=0;
            while(right<s.length()){
                char c=s.charAt(right);
                Integer oldIndex=windows.get(c);
                if(oldIndex==null||oldIndex<left){
                    max=Math.max(max,right+1-left);
                }else{//发生重复时,左端要移动到首个不重复位置
                    left=oldIndex+1;
                }
                windows.put(c,right);
                right++;
            }
            return max;

        }
    }
}
