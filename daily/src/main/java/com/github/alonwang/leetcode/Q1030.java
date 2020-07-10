package com.github.alonwang.leetcode;

import java.util.Arrays;
import java.util.Collections;

/**
 * 基于桶排序,由扩张的最大范围确定桶的数量,将"相同距离"的元素放在同一个桶中
 * 关键:
 * 1. 每个桶的大小和范围相关
 * 2. 自己维护每个桶的当前下标减少内存消耗
 *
 * 主要在R,C的两次循环  时间复杂度 O(R*C)
 * 空间复杂度 TODO
 */
public class Q1030 {
    //base on bucket sort,special handle {r0,c0}, save memory by only using array
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int bucketSize = Collections.max(Arrays.asList(r0 + c0, r0 + ((C - 1) - c0), ((R - 1) - r0) + c0, ((R - 1) - r0) + ((C - 1) - c0)));

        int[][][] buckets = new int[bucketSize][][];
        //bucket[i]=4*(i+1)
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new int[4 * (i + 1)][];
        }
        //represent next idx of bucket;
        int[] idxs = new int[bucketSize];
        //special handle if distance equal 0.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int distance = Math.abs(r0 - i) + Math.abs(c0 - j);
                if (distance == 0) {
                    continue;
                }
                distance -= 1;
                buckets[distance][idxs[distance]++] = new int[]{i, j};
            }
        }
        int[][] result = new int[R * C][];
        result[0] = new int[]{r0, c0};
        int k = 1;
        for (int i = 0; i < bucketSize; i++) {
            if (idxs[i] > 0) {
                for (int j = 0; j < idxs[i]; j++) {
                    result[k++] = buckets[i][j];
                }
            }
        }
        return result;
    }
}
