public class Main {

    public static void main(String[] args) {
        ListNode t1 = new ListNode(5);
        ListNode t2 = new ListNode(6);
        ListNode t3 = new ListNode(1);
        ListNode t4 = new ListNode(2);
        ListNode t5 = new ListNode(1);
        ListNode t6 = new ListNode(6);
        ListNode t7 = new ListNode(5);

        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        t4.next = t5;
        t5.next = t6;
        t6.next = t7;

	    Solution s = new Solution();
	    boolean res1 = s.isPalindrome(t1);

        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(0);
        ListNode r3 = new ListNode(1);
        ListNode r4 = new ListNode(1);

        r1.next = r2;
        r2.next = r3;
        r3.next = r4;

        boolean res2 = s.isPalindrome(r1);

	    System.out.println(res1);
        System.out.println(res2);
    }
}
