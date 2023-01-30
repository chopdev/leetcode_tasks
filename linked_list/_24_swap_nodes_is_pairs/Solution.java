/**
 * 24. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 * */
public class Solution {
     class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * My solution.
     * The idea is to swap current two nodes. Then recursively swap others. The result of recursion is a new next for current pair.
     * e.g.
     * 1 -> 2 -> 3 -> 4
     * We swap 1 -> 2 to 2 -> 1 , but 1 is still referencing 3 which should be 4 eventually:
     * 2 -> 1 -> 3 -> 4
     * After recursion is completed
     * 2 -> 1 -> 4 -> 3
     *
     * O(N) time and space
     * */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode first = head;
        ListNode second = head.next;

        first.next = second.next;
        second.next = first;

        ListNode third = swapPairs(first.next);
        first.next = third;

        return second;
    }
}
