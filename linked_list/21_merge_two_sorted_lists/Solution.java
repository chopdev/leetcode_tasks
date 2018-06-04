import java.lang.*;

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(val).append("->");
        ListNode t = next;
        while (t != null) {
            b.append(t.val).append("->");
            t = t.next;
        }

        return b.toString();
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null && l2 == null)
            return  null;
        else if(l1 == null)
            return l2;
        else  if(l2 == null)
            return l1;

        ListNode res, temp;
        res = new ListNode(0);
        res.next = temp = new ListNode(0);

        if (l1.val <= l2.val) {
            res.val = l1.val;
            l1 = l1.next;
        } else {
            res.val = l2.val;
            l2 = l2.next;
        }

        while (true) {
            int val1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int val2 = l2 == null ? Integer.MAX_VALUE : l2.val;

            if (val1 <= val2) {
                temp.val = val1;
                l1 = l1.next;
            } else {
                temp.val = val2;
                l2 = l2.next;
            }

            if (l1 == null && l2 == null)
                break;

            temp.next = new ListNode(0);
            temp = temp.next;
        }

        return res;
    }
}