/**
 142. Linked List Cycle II
 https://leetcode.com/problems/linked-list-cycle-ii/

 Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

 To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
 in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 Note: Do not modify the linked list.

 Example 1:

 Input: head = [3,2,0,-4], pos = 1
 Output: tail connects to node index 1
 Explanation: There is a cycle in the linked list, where tail connects to the second node.


 Example 2:

 Input: head = [1,2], pos = 0
 Output: tail connects to node index 0
 Explanation: There is a cycle in the linked list, where tail connects to the first node.


 Example 3:

 Input: head = [1], pos = -1
 Output: no cycle
 Explanation: There is no cycle in the linked list.


 QUESTIONS
 1) could we modigy the list
 2) beginning of the loop is the closest to the head node?

* */
public class Solution {


    // My O(N^2) solution
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return null;
        ListNode slow = head.next, fast = head.next.next;

        while (slow != null && fast != null && slow != fast) {
            slow = slow.next;
            if(fast.next != null) fast = fast.next.next;
            else fast = null;
        }

        if(slow == null || fast == null) return null;
        slow = slow.next;
        while(slow != head) {
            while (slow != fast) {
                slow = slow.next;
                if(slow == head) return head;
            }
            slow = slow.next;
            head = head.next;
        }

        return head;
    }


    // Not mine solution O(N) based on math
    // https://www.youtube.com/watch?v=LUm2ABqAs1w
    //Let the distance from the first node to the the node where cycle begins be A, and let say the slow pointer
    // travels travels A+B. The fast pointer must travel 2A+2B to catch up. The cycle size is N.
    // Full cycle is also how much more fast pointer has traveled than slow pointer at meeting point.
    // https://leetcode.com/problems/linked-list-cycle-ii/discuss/44774/Java-O(1)-space-solution-with-detailed-explanation.
    // https://leetcode.com/problems/linked-list-cycle-ii/discuss/44793/O(n)-solution-by-using-two-pointers-without-change-anything
    public ListNode detectCycle2222(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow){
                ListNode slow2 = head;
                while (slow2 != slow){
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}
