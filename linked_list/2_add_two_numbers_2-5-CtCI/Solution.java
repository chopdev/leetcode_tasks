/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * Input: (2 -> 4 -> 3) + (9)
 * Output: 1 -> 5 -> 3
 * Explanation: 342 + 9 = 351.
 * */
public class Solution {

    /**
     * Mine Solution
     * Time complexity : O(max(m,n)). Assume that mm and nn represents the length of t1 and t2 respectively,
     * the algorithm above iterates at most max(m, n) times.
     * Space complexity : O(max(m,n)). The length of the new list is at most max(m,n)+1.
     * */
    public ListNode addTwoNumbers(ListNode t1, ListNode t2) {
        ListNode curr = new ListNode(0);
        ListNode resHead = curr;
        int sum = 0, rest = 0;
        while (t1 != null || t2 != null || rest != 0) {
            int val1 = t1 == null ? 0 : t1.val;
            int val2 = t2 == null ? 0 : t2.val;
            sum = val1 + val2 + rest;

            if(sum >= 10) {
                curr.next = new ListNode(sum % 10);
                rest = 1;
            }else {
                rest = 0;
                curr.next = new ListNode(sum);
            }
            curr = curr.next;
            t1 = t1 == null ? null : t1.next;
            t2 = t2 == null ? null : t2.next;
        }

        return resHead.next;
    }

    // Not mine, shorter solution
    public ListNode addTwoNumbers1111(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
