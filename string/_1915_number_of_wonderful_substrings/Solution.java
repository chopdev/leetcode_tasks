public class Solution {

    /**
     * My brute force solution
     * O(W^2) time, O(1) space
     * */
    public long wonderfulSubstrings2222(String word) {
        long res = 0;

        for (int i = 0; i < word.length(); i++) {
            int[] letters = new int[10];
            long countOfOdd = 0;
            for (int j = i; j < word.length(); j++) {
                int ch = word.charAt(j) - 'a';
                letters[ch] += 1;

                if (letters[ch] % 2 != 0) countOfOdd ++;
                else countOfOdd --;

                if (countOfOdd <= 1) res++;
            }
        }
        return res;
    }


    /**
     * O(N) time, O(1) space
    * https://leetcode.com/problems/number-of-wonderful-substrings/solutions/1299773/intuitive-explanation-easy-to-understand/?orderBy=most_votes
    * */
    public long wonderfulSubstrings(String word) {
        long[] statesCount = new long[1024]; // 2^10 of possible states
        int mask = 0; // current state, bitmask. "1001" means d has odd count, c even, b even, a odd
        statesCount[0] = 1; // empty string gives case where all characters occur even number of times
        long res = 0;
        for (char ch : word.toCharArray()) {
            int ind = ch - 'a';
            mask ^= 1 << ind;
            res += statesCount[mask]; // if we meet the same state it means that word[j to current Index] has even number of chars
            for (int i = 0; i < 10; i++) {
                // check if we had previous states where only one char was different from current state, meaning 1 char will be odd in word[j to current Index]
                int lookForMask = mask ^ (1 << i);
                res += statesCount[lookForMask];
            }
            statesCount[mask] ++;
        }
        return res;
    }

}
