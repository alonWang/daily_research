package com.github.alonwang.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alonwang
 * @date 2021/2/19 5:45 下午
 */
public class Q94 {
    class Solution {
        List<Integer> result;

        public List<Integer> inorderTraversal(TreeNode root) {
            result = new ArrayList<>();
            doTravel(root);
            return result;
        }

        public void doTravel(TreeNode root) {
            if (root == null) {
                return;
            }
            doTravel(root.left);
            result.add(root.val);
            doTravel(root.right);
        }
    }
}
