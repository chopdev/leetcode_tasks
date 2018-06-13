import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode s1 = new ListNode(1);
        ListNode s2 = new ListNode(1);
        ListNode s3 = new ListNode(2);
        ListNode s4 = new ListNode(4);
        s1.next = new ListNode(1);
        s1.next.next = s2;
        s2.next = s3;
        s3.next = s4;
        s4.next = new ListNode(4);

        ListNode result = s.deleteDuplicates2222(s1);
        System.out.println(printList(result));


        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(2);
        l1.next.next.next.next = new ListNode(3);

        result = s.deleteDuplicates2222(l1);
        System.out.println(printList(result));

        ListNode k1 = new ListNode(1);
        k1.next = new ListNode(2);
        k1.next.next = new ListNode(2);
        k1.next.next.next = new ListNode(3);
        k1.next.next.next.next = new ListNode(3);

        result = s.deleteDuplicates2222(k1);
        System.out.println(printList(result));
    }

    private static String printList(ListNode node) {
        StringBuilder b = new StringBuilder();
        //b.append(node.val).append("->");
        ListNode t = node;
        while (t != null) {
            b.append(t.val).append("->");
            t = t.next;
        }

        return b.toString();
    }
}
