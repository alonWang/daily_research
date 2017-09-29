package com.velo.algorithm4.listproblem;


class MyList{
    public int val;
    public MyList next;
    public MyList(int val){
        this.val=val;
    }
}


public class ReverseList {
    //递归
    public static MyList recursiveReverse(MyList head){
        if(head==null||head.next==null)
            return head;
        MyList reversedList=recursiveReverse(head.next);
        head.next.next=head;

        head.next=null;
        return reversedList;
    }
    //非递归
    public static MyList nonRecursiveReverse(MyList head){
        MyList prev=head;
        MyList cur=head.next;
        MyList nex;
        while(cur!=null){
            nex=cur.next;
            //下面这句是错的,想想为什么
            //prev.next=null;
            cur.next=prev;

            prev=cur;
            cur=nex;

        }
        head.next=null;
        return prev;
    }
        public static void main(String[] args){
            MyList head=new MyList(0);
            MyList prev=head;
            for(int i=1;i<10;i++){
                MyList temp=new MyList(i);
                prev.next=temp;
                prev=temp;
            }
            prev.next=null;

           // prev=recursiveReverse(head);
           prev=nonRecursiveReverse(head);
            while(prev!=null){
                System.out.print(prev.val+" ");
                prev=prev.next;
            }
        }
}
