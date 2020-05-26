package com.sagar.practice;

/**
 * Created by sagarsingh on 2020-03-20
 */
public class SortLinkedList {
    public static void main(String[] args) {
        System.out.println(Math.sqrt(Integer.MAX_VALUE));
        SortLinkedList sort = new SortLinkedList();
        ListNode head = new ListNode(0);
        int[] input = new int[]{4,2,3,1};
        ListNode curr = head;
        for(int n:input) {
            curr.next = new ListNode(n);
            curr = curr.next;
        }
        sort.sortList(head.next);
    }
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode slow=head;
        ListNode fast =head,prev=null;
        do {
            prev=slow;
            slow = slow.next;
            fast=fast.next.next;
        } while(fast!=null && fast.next!=null);
        prev.next=null;
        return merge(mergeSort(head),mergeSort(slow));
    }

    private ListNode merge(ListNode n1,ListNode n2) {
        if(n1==null) return n2;
        if(n2==null) return n1;
        ListNode result= new ListNode(0);
        ListNode curr = result;
        while(n1!=null && n2!=null) {
            if(n1.val<n2.val) {
                curr.next = n1;
                n1=n1.next;
            } else {
                curr.next = n2;
                n2=n2.next;
            }
            curr = curr.next;
        }
        while(n1!=null) {
            curr.next = n1;
            curr = curr.next;
            n1=n1.next;
        }
        while(n2!=null) {
            curr.next = n2;
            curr=curr.next;
            n2 = n2.next;
        }
        curr.next = null;
        return result.next;
    }

    private static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
