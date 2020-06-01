package com.github.alonwang.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author alonwang
 * @date 2020/6/1 18:39
 * @detail
 */
public class Q692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        words = counts.size() == words.length ? new String[counts.size() + 1] : words;
        Iterator<String> iter = counts.keySet().iterator();
        for (int i = counts.size(); i > 0; i--) {
            words[i] = iter.next();
        }
        for (int i = counts.size() / 2; i > 0; i--) {
            heapify(words, i, counts.size() + 1, counts);
        }
        List<String> result = new ArrayList<>(k);
        for (int i = 1; i <= k; i++) {
            result.add(words[1]);
            words[1] = words[counts.size() - i + 1];
            heapify(words, 1, counts.size() - i + 1, counts);
        }

        return result;

    }

    /**
     * 自底至上堆化.
     * 从最后一个非叶节点开始,确保**该节点控制的子节点**满足堆要求
     * @param words
     * @param i
     * @param n
     * @param counts
     */
    private void heapify(String[] words, int i, int n, Map<String, Integer> counts) {
        int j = i;
        String val = words[j];
        while (j * 2 < n) {
            //找出子节点最大的
            int maxPos = j * 2;
            if (j * 2 + 1 < n && isGreater(counts, words, j * 2 + 1, words[j * 2])) {
                maxPos = j * 2 + 1;
            }
            // 不大于父节点,无需处理
            if (!isGreater(counts, words, maxPos, val)) {
                break;
            }
            //大于父节点,子节点提升,父节点下降
            words[j] = words[maxPos];
            j = maxPos;
        }
        words[j] = val;
    }

    /**
     * 优先出现次数,再之字母序排列
     * @param counts
     * @param words
     * @param i
     * @param pivot
     * @return
     */
    private boolean isGreater(Map<String, Integer> counts, String[] words, int i, String pivot) {
        return counts.get(words[i]) > counts.get(pivot) || (counts.get(words[i]).equals(counts.get(pivot)) && words[i].compareTo(pivot) < 0);
    }
}
