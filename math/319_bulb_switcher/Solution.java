import java.util.Arrays;

/**
 319. Bulb Switcher
 https://leetcode.com/problems/bulb-switcher/

 There are n bulbs that are initially off. You first turn on all the bulbs.
 Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off
 or turning off if it's on). For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the
 last bulb. Find how many bulbs are on after n rounds.

 Example:

 Input: 3
 Output: 1

 Explanation:
 At first, the three bulbs are [off, off, off].
 After first round, the three bulbs are [on, on, on].
 After second round, the three bulbs are [on, off, on].
 After third round, the three bulbs are [on, off, off].

 So you should return 1, because there is only one bulb is on.
* */
public class Solution {

    // My solution
    // O(n^2) - time limit exceeded
    public int bulbSwitch(int n) {
        boolean[] bulbs = new boolean[n+1];
        Arrays.fill(bulbs, true);

        for (int i = 2; i <= n ; i++) {
            for (int j = i; j <= n; j+=i) {
                bulbs[j] = bulbs[j] ? false : true;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if(bulbs[i]) count++;
        }

        return count;
    }

    // Not mine O(1) solution
    //https://leetcode.com/problems/bulb-switcher/discuss/77104/Math-solution..
    // Divisors come in pairs, like i=12 has divisors 1 and 12, 2 and 6, and 3 and 4.
    // Except when i is a square, like 36 has divisors 1 and 36, 2 and 18, 3 and 12, 4 and 9,
    // and double divisor 6. So bulb i ends up on if and only if i is a square.
    //
    //So just count the square numbers.
    //Let R = int(sqrt(n)). That's the root of the largest square in the range [1,n].
    // And 1 is the smallest root. So you have the roots from 1 to R, that's R roots.
    // Which correspond to the R squares. So int(sqrt(n)) is the answer. (C++ does the
    // conversion to int automatically, because of the specified return type).
    public int bulbSwitch2222(int n) {
        return (int)Math.sqrt(n);
    }

    // O(N) time
    //https://leetcode.com/problems/bulb-switcher/discuss/77116/Java-simple-n(12)-solution
    // Divisors come in pairs, like i=12 has divisors 1 and 12, 2 and 6, and 3 and 4.
    // Except when i is a square, like 36 has divisors 1 and 36, 2 and 18, 3 and 12, 4 and 9,
    // and double divisor 6. So bulb i ends up on if and only if i is a square.
    public int bulbSwitch3333(int n) {
        int on = 0;
        for (int i = 1; i * i <= n; i++)    {
            on++;
        }

        return on;
    }
}
