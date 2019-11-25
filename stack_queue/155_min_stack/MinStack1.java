/**
 155. Min Stack
 https://leetcode.com/problems/min-stack/

 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.

 Example:

 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.


 QUESTIONS:
 getMin should delete element from the dara structure?
 if it deletes, what if we have multiple same values (which one to delete)?
 -1 -1 1 -1

 * */


// My solution
// pop - O(N), others O(1)
public class MinStack1 {
    int min = Integer.MAX_VALUE;
    Node top = null;

    /** initialize your data structure here. */
    public MinStack1() {

    }

    public void push(int x) {
        if(top == null)
            top = new Node(x, null);
        else
            top = new Node(x, top);

        if(x < min)
            min = x;
    }

    public void pop() {
        if(top == null) return;

        if(top.val == min) { // search new min
            min = Integer.MAX_VALUE;
            Node tmp = top.next;
            while (tmp != null) {
                min = Math.min(min, tmp.val);
                tmp = tmp.next;
            }
        }

        top = top.next;  // remove top
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return min;
    }

    private class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
