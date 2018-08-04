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
        ListNode lHead = null, lTail = null, rHead = null, rTail = null, curr = head; // define left head and tails queues, the same for right
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


    // The idea is the same, but author uses dummy left head and right head List nodes
    //https://leetcode.com/problems/partition-list/discuss/29183/Concise-java-code-with-explanation-one-pass
    //the basic idea is to maintain two queues, the first one stores all nodes with val less than x , and the second queue stores all the rest nodes. Then concat these two queues. Remember to set the tail of second queue a null next, or u will get TLE.
    public ListNode partition2222(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head!=null){
            if (head.val<x) {
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}
