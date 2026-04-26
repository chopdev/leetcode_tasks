// https://leetcode.com/problems/merge-k-sorted-lists/

import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    // O(N log k)
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        ListNode dummy = new ListNode();
        ListNode head = dummy;

        while (!pq.isEmpty()) {
            dummy.next = pq.poll();
            dummy = dummy.next;
            if (dummy.next != null) {
                pq.add(dummy.next);
            }
        }

        return head.next;
    }
}
