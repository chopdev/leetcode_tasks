import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    // [3,2,0,-4]
        //1

        ListNode head = new ListNode(3);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(0);
        ListNode head3 = new ListNode(-4);

        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head1;

	    ListNode res = sol.detectCycle(head);
    }
}
