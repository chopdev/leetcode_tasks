public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    ListNode first = new ListNode(1);
	    first.next =  new ListNode(4);
	    first.next.next = new ListNode(5);

        ListNode second = new ListNode(1);
        second.next =  new ListNode(3);
        second.next.next = new ListNode(4);

        ListNode third = new ListNode(2);
        third.next =  new ListNode(6);

	    ListNode[] arr = new ListNode[3];
	    arr[0] = first;
	    arr[1] = second;
	    arr[2] = third;

	    ListNode res = sol.mergeKLists3333(arr);
    }
}
