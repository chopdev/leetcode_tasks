/**
 https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 19. Remove Nth Node From End of List

 Given a linked list, remove the n-th node from the end of list and return its head.

 Example:
 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.

 Note:
 Given n will always be valid.

 Follow up:
 Could you do this in one pass?

 SOLUTION DESCRIPTION
 https://leetcode.com/articles/remove-nth-node-from-end-of-list/
 */
public class Solution2 {

    // My solution O(N) time
    // Firstly we make n steps from the beginning by fast pointer
    // Then start to move slow pointer adn fast pointer simultaniously
    // when fast will be on the end, slow will be on the element we need to remove
    // Other approach is to get size of the list first O(2N)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head, prev = null;

        for (int i = 1; i < n; i++)
            fast = fast.next;

        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if(prev != null) {
            prev.next = slow.next;
            slow.next = null;
            return head;
        }

        return slow.next;
    }


    // My solution but without previous pointer
    // We use dummy to avoid checking if first or middle element is deleted
    public ListNode removeNthFromEnd2222(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        for (int i = 1; i < n + 1; i++)
            fast = fast.next;  // set firts on Nth element from beginning

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next; // slow is on prev element to element we need to remove

        return dummy.next;
    }
}
