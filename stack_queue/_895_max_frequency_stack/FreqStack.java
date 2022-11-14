/**
 * 895. Maximum Frequency Stack
 * https://leetcode.com/problems/maximum-frequency-stack/
 *
 *
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 *
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 *
 *
 * Example 1:
 *
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 *
 * Explanation
 * FreqStack freqStack = new FreqStack();
 * freqStack.push(5); // The stack is [5]
 * freqStack.push(7); // The stack is [5,7]
 * freqStack.push(5); // The stack is [5,7,5]
 * freqStack.push(7); // The stack is [5,7,5,7]
 * freqStack.push(4); // The stack is [5,7,5,7,4]
 * freqStack.push(5); // The stack is [5,7,5,7,4,5]
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 * freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
 * freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
 *
 *
 * Constraints:
 *
 * 0 <= val <= 109
 * At most 2 * 104 calls will be made to push and pop.
 * It is guaranteed that there will be at least one element in the stack before calling pop.
 *
 * */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * My solution, Time Limit Exceeded
 *
 * in worst case O(N) for pop and push operations
 *
 * the idea is to use similar to LRU cache data structure with linked list of nodes + hashmap. Each node contains count of entrances,
 * nodes are sorted by the count and index of the last element.
 * */
class FreqStack {

    class Node {
        int val;

        Stack<Long> indexStack;

        Node next;
        Node prev;

        public Node(int val) { // for tail and head
            this.val = val;
        }

        public Node(int val, long index) {
            this.val = val;
            indexStack = new Stack<>();
            indexStack.push(index);
        }
    }

    private long index = 0;
    private Node head = new Node(-10);
    private Node tail = new Node(-1);

    private Map<Integer, Node> valToNode = new HashMap<>();

    public FreqStack() {
        head.next = tail;
        tail.prev = head;
    }

    public void push(int val) {
        Node node;
        if (valToNode.containsKey(val)) {
            node = valToNode.get(val);
            node.indexStack.push(index ++);
        } else {
            node = new Node(val, index ++);
            valToNode.put(val, node);

            Node temp = tail.prev;
            temp.next = node;
            node.prev = temp;
            node.next = tail;
            tail.prev = node;
        }

        while (node.prev != head && node.indexStack.size() >= node.prev.indexStack.size()) {
            swap(node.prev, node);
        }

        //printStack();
    }

    public int pop() {
        if (head.next == tail) return -1; // empty stack

        Node node = head.next;
        node.indexStack.pop(); // remove top index element

        if (node.indexStack.size() == 0) { // no more values
            valToNode.remove(node.val);

            node.next.prev = head;
            head.next = node.next;
            node.next = null;
            node.prev = null;
        } else {
            while (node.next != tail &&
                    (node.indexStack.size() < node.next.indexStack.size() ||
                            (node.indexStack.size() == node.next.indexStack.size() &&
                            node.indexStack.peek() < node.next.indexStack.peek()))) {
                swap(node, node.next);
            }
        }

        //printStack();
        return node.val;
    }

    private void swap(Node left, Node right) {
        //  t1 <-> left <-> right <-> t2
        right.prev = left.prev;
        right.prev.next = right;  // change next reference for t1

        left.next = right.next;
        left.next.prev = left; // change prev reference for t2

        right.next = left;
        left.prev = right;
    }

    private void printStack() {
        Node node = head.next;
        StringBuilder sb = new StringBuilder();
        while (node != tail) {
            sb.append(node.val + " (index:" + node.indexStack.peek() + ", count: " + node.indexStack.size() + ")");
            sb.append(" -> ");
            node = node.next;
        }

        System.out.println(sb);
    }
}
