import java.util.HashSet;

/*
* Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3

begin to intersect at node c1.

Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.


EXPLANATION
https://leetcode.com/articles/intersection-of-two-linked-lists/
https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!

* */

public class Solution {

    // Mine solution
    // Time O(N+M), space O(N)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        HashSet<ListNode> hashSet = new HashSet<>();
        while (headA != null) {
            hashSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if(hashSet.contains(headB)) {
                return headB;
            }

            headB = headB.next;
        }

        return null;
    }

    // Not mine solution
    // Another way is to check length of two lists and calculate diff in lenght. Then make diff steps in longer lists and then make steps in both lists untill find equal node
    public ListNode getIntersectionNode2222(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        // If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }
}
