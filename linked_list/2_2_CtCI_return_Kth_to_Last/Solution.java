/**
 * Problem: Implement an algorithm to find the kth to last element of a singly
 * linked list. 1 -> 2 -> 3 3th to last node is 1
 *
 */
public class Solution {

    // Mine iterative solution, Time complexity O(N), Space O(1)
    public ListNode getKthToLast(ListNode head, int K){
        if(head == null || K < 0) return null;

        ListNode curr = head;
        int count = 1;
        while (curr.next != null) {
            count ++;
            curr = curr.next;
        }

        if(K >= count) return null;

        int till = count - K;
        curr = head;
        for (int i = 1; i < till; i++)
            curr = curr.next;

        return curr;
    }


    // Mine recursive solution, Time Complexity O(2N)=O(N),
    // /Space: O(N) - lenght of recursion, on each level we preserve state (like list node)
    public ListNode getKthToLastRecursive(ListNode head, int K){
        if(head == null || K < 0) return null;

        Item temp = getKthToLastInternal(head, K);

        if(temp.number < K) return null;

        return temp.node;
    }

    private Item getKthToLastInternal(ListNode n, int K) {
        if(n.next == null)
            return new Item(n, 0);

        Item next = getKthToLastInternal(n.next, K);
        if(next.number < K)
            return new Item(n, next.number + 1);
        else
            return next;
    }
	
	
	/**
	 * mehtod 3: iteration Time: O(N), Space O(1)
	 * Not mine, but easy and handsome
	 */
	public ListNode kthToLast3(ListNode node, int k) {
		ListNode left = node;
		ListNode right = node;
		for (int i = 0; i < k; i++) {
			if (right == null) 
				return null;

			right = right.next;
		}
		while (right != null) {
			right = right.next;
			left = left.next;
		}
		return left;
	}
}

class Item {
    ListNode node;
    int number;

    public Item(ListNode node, int number){
        this.node = node;
        this.number = number;
    }
}
