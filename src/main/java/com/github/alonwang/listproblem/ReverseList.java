package com.github.alonwang.listproblem;


public class ReverseList {
    //递归
    public static Node recursiveReverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node reversedList = recursiveReverse(head.next);
        head.next.next = head;

        head.next = null;
        return reversedList;
    }

    //非递归
    public static Node nonRecursiveReverse(Node head) {
        Node prev = head;
        Node cur = head.next;
        Node nex;
        while (cur != null) {
            nex = cur.next;
            //下面这句是错的,想想为什么
            //prev.next=null;
            cur.next = prev;

            prev = cur;
            cur = nex;

        }
        head.next = null;
        return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        Node prev = head;
        for (int i = 1; i < 10; i++) {
            Node temp = new Node(i);
            prev.next = temp;
            prev = temp;
        }
        prev.next = null;

        // prev=recursiveReverse(head);
        prev = nonRecursiveReverse(head);
        while (prev != null) {
            System.out.print(prev.val + " ");
            prev = prev.next;
        }
    }
}
