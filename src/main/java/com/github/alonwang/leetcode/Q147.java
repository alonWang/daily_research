package com.github.alonwang.leetcode;

/**
 * @author weilong.wang Created on 2018/8/17
 * <p>
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
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // simplify insert of headNode
        ListNode dummyNode = new ListNode(0);
        ListNode curNode = head;
        dummyNode.next = head;
        while (curNode.next != null) {
            if (curNode.val <= curNode.next.val) {
                curNode = curNode.next;
            } else {
                ListNode targetNode = curNode.next;

                //remove target node
                curNode.next = curNode.next.next;

                //user begin.node.next to complete insert
                ListNode loopNode = dummyNode;
                while (loopNode.next.val < targetNode.val) {
                    loopNode = loopNode.next;
                }
                targetNode.next = loopNode.next;
                loopNode.next = targetNode;
            }

        }
        return dummyNode.next;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode sec = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode four = new ListNode(1);
        head.next = sec;
        sec.next = third;
        third.next = four;
        insertionSortList(head);
    }
}
