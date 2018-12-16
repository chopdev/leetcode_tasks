/**
 122. Best Time to Buy and Sell Stock II
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/

 Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 (i.e., buy one and sell one share of the stock multiple times).
 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 7
 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.


 Example 2:

 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.


 Example 3:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.


 IMPORTANT:
 COuld we have 0 price or negative?
* */
public class Solution {

    // My solution
    // time limit exception
    // Time O(2^N)
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        return calculate(-1, 0, prices, 0);
    }

    private int calculate(int curr, int profit, int[] prices, int ind) {
        if(ind == prices.length) return profit;

        int res1 = 0;
        if(curr < 0)
            res1 = calculate(prices[ind], profit, prices, ind + 1);
        else if(prices[ind] > curr)
            res1 = calculate(-1, prices[ind] - curr + profit, prices, ind + 1);

        int res2 = calculate(curr, profit, prices, ind + 1);
        return Math.max(res1, res2);
    }
}
