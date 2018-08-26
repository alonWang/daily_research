package com.github.alonwang.leetcode;

class Q179 {
    public String largestNumber(int[] nums) {
        if (nums.length < 2) {
            return String.valueOf(nums[0]);
        }
        sort(nums);
        StringBuilder sb = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--) {
            sb.append(nums[i]);
        }
        if (sb.charAt(0) == '0') return "0";
        else return sb.toString();


    }

    public void sort(int a[]) {
        final int len = a.length;
        for (int idx = 0; idx < len; idx++) {
            int minValIdx = idx;
            //determine the index of the minimum value
            for (int inboundIdx = minValIdx + 1; inboundIdx < len; inboundIdx++) {
                if (less(a[inboundIdx], a[minValIdx])) minValIdx = inboundIdx;
            }
            //put the (idx+1)th minimum value at current index
            int swapVal = a[idx];
            a[idx] = a[minValIdx];
            a[minValIdx] = swapVal;
        }
    }

    public boolean less(int a, int b) {
        String aStr = String.valueOf(a);
        String bStr = String.valueOf(b);
        if (Long.parseLong(aStr + bStr) < Long.parseLong(bStr + aStr)) return true;
        else return false;
    }
}