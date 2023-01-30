import java.util.Arrays;

/**
 * 2028. Find Missing Observations
 *
 * You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6. n of the observations went
 * missing, and you only have the observations of m rolls. Fortunately, you have also calculated the average value of the n + m rolls.
 * You are given an integer array rolls of length m where rolls[i] is the value of the ith observation. You are also given the two integers mean and n.
 *
 * Return an array of length n containing the missing observations such that the average value of the n + m rolls is exactly mean.
 * If there are multiple valid answers, return any of them. If no such array exists, return an empty array.
 *
 * The average value of a set of k numbers is the sum of the numbers divided by k.
 *
 * Note that mean is an integer, so the sum of the n + m rolls should be divisible by n + m.
 *
 *
 * Example 1:
 *
 * Input: rolls = [3,2,4,3], mean = 4, n = 2
 * Output: [6,6]
 * Explanation: The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.
 * Example 2:
 *
 * Input: rolls = [1,5,6], mean = 3, n = 4
 * Output: [2,3,2,2]
 * Explanation: The mean of all n + m rolls is (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3.
 * Example 3:
 *
 * Input: rolls = [1,2,3,4], mean = 6, n = 4
 * Output: []
 * Explanation: It is impossible for the mean to be 6 no matter what the 4 missing rolls are.
 *
 *
 * Constraints:
 *
 * m == rolls.length
 * 1 <= n, m <= 105
 * 1 <= rolls[i], mean <= 6
 * */
public class Solution {

    /**
     * My solution
     * O(N + M) time
     *
     * */
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int count = m + n;
        int sum = count * mean;
        int mSum = Arrays.stream(rolls).sum(); // O(M)
        int nSum = sum - mSum;
        double nAverage = nSum / (n * 1.0);
        if (nAverage > 6 || nAverage < 1) return new int[0];
        int nRest = nSum - ((int) nAverage) * n; // rest that should be added to N entries. e.g. if rest is 3 we want to add 1 to first 3 entry
        int[] nRolls = new int[n];
        for (int i = 0; i < n; i++) { // O(N)
            int val = (int) nAverage;
            if (nRest > 0) {
                val ++;
                nRest --;
            }
            nRolls[i] = val;
        }
        return nRolls;
    }


    /**
     * Not my
     * https://leetcode.com/problems/find-missing-observations/solutions/1500256/c-java-python-math-then-div-mod-clean-concise/?orderBy=most_votes
     *
     * SImilar solution, O(N+M) time
     * */
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int curSum = Arrays.stream(rolls).sum(), m = rolls.length;
        int missingSum = mean * (n + m) - curSum;
        if (missingSum < n || missingSum > 6*n) return new int[0];

        int part = missingSum / n, rem = missingSum % n;
        int[] ans = new int[n];
        Arrays.fill(ans, part);
        for (int i = 0; i < rem; ++i)
            ++ans[i];
        return ans;
    }
}
