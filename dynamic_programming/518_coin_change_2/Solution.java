import java.util.Arrays;

/**
 518. Coin Change 2
 https://leetcode.com/problems/coin-change-2/

 You are given coins of different denominations and a total amount of money. Write a function to compute the number of
 combinations that make up that amount. You may assume that you have infinite number of each kind of coin.



 Example 1:

 Input: amount = 5, coins = [1, 2, 5]
 Output: 4
 Explanation: there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1


 Example 2:

 Input: amount = 3, coins = [2]
 Output: 0
 Explanation: the amount of 3 cannot be made up just with coins of 2.


 Example 3:

 Input: amount = 10, coins = [10]
 Output: 1


 Note:

 You can assume that

 0 <= amount <= 5000
 1 <= coin <= 5000
 the number of coins is less than 500
 the answer is guaranteed to fit into signed 32-bit integer


 */
public class Solution {

    // Mine not working solution
    // It takes into account the order of the coins
    // amount 3, coins [1, 2]
    // it will calculate it wrong for 1 - 1 combination
    // for 2 - 2 combinations
    // for 3 - combinations of 2 + combination of 1 = 3, but we could have only 2 combinations
    public int change(int amount, int[] coins) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        return countCombinations(amount, coins, memo);
    }

    private int countCombinations(int amount, int[] coins, int[] memo) {
        if(amount == 0) return 1;
        if(memo[amount] > -1) return memo[amount];

        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if(coins[i] > amount) continue;
            count += countCombinations(amount - coins[i], coins, memo);
        }
        return memo[amount] = count;
    }


    // Not mine solution
    // https://leetcode.com/problems/coin-change-2/discuss/99222/Video-explaining-how-dynamic-programming-works-with-the-Coin-Change-problem
    // so we have to calculate number of combinations if we could use only one coin
    // than we have to calculate number of combinations if we could also use second coin...
    public int change2222(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // dp - count of combinations for amount dp[i]
        dp[0] = 1;

        for (int coin : coins) {
            for (int j = 1; j < dp.length; j++) {  // j - current amount
                if(j >= coin)                   // if current amount is bigger than coin
                    dp[j] += dp[j - coin];      // add count of combinations for sum j - coins[i]
            }
        }

        return dp[amount];
    }
}
