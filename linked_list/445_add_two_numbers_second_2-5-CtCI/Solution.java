import java.util.Stack;

/**
 You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:
 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7
 */
public class Solution {

    /**
     * Mine recursive solution. The idea is to add zeros to shorter number, like 84 + 09,
     * and then recursively add numbers beginning from the end
     * Space: O(max(M, N) + 1 + (M +N) )=O(max(M, N) + M + N)   - (M+N) - for the each level of recursion, max(M, N)+1 - length of the new list
     * Time: O(2max(M, N))=O(max(M, N))
     * */
    public ListNode addTwoNumbers(ListNode t1, ListNode t2) {
        if(t1 == null || t2 == null) return null;

        ListNode dummy = new ListNode(0), curr1 = t1, curr2 = t2;
        ListNode dHead = dummy;
        boolean firstShorter = false;

        while (curr1 != null || curr2 != null){
            if(curr1 == null || curr2 == null){
                dummy.next = new ListNode(0);
                dummy = dummy.next;
                if(curr1 == null) firstShorter = true;
            }

            if(curr1 != null) curr1 = curr1.next;
            if(curr2 != null) curr2 = curr2.next;
        }

        if(firstShorter && dHead.next != null) {
            dummy.next = t1;
            t1 = dHead.next;
        }
        else if(dHead.next != null){
            dummy.next = t2;
            t2 = dHead.next;
        }

        return addRecursive(t1, t2);
    }

    private ListNode addRecursive(ListNode t1, ListNode t2){
        ListNode sumNode = addRecursiveInternal(t1, t2);
        ListNode head = sumNode;
        if(sumNode.val >= 10){
            head = new ListNode(sumNode.val / 10);
            sumNode.val %= 10;
            head.next = sumNode;
        }
        return head;
    }

    private ListNode addRecursiveInternal(ListNode t1, ListNode t2){
        if(t1.next == null && t2.next == null){
            return new ListNode(t1.val + t2.val);
        }

        ListNode nextNode = addRecursiveInternal(t1.next, t2.next);
        int rest = nextNode.val / 10;
        nextNode.val %= 10;
        ListNode sumNode = new ListNode(rest + t1.val + t2.val);
        sumNode.next = nextNode;
        return sumNode;
    }



    /**
     * Not mine solution. Cool solution using stack
     * Space: O(M+N+max(M, N)) - two stacks + result list
     * Time: O(max(M, N) + M + N)
     * */
    public ListNode addTwoNumbers2222(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();

        for(; l1 != null; l1 = l1.next)
            s1.push(l1.val);

        for(; l2 != null; l2 = l2.next)
            s2.push(l2.val);

        ListNode res = new ListNode(0);
        int carry = 0;
        while(!s1.empty() || !s2.empty() || carry != 0) {
            ListNode cur = new ListNode(carry);
            if(!s1.empty()) cur.val += s1.pop();
            if(!s2.empty()) cur.val += s2.pop();
            carry = cur.val / 10;
            cur.val %= 10;
            cur.next = res.next;
            res.next = cur;
        }

        return res.next;
    }
}

