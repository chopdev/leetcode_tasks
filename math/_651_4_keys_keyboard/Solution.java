/***
 651. 4 Keys Keyboard

 https://leetcode.com/problems/4-keys-keyboard/?envType=study-plan-v2&envId=premium-algo-100

 Imagine you have a special keyboard with the following keys:

 A: Print one 'A' on the screen.
 Ctrl-A: Select the whole screen.
 Ctrl-C: Copy selection to buffer.
 Ctrl-V: Print buffer on screen appending it after what has already been printed.
 Given an integer n, return the maximum number of 'A' you can print on the screen with at most n presses on the keys.


 Example 1:

 Input: n = 3
 Output: 3
 Explanation: We can at most get 3 A's on screen by pressing the following key sequence:
 A, A, A


 Example 2:

 Input: n = 7
 Output: 9
 Explanation: We can at most get 9 A's on screen by pressing following key sequence:
 A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V


 Constraints:

 1 <= n <= 50

 */
public class Solution {

    /**
     * My partially working solution
     * */
    public int maxA(int n) {
        if (n <= 6) return n;

        int max = n;
        for (int i = 3; i <= 6; i++) {
            int possible = getMax(i, n);
            max = Math.max(max, possible);
        }

        return max;
    }

    private int getMax(int initialLength, int n) {
        int length = initialLength;
        int buffer = 1;

        int i = initialLength + 1;
        for (; i <= n - 2; i ++) { // last 2 times should be paste (ctrl+V), no reason to waste on copying
            if ((3 * buffer + length) > length * 2) {
                length += buffer;
            } else {
                i += 2; // skip 3 steps, third one inside the loop
                buffer = length;
                length = length * 2;
            }
        }

        length += buffer * (n - (i - 1));
        return length;
    }
}
