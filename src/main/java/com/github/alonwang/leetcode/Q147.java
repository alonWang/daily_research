package com.github.alonwang.leetcode;

/**
 * @author weilong.wang Created on 2018/8/17
 * <p> 引入辅助节点,避免了头结点的判断逻辑
 * refer https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51619973
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Q147 {
    //归并排序
    public ListNode insertionSortList(ListNode head) {
        // base case
        if (null == head || null == head.next) {
            return head;
        }
        //等分为两个子链表
        ListNode pre = null, slow = head, fast = head;
        while (null != fast && null != fast.next) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        //左侧有序
        ListNode left = insertionSortList(head);
        //右边有序
        ListNode right = insertionSortList(slow);
        //合并左右
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        // base case
        if (null == left) {
            return right;
        }
        if (null == right) {
            return left;
        }
        ListNode head = null;
        if (left.val < right.val) {
            head = left;
            left = left.next;
        } else {
            head = right;
            right = right.next;
        }
        head.next = merge(left, right);
        return head;
    }
}
