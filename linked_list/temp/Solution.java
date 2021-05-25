import java.util.Random;


/* 
    391 
*/
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

 // My not working solution
 // Reservior sampling https://www.youtube.com/watch?v=A1iwzSew5QY
class Solution {

    ListNode head;
    ListNode curr;
    Random r = new Random();

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        curr = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        boolean shouldReturn = toReturn();
        while (!shouldReturn) {
            if (curr.next == null)
                curr = head;
            else
                curr = curr.next;

            shouldReturn = toReturn();
        }
        return curr.val;
    }

    private boolean toReturn() {
        return r.nextBoolean();
    }  
}