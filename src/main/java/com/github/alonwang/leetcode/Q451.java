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
        for (int i = 1; i <= counts.size(); i++) {
            result[i] = iter.next();
        }
        buildHeap(result, counts);
        StringBuilder sb = new StringBuilder();
        int i = counts.size();
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
        int j = i;
        int value = result[j];
        while (j * 2 < n) {
            int leafPos = j * 2;
            if (j * 2 + 1 < n && counts.get(result[j * 2 + 1]) > counts.get(result[leafPos])) leafPos = j * 2 + 1;
            if (counts.get(result[leafPos]) > counts.get(value)) {
                result[j] = result[leafPos];
                j = leafPos;
            } else {
                break;
            }
        }
        result[j] = value;
    }

    private void buildHeap(int[] result, Map<Integer, Integer> counts) {
        for (int i = counts.size() / 2; i > 0; i--) {
            heapify(result, i, result.length, counts);
        }
    }

    public static void main(String[] args) {
        new Q451().frequencySort("tree");
    }
}
