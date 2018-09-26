/**
 * Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
 pennies (1 cent), write code to calculate the number of ways of representing n cents.
 */
public class Solution {
    public int getNumberOfCombinations(int cents) {
        int[] dp = new int[cents + 1];
        for (int i = 0; i < cents + 1; i++) {
            dp[i] = -1;
        }

        return getNumberOfCombinationsInternal(cents, dp);
    }

    public int getNumberOfCombinationsInternal(int cents, int[] dp) {
        if(cents < 0) return 0;
        if(cents == 0) return 1;

        if(dp[cents] >= 0) return dp[cents];

        int count1 = getNumberOfCombinationsInternal(cents - 25, dp);
        int count2 = getNumberOfCombinationsInternal(cents - 10, dp);
        int count3 = getNumberOfCombinationsInternal(cents - 5, dp);
        int count4 = getNumberOfCombinationsInternal(cents - 1, dp);

        dp[cents] = count1 + count2 + count3 + count4;
        return dp[cents];
    }
}
