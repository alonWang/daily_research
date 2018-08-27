package com.github.alonwang.leetcode;

/**
 * https://www.jianshu.com/p/82e292477e49
 */
public class Q164 {
    static class Bucket {
        int max;
        int min;

        Bucket(int val) {
            max = min = val;
        }
    }

    public static int maximumGap(int[] nums) {
        final int len = nums.length;
        if (len < 2) return 0;

        //determine max&min
        int min = nums[0], max = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] < min) min = nums[i];
            if (nums[i] > max) max = nums[i];
        }
        //determine gap between bucket
        int gapPerBucket = (int) Math.ceil((max - min) / (double) len);
        gapPerBucket = Math.max(gapPerBucket, 1);

        //make sure at least one null bucket,
        Bucket[] buckets = new Bucket[len + 1];
        final int offset = min;

        //put num into correspond bucket
        for (int i = 0; i < len; i++) {
            int index = (nums[i] - offset) / gapPerBucket;
            if (buckets[index] == null) {
                buckets[index] = new Bucket(nums[i]);
            } else {
                buckets[index].max = Math.max(buckets[index].max, nums[i]);
                buckets[index].min = Math.min(buckets[index].min, nums[i]);
            }
        }

        //the gap between two nonull border bucket is the maxGap;
        int maxGap = 0;
        // the first bucket wouldn't be null
        int i = 0;
        int j = 1;
        while (j < buckets.length) {
            while (j < buckets.length - 1 && buckets[j] == null) j++;
            if (buckets[j] == null) break;
            maxGap = Math.max(maxGap, buckets[j].min - buckets[i].max);
            i = j;
            j++;
        }
        return maxGap;

    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1};
        System.out.print(maximumGap(arr));
    }
}
