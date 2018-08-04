/**
 * https://leetcode.com/problems/partition-list/description/
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 * */
public class Solution {
    // Mine solution Time: O(N), Space O(1)
    public ListNode partition(ListNode head, int x) {
        ListNode lHead = null, lTail = null, rHead = null, rTail = null, curr = head;
        while (curr != null) {
            if(curr.val < x) {
                if(lHead == null)
                    lHead = curr;

                if(lTail == null)
                    lTail = curr;
                else {
                    lTail.next = curr;
                    lTail = curr;
                }
            }
            else {
                if(rHead == null)
                    rHead = curr;

                if(rTail == null)
                    rTail = curr;
                else {
                    rTail.next = curr;
                    rTail = curr;
                }
            }

            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }

        if(lHead == null)
            return rHead;

        lTail.next = rHead;
        return lHead;
    }
}
