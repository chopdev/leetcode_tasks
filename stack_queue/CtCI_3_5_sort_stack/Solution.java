import java.util.Stack;


/**
 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty
 * */
public class Solution {

    private Stack<Integer> s2;
    private int curr;

    //*
    // Mine solution. Time O(N^2)
    // */
    public void sort(Stack<Integer> s1) {
        if(s1 == null) return;
        curr = 0;
        s2 = new Stack<Integer>();

        while (!s1.isEmpty()) {
            if(s2.isEmpty() || s2.peek() <= s1.peek())
                s2.push(s1.pop());
            else {
                curr = s1.pop();
                while (!s2.isEmpty() && s2.peek() > curr)
                    s1.push(s2.pop());

                s2.push(curr);
            }
        }

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
}
