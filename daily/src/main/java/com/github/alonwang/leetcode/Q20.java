package com.github.alonwang.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alonwang
 * @date 2021/3/1 9:02 下午
 */
public class Q20 {
   static class Solution{
        static Map<Character, Character> matches = new HashMap<>();

        static {
            matches.put('(', ')');
            matches.put('[', ']');
            matches.put('{', '}');
        }

        public boolean isValid(String s) {
            if (s.length() < 2) {
                return false;
            }
            Deque<Character> queue= new ArrayDeque<Character>() {};
            for (char c : s.toCharArray()) {
                if (!queue.isEmpty()&&isMatched(queue.peekFirst(),c)) {
                    queue.removeFirst();
                    continue;
                }
                queue.addFirst(c);
            }
            return queue.isEmpty();
        }

        private boolean isMatched(Character c1, Character c2) {
            Character c = matches.get(c1);
            return c != null && c.equals(c2);
        }
    }
}
