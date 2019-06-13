package com.github.alonwang.leetcode;

import java.util.Arrays;
import java.util.Comparator;

class Q179 {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";

        String[] res = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            res[i] = Integer.toString(nums[i]);
        }
        // leetcode 改为lambda写法性能暴跌
        Arrays.sort(res, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String s1 = a + b;
                String s2 = b + a;
                return s2.compareTo(s1);
            }
        });
        // 如果最大的数都为0,结果为0
        if (res[0].charAt(0) == '0') return "0";

        StringBuilder sb = new StringBuilder();

        for (String s : res) {
            sb.append(s);
        }
        return sb.toString();
    }
}