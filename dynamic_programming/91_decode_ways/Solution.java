/**
 91. Decode Ways
 https://leetcode.com/problems/decode-ways/

 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).


 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


 */
public class Solution {

    // My backtracking solution
    // Time Limit Exceeded  - O(2^N)
    int count;
    public int numDecodings(String s) {
        if(s.isEmpty()) return 0;
        count = 0;
        countWays(s, 0);
        return count;
    }

    private void countWays(String s, int ind) {
        if(ind >= s.length()) {
            count++;
            return;
        }

        if(s.charAt(ind) - '0' > 0)
            countWays(s, ind + 1);

        if(ind <= s.length() - 2) {
            int numb = Integer.parseInt(s.substring(ind, ind + 2));
            if(numb >= 10 && numb <= 26) countWays(s, ind + 2);
        }
    }
}
