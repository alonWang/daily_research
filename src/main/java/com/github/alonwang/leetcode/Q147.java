package com.github.alonwang.leetcode;

/**
 * @author weilong.wang Created on 2018/8/17
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
		ListNode headNode = head;
		ListNode formerNode = headNode;
		ListNode curNode = formerNode.next;
		while (curNode != null) {
			// remove node
			ListNode latterNode = curNode.next;
			formerNode.next = latterNode;
			curNode.next = null;

			// if less than head node ,become first
			if (curNode.val < headNode.val) {
				curNode.next = headNode;
				headNode = curNode;
				formerNode = formerNode.next;
				curNode = formerNode.next;

				continue;
			}

			ListNode prevNode = headNode;
			ListNode loopNode = prevNode.next;
			while (loopNode != null) {
				if (curNode.val < loopNode.val) {
					prevNode.next = curNode;
					curNode.next = loopNode;
					break;
				}
				prevNode = loopNode;
				loopNode = loopNode.next;
			}
			if (curNode.next == null) {
				prevNode.next = curNode;
			}
			formerNode = formerNode.next;
			curNode = formerNode.next;
		}
		return headNode;

	}

	public static void main(String[] args) {
		ListNode head = new ListNode(4);
		ListNode sec = new ListNode(2);
		ListNode third = new ListNode(1);
		ListNode four = new ListNode(3);
		head.next = sec;
		sec.next = third;
		third.next = four;
		insertionSortList(head);
	}
}
