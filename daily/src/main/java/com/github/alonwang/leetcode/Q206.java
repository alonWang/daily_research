package com.github.alonwang.leetcode;

/**
 * 反转链表的两种方式
 * @author alonwang
 * @date 2021/2/19 7:58 下午
 */
public class Q206 {
    /**
     * 迭代方式需要维护好前节点,当前节点和后续节点的连接
     * 首先,当前节点反转之前,先保存好后续节点,不然关系就丢了
     * 其次,当前节点反转(指向前节点)
     * 最后 为了下次迭代,前节点,当前节点依次向后走
     * @param head
     * @return
     */
    public ListNode reverseListIteration(ListNode head) {
        ListNode cur=head;
        ListNode prev=null,next=null;
        while(cur!=null){
            //锁定后节点
            next=cur.next;
            //反转
            cur.next=prev;
            //更新前节点
            prev=cur;
            //迭代到后节点
            cur=next;
        }
        return prev;
    }

    /**
     * 递归方式首先注意结束条件, 如果没有后续节点了,必然是结束了,而这个节点就是反转后的头节点
     * 其次  在递归的过程中,调用栈中已经包含了前置节点到后续节点的关系,因此在递归返回后,前置节点就无需再指向后续节点
     * 最后  递归调用时从后向前反转,递归返回值就是后节点,要让后节点指向前节点,
     */
    ListNode result = null;
    public ListNode reverseListRecursively(ListNode head) {
        if (head==null){
            return null;
        }
        doReverse(head);
        return result;

    }

    private ListNode doReverse(ListNode head) {
        if (head.next == null) {
            result = head;
            return head;
        }
        // 1->2->3->4->5
        ListNode next = doReverse(head.next);
        //1->2->3->4 5
        head.next = null;
        //1->2->3->4<-5
        next.next = head;
        // 4
        return head;
    }

}
