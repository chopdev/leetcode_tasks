import java.util.Stack;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * Input: 1->2
 * Output: false
 *
 *
 * Input: 1->2->2->1
 * Output: true
 *
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *
 * Good solution
 * https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
 * */
public class Solution {

    // Mine solution, time O(N) , Space O(N/2)=O(N)
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        if(head.next.next == null) return  head.val == head.next.val;

        ListNode slow = head, fast = head.next;
        Stack<Integer> st = new Stack<>();
        boolean odd = false;
        while (fast.next != null) {
            st.push(slow.val);
            slow = slow.next;
            if(fast.next != null && fast.next.next != null) fast = fast.next.next;
            else {
                fast = fast.next;
                odd = true;
            }
        }

        if(!odd) st.add(slow.val);
        while (slow.next!=null){
            slow = slow.next;
            int val = st.pop();
            if(slow.val != val)
                return false;
        }

        return true;
    }

    // Mine solution, time O(N) , Space O(1) - but we modify initial list
    // We reverse left part from the middle and compare left and right parts of list
    public boolean isPalindrome2222(ListNode head) {
        if(head == null || head.next == null) return true;
        if(head.next.next == null) return  head.val == head.next.val;

        ListNode slow = head, fast = head.next.next, slowNext = null, prev = null;
        int count = 3;
        while (fast != null) {
            slowNext = slow.next;
            slow.next = prev;
            prev = slow;
            slow = slowNext;

            if(fast.next != null && fast.next.next != null) {
                count +=2;
                fast = fast.next.next;
            }
            else {
                count ++;
                fast = fast.next;

            }
        }

        if(count % 2 == 0) slow = slow.next;
        while (slow != null){
            if(slow.val != prev.val)
                return false;

            slow = slow.next;
            prev = prev.next;
        }

        return true;
    }



    ///
    // Not mine solution, it is shorter cause we reverse right part, O(N) , Space O(1) also modify list
    //https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
    public boolean isPalindrome3333(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
