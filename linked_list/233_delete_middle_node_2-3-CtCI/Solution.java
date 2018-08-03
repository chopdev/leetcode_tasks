/**
     Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
     the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
     that node.
     EXAMPLE
     Input: the node c from the linked list a - >b- >c - >d - >e- >f
     Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f
 */
public class Solution {

    // Mine solution, just replace the value and change next pointer
    // Time complexity O(1), Space O(1)
    public void deleteNode(ListNode node) {
        if(node.next == null) return;;

        node.val = node.next.val;
        if(node.next.next != null)
            node.next = node.next.next;
        else
            node.next = null;
    }
}
