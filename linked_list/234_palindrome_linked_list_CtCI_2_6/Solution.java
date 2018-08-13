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
}
