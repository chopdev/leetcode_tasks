/*
82. Remove Duplicates from Sorted List II
https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/#/description
Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.
For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;

        ListNode curr, stable = null; // stable - element without duplicate next
        curr = head;
        head = null;
        boolean duplicate = false;

        while(curr.next != null)
        {
            boolean nextIsSame = curr.val == curr.next.val; // look one step forward

            if(duplicate) { // step forward if duplicate
                if(!nextIsSame)
                    duplicate = false;

                curr = curr.next;
                continue;
            }

            if(nextIsSame) {
                duplicate = true;
            }
            else {
                if(stable == null)
                    head = stable = curr;
                else {
                    stable.next = curr;
                    stable = stable.next;
                }
            }

            curr = curr.next;
        }

        // analyze last element
        if(duplicate)
        {
            if(stable == null)
                return null;

            stable.next = null;
        }
        else {
            if(stable == null)
                head = stable = curr;
            else
                stable.next = curr;
        }

        return head;
    }


    //LeetCode solution
    public ListNode deleteDuplicates2222(ListNode head) {
        if(head==null) return null;
        ListNode FakeHead=new ListNode(0);
        FakeHead.next=head;
        ListNode pre=FakeHead;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
            }
            if(pre.next==cur){
                pre=pre.next;
            }
            else{
                pre.next=cur.next;
            }
            cur=cur.next;
        }
        return FakeHead.next;
    }
}