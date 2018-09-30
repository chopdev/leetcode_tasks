public class Main {

    public static void main(String[] args) {

        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);

        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        headA.next.next = l3;

        ListNode headB = new ListNode(10);
        headB.next = new ListNode(11);
        headB.next.next = new ListNode(12);
        headB.next.next.next = l3;

        Solution s = new Solution();
        ListNode res1 = s.getIntersectionNode(headA, headB);
        ListNode res2 = s.getIntersectionNode(l3, headB);

    }
}
