public class Main {

    public static void main(String[] args) {
        ListNode t1 = new ListNode(2);
        ListNode t2 = new ListNode(4);
        ListNode t3 = new ListNode(3);
        t1.next = t2;
        t2.next = t3;

        ListNode r1 = new ListNode(5);
        ListNode r2 = new ListNode(6);
        ListNode r3 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;

        ListNode k1 = new ListNode(9);

        Solution s = new Solution();
        ListNode res = s.addTwoNumbers(t1, k1);  // 243 + 9 = 252

        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
