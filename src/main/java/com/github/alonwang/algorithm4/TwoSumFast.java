package com.github.alonwang.algorithm4;

import java.util.Arrays;

public class TwoSumFast {
    public static int count(int[] sources) {
        Arrays.sort(sources);

        int count = 0;
        for (int i = 0; i < sources.length; i++) {
            if (BinarySearch.rank(-sources[i], sources) > i) {
                count++;
            }
        }
        return count;
    }
}
