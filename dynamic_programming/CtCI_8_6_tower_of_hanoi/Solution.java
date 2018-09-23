import java.util.Stack;

/*
* Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order
of size from top to bottom (Le., each disk sits on top of an even larger one). You have the following
constraints:
(1) Only one disk can be moved at a time.
(2) A disk is slid off the top of one tower onto another tower.
(3) A disk cannot be placed on top of a smaller disk.
Write a program to move the disks from the first tower to the last using stacks.
* */

public class Solution {
    // Not mine solution
    // Time O(2^n), space O(N) - deepness of recursion
    public void moveToThird(Stack<Integer> first, Stack<Integer> second, Stack<Integer> third) throws Exception {
        if(first == null || second == null || third == null) return;
        moveToThird(first.size(), first, second, third);
    }

    private void moveToThird(int n, Stack<Integer> first, Stack<Integer> second, Stack<Integer> third) throws Exception {
        if(n <= 0) return;

        moveToThird(n-1, first, third, second); // move n - 1 item to second tower, using third as a buffer
        // carry item to destination
        int item = first.pop();
        if(!third.empty() && item > third.peek())
            throw new Exception("Hanoi exception");

        third.push(item);

        moveToThird(n-1, second, first, third);  // move n - 1 item from second tower to third using 1 tower as a buffer
    }
}
