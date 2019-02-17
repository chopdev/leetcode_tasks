public class Main {

    public static void main(String[] args) {
        RandomListNode r1 = new RandomListNode(-1);
        RandomListNode r2 = new RandomListNode(2);
        r1.next = r2;
	    Solution sol = new Solution();
        RandomListNode copy  = sol.copyRandomList(r1);
    }
}
