package com.github.alonwang.listproblem;

public class MidNode {

    public static int findMidValue(Node head) {
        Node slow, quick;
        slow = quick = head;
        while (quick.next != null) {
            quick = quick.next;
            if (quick.next != null) {
                quick = quick.next;
                slow = slow.next;
            }
        }
        return slow.val;

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

        System.out.print(findMidValue(head));
    }
}
