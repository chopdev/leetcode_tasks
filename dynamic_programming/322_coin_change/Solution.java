import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 322. Coin Change
 https://leetcode.com/problems/coin-change/

 You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:

 Input: coins = [1, 2, 5], amount = 11
 Output: 3
 Explanation: 11 = 5 + 5 + 1


 Example 2:

 Input: coins = [2], amount = 3
 Output: -1


 Note:
 You may assume that you have an infinite number of each kind of coin.

 SOLUTION
 https://leetcode.com/articles/coin-change/
 */
public class Solution {

    // My incorrect solution
    // I try to sort array of coins and than try to take max count of the biggest coins
    // for example for  [1, 4, 5] 12
    // my solution will return 5+5+1+1, but 4+4+4 is a correct solution
    // So here we need to go through all the combinations and select the one with the min coins
    public int coinChange(int[] coins, int amount) {
        if(amount < 0 || coins == null || coins.length == 0) return -1;
        if(amount == 0) return 0;
        Arrays.sort(coins);
        Integer res = getNumberOfCoins(coins, coins.length - 1, amount);
        return res == null ? -1 : res;
    }

    private Integer getNumberOfCoins(int[] coins, int ind, int amount) {
        if(ind < 0) return null;

        for (int i = ind; i >= 0 ; i--) {
            if(coins[i] > amount) continue;
            int count = amount / coins[i];
            int rest = amount % coins[i];
            if(rest == 0) return count;

            while (count >= 0) {
                Integer restCount = getNumberOfCoins(coins, ind - 1, amount - count * coins[ind]);
                if(restCount != null) return restCount + count;
                count--;
            }
        }

        return null;
    }

    // Not mine solution
    // https://leetcode.com/articles/coin-change/
    //Time complexity : O(S*n)O(S∗n). where S is the amount, n is denomination count.
    // In the worst case the recursive tree of the algorithm has height of SS and the algorithm solves only SS subproblems because it caches precalculated solutions in a table. Each subproblem is computed with nn iterations, one by coin denomination.
    // Therefore there is O(S*n)O(S∗n) time complexity.
   // Space complexity : O(S)O(S), where SS is the amount to change We use extra space for the memoization table.
    public int coinChange2222(int[] coins, int amount) {
        if(coins == null || coins.length == 0) return -1;
        return getNumberOfCoins(coins, amount, new int[amount + 1]);
    }

    // memo - number of coins for specific amount
    private int getNumberOfCoins(int[] coins, int amount, int[] memo) {
        if(amount == 0) return 0;
        if(amount < 0) return -1;
        if(memo[amount] != 0) return memo[amount];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if(coins[i] > amount) continue;
            int res = getNumberOfCoins(coins, amount - coins[i], memo);
            if(res != -1) min = Math.min(min, res + 1);
        }

        memo[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return memo[amount];
    }


    // Not mine solution
    // https://leetcode.com/articles/coin-change/
    //Time complexity : O(S*n)O(S∗n). On each step the algorithm finds the next F(i)F(i) in nn iterations, where 1\leq i \leq S1≤i≤S. Therefore in total the iterations are S*nS∗n.
    // Space complexity : O(S)O(S). We use extra space for the memoization table.
    public int coinChange3333(int[] coins, int amount) {
        if(coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int sum = 1; sum <= amount; sum++) {
            for (int j = 0; j < coins.length; j++) {
                if(coins[j] > sum) continue;
                dp[sum] = Math.min(dp[sum], dp[sum - coins[j]] + 1);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
