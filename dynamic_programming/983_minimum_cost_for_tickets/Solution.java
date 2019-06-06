import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 983. Minimum Cost For Tickets
 https://leetcode.com/problems/minimum-cost-for-tickets/

 In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

 Train tickets are sold in 3 different ways:

 a 1-day pass is sold for costs[0] dollars;
 a 7-day pass is sold for costs[1] dollars;
 a 30-day pass is sold for costs[2] dollars.
 The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

 Return the minimum number of dollars you need to travel every day in the given list of days.



 Example 1:

 Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 Output: 11
 Explanation:
 For example, here is one way to buy passes that lets you travel your travel plan:
 On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 In total you spent $11 and covered all the days of your travel.
 Example 2:

 Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 Output: 17
 Explanation:
 For example, here is one way to buy passes that lets you travel your travel plan:
 On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 In total you spent $17 and covered all the days of your travel.


 Note:

 1 <= days.length <= 365
 1 <= days[i] <= 365
 days is in strictly increasing order.
 costs.length == 3
 1 <= costs[i] <= 1000

 */
public class Solution {
    int[] ticketPassDays = new int[] {1, 7, 30};

    // Not my solution (my interpretation from memory)
    // Memoization solution, O(W) time and space (deepness of recursion + memo)
    // We check every day, if we do not travel at this day, we just skip it
    // if we travel, we try to buy all ticket types and pick the cheapest
    // https://leetcode.com/articles/minimum-cost-for-tickets/
    public int mincostTickets(int[] days, int[] costs) {
        if(days == null || days.length == 0) return 0;
        HashSet<Integer> travelDays = new HashSet<>(Arrays.stream(days).boxed().collect(Collectors.toList()));
        int[] memo = new int[366];
        return calculateForDay(1, travelDays, costs, memo);
    }

    // returns money we need to pay beginning from current day
    private int calculateForDay(int day, HashSet<Integer> travelDays, int[] costs, int[] memo) {
        if(day > 365) return 0;
        if(memo[day] > 0) return memo[day];

        if(!travelDays.contains(day))
            memo[day] = calculateForDay(day + 1, travelDays, costs, memo);
        else {
            memo[day] = Integer.MAX_VALUE;
            for (int i = 0; i < costs.length; i++)
                memo[day] = Math.min(memo[day], calculateForDay(day + ticketPassDays[i], travelDays, costs, memo) + costs[i]);
        }

        return memo[day];
    }


    // Not mine (my code interpretation from memory)
    // Similar solution as above, but using DP, O(W) time and space (dp array)
    // dp preserves money we need to spend TILL that day
    // if we do not travel on current date, sum is the same as in previous day
    public int mincostTickets2222(int[] days, int[] costs) {
        if(days == null || days.length == 0) return 0;
        HashSet<Integer> travelDays = new HashSet<>(Arrays.stream(days).boxed().collect(Collectors.toList()));
        int[] dp = new int[366];
        for (int i = 1; i < 366; i++) {
            if(!travelDays.contains(i))
                dp[i] = dp[i-1];
            else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < costs.length; j++)
                    dp[i] = Math.min(dp[i], i >= ticketPassDays[j] ? dp[i - ticketPassDays[j]] + costs[j] : costs[j]);
            }
        }

        return dp[365];
    }
}
