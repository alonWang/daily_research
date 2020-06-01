package com.github.alonwang.leetcode;

import java.util.*;

/**
 * @author alonwang
 * @date 2020/6/1 18:39
 * @detail
 */
public class Q692 {
    //    Comparator<Map.Entry<String, Integer>> comparator = (o1,o2)->{
//      return  Map.Entry.comparingByValue().thenComparing(Map.Entry::getKey);
//        if (!o1.getValue().equals(o2.getValue())){
//            return o1.getValue().compareTo(o2.getValue());
//        }else{
//            return
//        }
//
//    };
//    Comparator<Map.Entry<String, Integer>> comparator=Comparator.comparing((Function<Map.Entry<String, Integer>, Integer>) Map.Entry::getValue).thenComparing(Map.Entry::getKey);
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
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
            words[1] = words[counts.size() - i];
            heapify(words, 1, counts.size() - i + 1, counts);
        }

        return result;

    }

    private void heapify(String[] words, final int i, int n, Map<String, Integer> counts) {
//        if (2 * i >= n) {
//            return;
//        }
        int j = i;
        while (j > 0) {
            int maxChildPos = j * 2;
            if (j * 2 + 1 < n && (counts.get(words[j * 2 + 1]) > counts.get(words[j * 2]) || (counts.get(words[j * 2 + 1]).equals(counts.get(words[j * 2])) && words[j * 2 + 1].compareTo(words[j * 2]) < 0))) {
                maxChildPos = j * 2 + 1;
            }
            if ((counts.get(words[maxChildPos]) > counts.get(words[j]) || (counts.get(words[maxChildPos]).equals(counts.get(words[j])) && words[maxChildPos].compareTo(words[j]) < 0))) {
                String temp = words[j];
                words[j] = words[maxChildPos];
                words[maxChildPos] = temp;
                j /= 2;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Q692().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
    }
}
