package com.github.alonwang.leetcode;

/**
 * 遍历,找到red就放置到左边(可看做交换),找到blue就放置到右边(同样是交换),遍历完成后,左边都是red,右边都是blue,中间肯定都是white了
 * @author weilong.wang Created on 2018/8/16
 */
class Q75 {
    public void sortColors(int[] nums) {
        if (nums == null && nums.length == 0) {
            return;
        }
        int p1 = 0, p2 = nums.length - 1, index = 0;
        while (index <= p2) {
            if (nums[index] == 0) {
                nums[index] = nums[p1];
                nums[p1] = 0;
                p1++;
            }
            if (nums[index] == 2) {
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
                index--;
            }
            index++;
        }
    }
}
