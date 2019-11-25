import java.util.HashMap;
import java.util.Map;

/**
https://leetcode.com/problems/longest-well-performing-interval/

 We are given hours, a list of the number of hours worked per day for a given employee.
 A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 A well-performing interval is an interval of days for which the number of tiring days is strictly larger than
 the number of non-tiring days.
 Return the length of the longest well-performing interval.

 Example 1:

 Input: hours = [9,9,6,0,6,6,9]
 Output: 3
 Explanation: The longest well-performing interval is [9,9,6].

 Constraints:

 1 <= hours.length <= 10000
 0 <= hours[i] <= 16

 **/
public class Solution {

    // My wrong solution
    // will not work with [9, 6, 9]
    public int longestWPI(int[] hours) {
        int maxLen = 0, currLen = 0, balance = 0;
        for (int i = 0; i < hours.length; i++) {
            if(hours[i] > 8) balance ++;
            else balance--;

            if(balance > 0) {
                currLen++;
            }
            else {
                currLen = 0;
                balance = 0;
            }

            if(currLen > maxLen) maxLen = currLen;
        }

        currLen = 0;
        balance = 0;
        for (int i = hours.length - 1; i >= 0; i--) {
            if(hours[i] > 8) balance ++;
            else balance--;

            if(balance > 0) {
                currLen++;
            }
            else {
                currLen = 0;
                balance = 0;
            }

            if(currLen > maxLen) maxLen = currLen;
        }
        return maxLen;
    }


    /**
     * Not mine solution
     * https://leetcode.com/problems/longest-well-performing-interval/discuss/334565/JavaC%2B%2BPython-O(N)-Solution-Life-needs-996-and-669
     * O(N) space and time
     * Example:
     7    7   7   9   7   7   9   9      - hours[]
     -1  -2  -3  -2  -3  -4  -3  -2      - score
     0    0   0   1   1   1   1   5      - res
    * */
    public int longestWPI2222(int[] hours) {
        int res = 0, score = 0;
        Map<Integer, Integer> scoreToIndex = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            score += hours[i] > 8 ? 1 : -1;
            if(score > 0)
                res = i + 1;  // if score is positive, whole range from the beginning is well
            else {
                scoreToIndex.putIfAbsent(score, i);  // remember negative score and index on which it was

                // if there is a score that is less on one, means that current value is > 8
                // or that there were some more values > 8
                // so we find the index on which we first met value that is 1 less than current
                // so (j, i] is positive range
                if(scoreToIndex.containsKey(score - 1))
                    res = Math.max(res, i - scoreToIndex.get(score - 1));
            }
        }
        return res;
    }
}
