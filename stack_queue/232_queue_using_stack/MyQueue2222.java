import java.util.Stack;

/**
 * Not mine solution. I had an idea to push all element to 2 stack, pop and then back to first stack
 * But here solution is better, there are no reasons to push items from 1 to 2 stack on each read
 * we just checking if the "output stack" is empty or not
 * */
class MyQueue2222 {

    Stack<Integer> input = new Stack();
    Stack<Integer> output = new Stack();

    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return output.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return input.empty() && output.empty();
    }
}
