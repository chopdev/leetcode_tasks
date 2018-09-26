/**
 * Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
 pennies (1 cent), write code to calculate the number of ways of representing n cents.
 */
public class Solution {

    // Not mine solution
    // Time ???
    
    // make change beginning from 0, if cents = 100
    // makeChange(100 with 0 of 25)
    // makeChange(100 with 1 of 25) -> makeChange(75 with 0 of 10), makeChange(75 with 1 of 10) and so on
    // makeChange(100 with 2 of 25)
    // ...
    public int getNumberOfExchanges(int cents) {
        if(cents <= 0) return -1;
        int[] monets = new int[] {1, 5, 10, 25};
        int[][] dp = new int[cents + 1][monets.length];

        return getNumberOfExchangesInternal(cents, 0, monets, dp);
    }

    public int getNumberOfExchangesInternal(int cents, int index, int[] monets, int[][] dp ) {
        if(cents == 0) return 1;
        if(index >= monets.length) return 0;

        if(dp[cents][index] > 0)
            return dp[cents][index];

        int res = 0;
        for (int i = 0; cents >= i * monets[index]; i++) {
            int remCents = cents - i * monets[index];
            res += getNumberOfExchangesInternal(remCents, index + 1, monets, dp);
        }

        dp[cents][index] = res;
        return res;
    }
}
