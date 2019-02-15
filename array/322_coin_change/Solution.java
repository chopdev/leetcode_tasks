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
 */
public class Solution {
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
            if(rest > 0) {
                Integer restCount = getNumberOfCoins(coins, ind - 1, rest);
                if(restCount != null) return restCount + count;
            }
            else if(rest == 0) return count;
        }

        return null;
    }
}
