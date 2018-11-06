/**
 https://leetcode.com/problems/sort-list/description/
 148. Sort List
 Sort a linked list in O(n log n) time using constant space complexity.

 Example 1:

 Input: 4->2->1->3
 Output: 1->2->3->4
 Example 2:

 Input: -1->5->3->4->0
 Output: -1->0->3->4->5


    SOLUTIONS:
 with using of recursion
 https://leetcode.com/problems/sort-list/discuss/46714/Java-merge-sort-solution

 O(1) space
 https://leetcode.com/problems/sort-list/discuss/46712/Bottom-to-up(not-recurring)-with-o(1)-space-complextity-and-o(nlgn)-time-complextity?page=2

 */
public class Solution {

    // Mine solution O(N*logN) time, O(N) space
    // Worked from the first time
    // https://stackoverflow.com/questions/10342890/merge-sort-time-and-space-complexity
    public ListNode sortList(ListNode head) {
        if(head == null) return null;

        // count lenght of the list
        int count = 0;
        ListNode tmp = head;
        while (tmp != null) {
            count ++;
            tmp = tmp.next;
        }

        return mergeSort(head, count);
    }

    private ListNode mergeSort(ListNode head, int length) {
        if(length == 1) return head;

        ListNode tmp = head;
        for (int i = 0; i < length / 2 - 1; i++) { // go to pre-middle element
            tmp = tmp.next;
        }

        ListNode secondHalf = tmp.next; // assign middle element
        tmp.next = null;                // unbind 2 parts of list

        ListNode h1 = mergeSort(head, length / 2);      // recursively divide list till it length is 1
        ListNode h2 = mergeSort(secondHalf, length - length / 2);

        return merge(h1, h2);
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;

        while (h1 != null && h2 != null) {
            if(h1.val < h2.val){
                tmp.next = h1;
                h1 = h1.next;
            }
            else {
                tmp.next = h2;
                h2 = h2.next;
            }

            tmp = tmp.next;
            tmp.next = null;
        }

        if(h1 != null)
            tmp.next = h1;
        else
            tmp.next = h2;

        return head.next;
    }



    /**    Not mine solution
     *  Bottom-up solution
     *  https://leetcode.com/problems/sort-list/discuss/46712/Bottom-to-up(not-recurring)-with-o(1)-space-complextity-and-o(nlgn)-time-complextity?page=2
     * */
    public ListNode sortList2222(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode cur = head;
        int lenght = 0;
        while (cur != null) {
            lenght ++;
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left, right, tail;
        for (int step = 1; step < lenght; step *= 2) {
            cur = dummy.next;
            tail = dummy;
            while (cur != null) {
                left = cur;
                right = split(left, step);
                cur = split(right,step);
                tail = merge2222(left, right, tail);
            }
        }

        return dummy.next;
    }

    private ListNode split(ListNode head, int n) {
        for (int i = 1; head != null && i < n; i++) head = head.next;

        if(head == null) return null;
        ListNode second = head.next;
        head.next = null;
        return second;
    }

    private ListNode merge2222(ListNode left, ListNode right, ListNode head) {
        ListNode curr = head;
        while (left != null && right != null) {
            if(left.val > right.val) {
                curr.next = right;
                curr = right;
                right = right.next;
            }
            else {
                curr.next = left;
                curr = left;
                left = left.next;
            }
        }

        curr.next = right != null ? right : left;
        while (curr.next != null) curr = curr.next;
        return curr;
    }

}
