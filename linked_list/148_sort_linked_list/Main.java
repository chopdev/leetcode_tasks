public class Main {

    public static void main(String[] args) {
	    ListNode t1 = new ListNode(4);
        ListNode t2 = new ListNode(2);
        ListNode t3 = new ListNode(1);
        ListNode t4 = new ListNode(3);
        t1.next = t2;
        t2.next = t3;
        t3.next = t4;

        Solution solution = new Solution();
        ListNode res1 = solution.sortList2222(t1);
    }
}
