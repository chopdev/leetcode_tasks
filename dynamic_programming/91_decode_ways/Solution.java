import java.util.Arrays;

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



    // Not mine solution
    // But with mine approach
    // https://leetcode.com/problems/decode-ways/discuss/30451/Evolve-from-recursion-to-dp
    public int numDecodings2222(String s) {
        if(s.isEmpty()) return 0;
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return getCountWays(s, 0, memo);
    }

    private int getCountWays(String s, int ind, int[] memo) {
        if(ind >= s.length()) return 1;
        if(memo[ind] > -1) return memo[ind];

        memo[ind] = 0;
        if(s.charAt(ind) - '0' > 0)
            memo[ind] = getCountWays(s, ind + 1, memo);

        if(ind <= s.length() - 2 && (s.charAt(ind) == '1' || s.charAt(ind) == '2' && s.charAt(ind + 1) <= '6'))
            memo[ind] += getCountWays(s, ind + 2, memo);

        return memo[ind];
    }


    // Memoization good solution
    // https://leetcode.com/problems/decode-ways/discuss/30451/Evolve-from-recursion-to-dp
    public int numDecodings3333(String s) {
        if(s.isEmpty()) return 0;
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return getCountWays(s, 0, memo);
    }

    private int numDecodings3333(String s, int ind, int[] memo) {
        if(ind >= s.length()) return 1;
        if(s.charAt(ind) == '0') return 0; // we can't start from 0 so it is not a right combination
        if(memo[ind] > -1) return memo[ind];


        memo[ind] = numDecodings3333(s, ind + 1, memo); // add number of decodings, if we take one letter

        // if we can take 2 letters - add number of decodings, if we take two letters
        if(ind <= s.length() - 2 && (s.charAt(ind) == '1' || s.charAt(ind) == '2' && s.charAt(ind + 1) <= '6'))
            memo[ind] += numDecodings3333(s, ind + 2, memo);

        return memo[ind];
    }


    // Not mine
    // better to look on memo approach, this one uses similar logic
    // https://leetcode.com/problems/decode-ways/discuss/30451/Evolve-from-recursion-to-dp
    public int numDecodings4444(String s) {
        if(s.isEmpty()) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1; // index after the last one
        for (int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == '0') continue;

            dp[i] = dp[i+1];
            if(i < n - 1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) <= '6'))
                dp[i] += dp[i+2];
        }

        return dp[0];
    }
}
