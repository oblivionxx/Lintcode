package dup;
import common.ListNode;

/*
Remove all elements from a linked list of integers that have value val.

Example
Given 1->2->3->3->4->5->3, val = 3, you should return the list as 1->2->4->5

Linked List
 */
public class Lint452_Remove_LinkedList_Elements {
	/**
     * @param head a ListNode
     * @param val an integer
     * @return a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        ListNode cur = head;
        ListNode next = null;
        
        if(head==null) return null;
        if(head.val==val) return removeElements(head.next, val);

        while(cur.next!=null){
            next = cur.next;
            if(next.val==val)
                cur.next = next.next;
            else
                cur = next;
            
        }
        
        return head;
    }
}
