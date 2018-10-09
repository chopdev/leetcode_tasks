/**
 https://leetcode.com/problems/odd-even-linked-list/description/
 Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

 You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

 Example 1:
 Input: 1->2->3->4->5->NULL
 Output: 1->3->5->2->4->NULL

 Example 2:
 Input: 2->1->3->5->6->4->7->NULL
 Output: 2->3->6->7->1->5->4->NULL

 Note:

 The relative order inside both the even and odd groups should remain as it was in the input.
 The first node is considered odd, the second node even and so on ...
 */
public class Solution {

    // Mine solution
    // Time O(N), Space O(1), in place reorder
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode lastOdd = head;
        ListNode prev = head.next;  // need to know previous node to make switch
        ListNode curr = head.next.next;  // current goes over odd nodes

        while (curr != null) {
            // change odd and even
            prev.next = curr.next;
            curr.next = lastOdd.next;
            lastOdd.next = curr;

            // refresh pointers
            lastOdd = lastOdd.next;
            curr = prev.next != null ? prev.next.next : null;
            prev = prev.next;
        }

        return head;
    }


    // Not mine, also good solution
    // https://leetcode.com/articles/odd-even-linked-list/
    public ListNode oddEvenList2222(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
