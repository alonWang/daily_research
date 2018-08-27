package com.github.alonwang.leetcode;

import java.util.Arrays;

class Q274 {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;

        //TODO use self sort
        final int len = citations.length;
        Arrays.sort(citations);
        if (citations[0] >= len) return len;


        int low = 0;
        int high = len - 1;
        int h = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (citations[mid] <= (len - mid)) {
                h = citations[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return h;

    }
}