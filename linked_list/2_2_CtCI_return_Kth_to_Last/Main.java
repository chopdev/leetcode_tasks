public class Main {

    public static void main(String[] args) {
	    ListNode l7 = new ListNode(20, null);
        ListNode l6 = new ListNode(11, l7); //1
        ListNode l5 = new ListNode(4, l6); // 2
        ListNode l4 = new ListNode(1, l5); // 3
        ListNode l3 = new ListNode(10, l4); // 4
        ListNode l2 = new ListNode(6, l3); // 5
        ListNode l1 = new ListNode(5, l2); // 6

        Solution s = new Solution();

        ListNode res1 = s.getKthToLast(l1, 4);
        ListNode res2 = s.getKthToLast(l1, 1);
        ListNode res3 = s.getKthToLast(l1, 6);
        ListNode res4 = s.getKthToLast(l1, 7);
        ListNode res5 = s.getKthToLast(l1, 0);

        System.out.println(res1 == null ? null : res1.value); // 10
        System.out.println(res2 == null ? null : res2.value); // 11
        System.out.println(res3 == null ? null : res3.value); // 5
        System.out.println(res4 == null ? null : res4.value); // null
        System.out.println(res5 == null ? null : res5.value); // 20
    }
}
