import java.util.Stack;

/**
 * Mine solution. Space O(N) - because the length of recursion is N, Time O(N) - N constant iterations in recursion
 *
 */
public class MyQueue {

    private Stack<Integer> s;

    public MyQueue() {
        s = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s.add(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(s.isEmpty()) return 0;

        return popInternal(false);
    }

    /** Get the front element. */
    public int peek() {
        if(s.isEmpty()) return 0;

        return popInternal(true);
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s.isEmpty();
    }

    private int popInternal(boolean peek){
        int curr = s.pop(); // pop top element

        if(s.isEmpty()) {  // if it is the last one - it is the element we should return from queue
            if(peek) s.push(curr);
            return curr;
        }

        // if we are here, it is not the last element
        int res = popInternal(peek);  // go deeper, and get result
        s.push(curr); // push our current element back!
        return res;
    }
}