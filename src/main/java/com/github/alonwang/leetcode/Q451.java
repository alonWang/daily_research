package com.github.alonwang.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author alonwang
 * @date 2020/5/28 10:17 下午
 * @detail
 */
public class Q451 {

    public String frequencySort(String s) {
        if (s == null || s.length() < 3) {
            return s;
        }
        Map<Integer, Integer> counts = new HashMap<>();
        s.chars().forEach((val) -> counts.put(val, counts.getOrDefault(val, 0) + 1));
        Iterator<Integer> iter = counts.keySet().iterator();
        int[] result = new int[counts.size() + 1];
        for (int i = 1; i < result.length; i++) {
          result[i]=iter.next();
        }
        buildHeap(result, counts);
        StringBuilder sb = new StringBuilder();
        int i = result.length - 1;
        while (i > 0) {
            int value = result[1];
            int count = counts.get(value);
            for (int j = 0; j < count; j++) {
                sb.append((char) value);
            }

            result[1] = result[i];
            heapify(result, 1, i, counts);
            i -= 1;
        }
        return sb.toString();
    }

    private void heapify(int[] result, int i, int n, Map<Integer, Integer> counts) {
        int value = result[i];
        while (i * 2 < n) {
            int leafPos = i * 2;
            if (i * 2 + 1 < n && counts.get(result[i * 2 + 1]) > counts.get(result[leafPos])) leafPos = i * 2 + 1;
            if (counts.get(value) >= counts.get(result[leafPos])) {
                break;
            }
            result[leafPos] = result[i];
            i = leafPos;
        }
        result[i] = value;
    }

    private void buildHeap(int[] result, Map<Integer, Integer> counts) {
        for (int i = (result.length-1) / 2; i > 0; i--) {
            heapify(result, i, result.length, counts);
        }
    }

    public static void main(String[] args) {
        new Q451().frequencySort("tree");
    }
}
