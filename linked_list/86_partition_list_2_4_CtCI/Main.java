public class Main {

    public static void main(String[] args) {
        ListNode t1 = new ListNode(1);
        ListNode t2 = new ListNode(4);
        ListNode t3 = new ListNode(3);
        ListNode t4 = new ListNode(2);
        ListNode t5 = new ListNode(5);
        ListNode t6 = new ListNode(2);

        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        t4.next = t5;
        t5.next = t6;

        Solution2 s = new Solution2();
        ListNode res = s.partition(t1, 3);

        ListNode curr = res;
        while (curr != null){
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
