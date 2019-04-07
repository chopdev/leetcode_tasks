import java.util.PriorityQueue;

/**
 23. Merge k Sorted Lists
https://leetcode.com/problems/merge-k-sorted-lists/

 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 Example:

 Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]

 Output: 1->1->2->3->4->4->5->6

 SOLUTION: https://leetcode.com/articles/merge-k-sorted-list/
* */
public class Solution {
    // Mine straightforward solution
    // time O(L*M), where L - count of lists, M - the length of the longest list
    // approach 2 from solutions
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode last = null;
        while (true) {
            int nextInd = -1;
            for (int i = 0; i < lists.length; i++) {
                if(lists[i] == null) continue;
                if(nextInd < 0) nextInd = i;
                else nextInd = lists[nextInd].val <= lists[i].val ? nextInd : i;
            }

            if(nextInd == -1) break;

            if(dummy.next == null) {
                dummy.next = lists[nextInd];
                last = dummy.next;
            }
            else {
                last.next = lists[nextInd];
                last = last.next;
            }

            lists[nextInd] = lists[nextInd].next;
        }

        return dummy.next;
    }


    // Mine solution (didn't figured out to use PriorityQueue from the beginning)
    // time O(L + C*log(L))=O(C*log(L)) - L - number of lists, C - count of all nodes, logL - for priority queue operations
    // C>=L, because even if each list will have one item C==L
    // Space complexity O(L) - priority Q
    // approach 3 from solutions
    public ListNode mergeKLists2222(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.val, o2.val));

        // add first L list nodes
        for (int i = 0; i < lists.length; i++) {
            if(lists[i] != null) minHeap.offer(lists[i]);
        }

        ListNode dummy = new ListNode(0);
        ListNode last = dummy;  // cool trick to point on dummy (compare with previous approach)
        while (!minHeap.isEmpty()) {
            ListNode curr = minHeap.poll();
            if(curr.next != null) minHeap.offer(curr.next);

            last.next = curr;
            last = last.next;
        }

        return dummy.next;
    }



    // Not mine solution
    //time O(C*log(L)) - L - number of lists, C - count of all nodes, the same as with priority Q
    // Space complexity O(logL) - deepness of recursion
    // https://leetcode.com/problems/merge-k-sorted-lists/discuss/10522/My-simple-java-Solution-use-recursion
    public ListNode mergeKLists3333(ListNode[] lists) {
        return partition(lists, 0, lists.length - 1);
    }

    // technique with partition similar to quick sort
    private ListNode partition(ListNode[] lists, int begin, int end) {
        if(begin > end) return null;
        if(begin == end) return lists[begin];

        int mid = (begin + end) / 2;
        ListNode left = partition(lists, begin, mid);
        ListNode right = partition(lists, mid + 1, end);
        return merge(left, right);
    }

    private ListNode merge(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(0);
        ListNode last = dummy;

        while (first != null && second != null) {
            if(first.val <= second.val) {
                last.next = first;
                first = first.next;
            }
            else {
                last.next = second;
                second = second.next;
            }
            last = last.next;
        }

        if(first != null) last.next = first;
        if(second != null) last.next = second;

        return dummy.next;
    }
}
