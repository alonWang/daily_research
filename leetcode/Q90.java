package com.velo.algorithm4.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90 {
    public static List<List<Integer>> ans=new ArrayList<>();
    public static boolean []v;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans.clear();
        v=new boolean[nums.length];
        Arrays.sort(nums);
        deepSearch(0,nums);
        return ans;
    }

    /**
     *关键时去重
     * 举个例子,数组 [7,8,8],用1,0代表取或不取
     * 如果不去重
     * 对于 组合[7,8]
     * 有[1,1,0],[1,0,1]两种情况就是重复的
     * 对于这种情况,可以规定当遇到连续相同数字,只有前面的取了才能取后面的
     * 在这个规则下,[1,1,0]是合法的,[1,0,1]是不合法的,
     *
     * @param idx
     * @param nums
     */
    public static void deepSearch(int idx,int[] nums){
        if(idx== nums.length){
            List<Integer> ls=new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                if(v[i])
                    ls.add(nums[i]);
            }
            ans.add(ls);
            return;
        }

        v[idx]=false;
        deepSearch(idx+1,nums);
        //只有前一个数字和当前数字相同并且前一个数字被选中才能选择当前数字
        if(!(idx>0&&nums[idx-1]==nums[idx]&&false==v[idx-1])){
            v[idx]=true;
            deepSearch(idx+1,nums);
        }

    }

}
