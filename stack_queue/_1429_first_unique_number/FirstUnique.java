import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 1429. First Unique Number
 *
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 *
 * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 * int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
 * void add(int value) insert value to the queue.
 *
 *
 *  Example 1:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
 * [[[2,3,5]],[],[5],[],[2],[],[3],[]]
 * Output:
 * [null,2,null,2,null,3,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([2,3,5]);
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(5);            // the queue is now [2,3,5,5]
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(2);            // the queue is now [2,3,5,5,2]
 * firstUnique.showFirstUnique(); // return 3
 * firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
 * firstUnique.showFirstUnique(); // return -1
 *
 *
 * Example 2:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
 * [[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
 * Output:
 * [null,-1,null,null,null,null,null,17]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
 * firstUnique.showFirstUnique(); // return -1
 * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
 * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
 * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
 * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
 * firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
 * firstUnique.showFirstUnique(); // return 17
 *
 *
 * Example 3:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","showFirstUnique"]
 * [[[809]],[],[809],[]]
 * Output:
 * [null,809,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([809]);
 * firstUnique.showFirstUnique(); // return 809
 * firstUnique.add(809);          // the queue is now [809,809]
 * firstUnique.showFirstUnique(); // return -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^8
 * 1 <= value <= 10^8
 * At most 50000 calls will be made to showFirstUnique and add.
 * */


/**
* My solution
 *
 * Time:
 * O(M) constructor, where M is nums.length
 * O(1) showFirstUnique() and add()
 *
 * Space: O(N) amortized where N is total count of elements (repeated nums are not duplicated in memory)
 *
 *  The idea is similar to LRU cache HashMap with references on nodes in linked list. Plus added HashSet for memorizing of repeated elements
* */
public class FirstUnique {

    class Node {
        public Node(int val) {
            this.val = val;
        }
        int val;
        Node next;
        Node prev;
    }

    private Node head = new Node(-1);
    private Node tail = new Node(-1);

    Map<Integer, Node> numToNode = new HashMap<>();
    HashSet<Integer> nonUniqueNums = new HashSet<>();

    public FirstUnique(int[] nums) {
        head.next = tail;
        tail.prev = head;
        for (int num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        return head.next.val;
    }

    public void add(int value) {
        if (nonUniqueNums.contains(value)) return;
        if (numToNode.containsKey(value)) {
            nonUniqueNums.add(value);
            removeNode(value);
        } else {
            addNode(value);
        }
    }

    private void addNode(int val) {
        Node newNode = new Node(val);
        Node temp = tail.prev;
        tail.prev = newNode;
        temp.next = newNode;
        newNode.next = tail;
        newNode.prev = temp;
        numToNode.put(val, newNode);
    }

    private void removeNode(int val) {
        Node currNode = numToNode.get(val);
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;
        numToNode.remove(val);
    }
}
