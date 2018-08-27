package com.github.alonwang.leetcode;

class Q242 {
    public boolean isAnagram(String s, String t) {
        int[] alphabets = new int[26];
        for (int i = 0; i < s.length(); i++) alphabets[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabets[t.charAt(i) - 'a']--;
        for (int i : alphabets) if (i != 0) return false;
        return true;
    }
}