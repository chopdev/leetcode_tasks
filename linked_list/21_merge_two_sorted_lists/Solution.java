import java.lang.*;

/*
21. Merge Two Sorted Lists
https://leetcode.com/problems/merge-two-sorted-lists/
Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(val).append("->");
        ListNode t = next;
        while (t != null) {
            b.append(t.val).append("->");
            t = t.next;
        }

        return b.toString();
    }
}

// Time complexity O(n+m), n - l1 len, m - l2 len.
// https://discuss.leetcode.com/topic/4480/clean-simple-o-n-m-c-solution-without-dummy-head-and-recurtion
// Great recursive solution
// https://discuss.leetcode.com/topic/45002/java-1-ms-4-lines-codes-using-recursion
// https://discuss.leetcode.com/topic/5513/my-recursive-way-to-solve-this-problem-java-easy-understanding

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null && l2 == null)
            return  null;
        else if(l1 == null)
            return l2;
        else  if(l2 == null)
            return l1;

        ListNode res, temp;
        res = new ListNode(0);
        res.next = temp = new ListNode(0);

        if (l1.val <= l2.val) {
            res.val = l1.val;
            l1 = l1.next;
        } else {
            res.val = l2.val;
            l2 = l2.next;
        }

        while (true) {
            int val1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int val2 = l2 == null ? Integer.MAX_VALUE : l2.val;

            if (val1 <= val2) {
                temp.val = val1;
                l1 = l1.next;
            } else {
                temp.val = val2;
                l2 = l2.next;
            }

            if (l1 == null && l2 == null)
                break;

            temp.next = new ListNode(0);
            temp = temp.next;
        }

        return res;
    }

    // shorter solution
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // Validation;
        if (l1==null && l2==null) return null;
        if (l1==null) return l2;
        if (l2==null) return l1;

        // Set head of new list;
        ListNode res = new ListNode(-1);

        ListNode current = res;
        while (l1 != null || l2 != null) {
            int val1 = (l1 == null) ? Integer.MAX_VALUE : l1.val;
            int val2 = (l2 == null) ? Integer.MAX_VALUE : l2.val;
            if (val1<val2) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        return res.next;
    }
}