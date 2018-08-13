package com.github.alonwang.leetcode;

import java.util.HashMap;
import java.util.Map;

class Q377 {
    Map<TreeNode, Integer> rubCache = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> noRubCache = new HashMap<TreeNode, Integer>();

    public int robot(TreeNode node, Boolean flag) {
        if (null == node) {
            return 0;
        }
        if (flag) {
            if (rubCache.containsKey(node))
                return rubCache.get(node);
        } else {
            if (noRubCache.containsKey(node))
                return noRubCache.get(node);
        }


        int ans = 0;
        if (!flag) {
            ans += Math.max(robot(node.left, true), robot(node.left, false));
            ans += Math.max(robot(node.right, true), robot(node.right, false));
        }
        ans = Math.max(ans, robot(node.left, false) + robot(node.right, false));
        if (flag) {
            ans += node.val;
            rubCache.put(node, ans);
        } else {
            noRubCache.put(node, ans);
        }

        return ans;
    }

    public int rob(TreeNode root) {
        return Math.max(robot(root, false), robot(root, true));
    }


}

