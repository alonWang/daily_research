package com.github.alonwang.leetcode;

import java.util.HashMap;

/**
 * 最小覆盖子串
 * @author alonwang
 * @date 2021/2/19 2:45 下午
 */
public class Q76 {
    public static void main(String[] args) {
        new Q76().minWindow("aa" ,"aa");
    }
    public String minWindow(String s, String t) {
        int left = 0, right = 0;
        int valid = 0;
        int start = Integer.MAX_VALUE;
        int len = Integer.MAX_VALUE;
        //需要的字符集合
        HashMap< Character, Integer> needs = new HashMap<>();
        t.chars().forEach(c -> needs.put((char)c, needs.getOrDefault((char)c, 0) + 1));
        // 窗口集合
        HashMap<Character, Integer> windows = new HashMap<>();
        while (right < s.length()) {
            char addC = s.charAt(right);
            right++;
            //在需要的字符集内,更新窗口
            if (needs.containsKey(addC)) {
                int count = windows.getOrDefault(addC, 0) + 1;
                windows.put(addC, count);
                if (count == needs.get(addC)) {
                    valid++;
                }
            }
            //已经满足需要字符,左侧开始缩减
            while(valid == needs.size()) {
                if (right-left<len){
                    len=right-left;
                    start=left;
                }
                char removeC=s.charAt(left);
                left++;
                if (needs.containsKey(removeC)){
                    int count=windows.get(removeC);
                    if (count==needs.get(removeC)){
                        valid-=1;
                    }
                    windows.put(removeC,count-1);
                }
            }


        }
        return start == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
