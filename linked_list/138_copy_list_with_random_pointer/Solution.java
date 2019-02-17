import java.util.HashMap;

/**
 138. Copy List with Random Pointer
 https://leetcode.com/problems/copy-list-with-random-pointer/

 A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.

* */
public class Solution {

    // My solution
    // O(N) time, O(N) space
    // use hashtable to map nodes of old list to new list
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode oldHead = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode curr = dummy;
        HashMap<RandomListNode, RandomListNode> oldToNew = new HashMap<>();

        while(head != null) {
            curr.next = new RandomListNode(head.label);
            oldToNew.put(head, curr.next);
            curr = curr.next;
            head = head.next;
        }

        head = oldHead;
        curr = dummy.next;

        while(head != null) {
            if(head.random != null)
                curr.random = oldToNew.get(head.random);
            curr = curr.next;
            head = head.next;
        }

        return dummy.next;
    }

    // Not mine solution
    // O(N) time, O(1) space
    //https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)
    // The algorithm is composed of the follow three steps which are also 3 iteration rounds.
    // 1) Iterate the original list and duplicate each node. The duplicate of each node follows its original immediately.
    // 2) Iterate the new list and assign the random pointer for each duplicated node.
    // 3) Restore the original list and extract the duplicated nodes.
    public RandomListNode copyRandomList2222(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }
}

