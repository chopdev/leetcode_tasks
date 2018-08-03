public class Main {

    public static void main(String[] args) {
	    ListNode t1 = new ListNode(4);
        ListNode t2 = new ListNode(5);
        ListNode t3 = new ListNode(1);
        ListNode t4 = new ListNode(9);

        t1.next = t2;
        t2.next = t3;
        t3.next = t4;

        Solution s = new Solution();
        s.deleteNode(t2);

        ListNode curr = t1;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
