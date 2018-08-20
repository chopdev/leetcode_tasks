/*
Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
steps at a time. Implement a method to count how many possible ways the child can run up the
stairs.

Explanation page 342
*/
public class Solution {
    // Mine solution recursive complexity O(3^N)
    /*
    NOTE
    Regardless of whether or not you use memoization, note that the number of ways will quickly overflow the
bounds of an integer. By the time you get to just n = 37, the result has already overflowed. Using a long
will delay, but not completely solve, this issue.*/
    public int count(int stair) {
        if(stair <= 0) return 0;
        if(stair == 1) return 1;
        if(stair == 2) return 2;
        if(stair == 3) return 4;

        return count(stair - 1) + count(stair - 2) + count(stair - 3);
    }

    // Mine solution, complexity O(N)
    public int countDynamic(int stair) {
        int count = stair < 3 ? 3 : stair;
        int[] arr = new int[count + 1];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for (int i = 4; i <= stair; i++) {
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
        }

        return arr[stair];
    }
}
