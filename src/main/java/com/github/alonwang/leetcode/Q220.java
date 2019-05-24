package com.github.alonwang.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于桶排序 从当前桶或相邻的桶中寻找,并且只维护当前k个数字(防止重复数字情况)
 */
public class Q220 {
    private long getID(long i, long w) {
        //处理负数极限情况
        return i < 0 ? (i + 1) / w - 1 : i / w;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            if (d.containsKey(m))
                return true;
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            d.put(m, (long) nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }
}
