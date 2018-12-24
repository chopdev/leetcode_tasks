/**
 121. Best Time to Buy and Sell Stock
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

 Say you have an array for which the ith element is the price of a given stock on day i.
 If you were only permitted to complete at most one transaction
 (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 Note that you cannot sell a stock before you buy one.

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Not 7-1 = 6, as selling price needs to be larger than buying price.


 Example 2:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.

 * */
public class Solution {

    // My O(N) time, O(1) space solution
    // idea is taken from 122 leetcode task
    // Imagine a graph with these prices (days to prices)
    // while graph is going down and min value is bigger than prices[i], remember min value
    // when the graph is going up, we are calculating diff and remember it when it is the biggest
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;

        int min = prices[0];
        int diff = 0;

        for (int i = 1; i < prices.length; i++) {
            if(prices[i-1] > prices[i] && min > prices[i]) {
                min = prices[i];
                continue;
            }

            int temp = prices[i] - min;
            if(temp > diff) diff = temp;
        }

        return diff;
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
    // The points of interest are the peaks and valleys in the given graph. We need to find
    // the largest peak following the smallest valley. We can maintain two variables - minprice
    // and maxprofit corresponding to the smallest valley and maximum profit
    // (maximum difference between selling price and minprice) obtained so far respectively.
    public int maxProfit2222(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
