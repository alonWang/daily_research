package com.github.alonwang.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author alonwang
 * @date 2020/5/26 21:12
 * @detail
 */
public class Q347 {
    public static void main(String[] args) {
        new Q347().topKFrequent(new int[]{1}, 1);
    }

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }
        if (nums.length == k) {
            return nums;
        }
        //散列表记录
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.compute(num, (key, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }


        int n = k + 1;
        int pos = 1;
        //构造维护一个小顶堆
        Iterator<Integer> iter = counts.keySet().iterator();
        while (iter.hasNext()) {
            if (pos >= n) {
                break;
            }
            nums[pos++] = iter.next();
        }
        buildHeap(nums, n, counts);
        while (iter.hasNext()) {
            int key = iter.next();
            if (counts.get(nums[1]) < counts.get(key)) {
                nums[1] = key;
                swap(nums, 1, n - 1);
                heapify(nums, n, 1, counts);
            }
        }

        return Arrays.copyOfRange(nums, 1, n);
    }

    private void buildHeap(int[] nums, int n, Map<Integer, Integer> counts) {
        for (int i = n / 2; i > 0; i--) {
            heapify(nums, n, i, counts);
        }
    }

    private void swap(int[] result, int i, int j) {
        int temp = result[i];
        result[i] = result[j];
        result[j] = temp;
    }

    /**
     * 从上到下进行堆化
     *
     * @param result
     * @param n      长度
     * @param i      非叶节点下标
     * @param counts
     */
    private void heapify(int[] result, int n, int i, Map<Integer, Integer> counts) {
        int value = result[i];
        while (i * 2 < n) {
            int leafPos = i * 2;
            //找出最小的子节点
            if (i * 2 + 1 < n && counts.get(result[i * 2]) > counts.get(result[i * 2 + 1])) leafPos = i * 2 + 1;
            if (counts.get(value) > counts.get(result[leafPos])) {
                result[i] = result[leafPos];
                i = leafPos;
            } else {
                break;
            }
        }
        result[i] = value;
    }
}
