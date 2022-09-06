/**
 * 1641. Count Sorted Vowel Strings
 * https://leetcode.com/problems/count-sorted-vowel-strings/
 *
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 *
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 *
 *
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * */

public class Solution {

    /**
     * My accepted, but slow solution
     * lenght of recursion is equal to N, so time complexity is exponential O(5^N)
     * space: O(N)
     * */
    private char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};

    public int countVowelStrings(int n) {
        return countRecursive(n, 0, 0);
    }

    private int countRecursive(int n, int stringLenght, int indent) {
        if (stringLenght >= n) {
            return 1;
        }
        stringLenght ++;

        int sum = 0;
        for (int i = indent; i < vowels.length; i++) {
            sum += countRecursive(n, stringLenght, i);
        }

        return sum;
    }


    /**
     * https://leetcode.com/problems/count-sorted-vowel-strings/discuss/918507/Java-DP-O(n)-time-Easy-to-understand
     *  O(N) time, O(1) space
     *  super easy to understand. "Base and build" approach from cracking the coding
     * */
    public int countVowelStrings22222(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        while(n > 1) {
            // add new char before prev string
            a = (a + e + i + o + u); // a, e, i, o, u -> aa, ae, ai, ao, au
            e = (e + i + o + u); // e, i, o, u -> ee, ei, eo, eu
            i = (i + o + u); // i, o, u -> ii, io, iu
            o = (o + u); // o, u -> oo, ou
            u = (u);; // u -> uu
            n--;
        }

        return a + e + i + o + u;
    }

    /**
     * https://leetcode.com/problems/count-sorted-vowel-strings/discuss/918498/JavaC%2B%2BPython-DP-O(1)-Time-and-Space
     * O(N) time, O(N) space
     *
     * dp[n][k] means the number of strings constructed by at most k different characters.
     *
     * If k = 1, use only u
     * If k = 2, use only o,u
     * If k = 3, use only i,o,u
     * If k = 4, use only e,i,o,u
     * If k = 5, use only a,e,i,o,u
     *
     *  basically
     * 0: [1, 2, 3, 4, 5] // ('u'), ('u' 'o'), ('u' 'o' 'i') ...
     * 1: [1, 3, 6, 10, 15] // ('uu'), ('uu', 'ou', 'oo'), ('uu', 'ou', 'oo', 'iu' 'io' 'ii') ....
     * */
    public int countVowelStrings33333(int n) {
        int[][] dp = new int[n + 1][6];
        for (int i = 1; i <= n; ++i)
            for (int k = 1; k <= 5; ++k)
                dp[i][k] = dp[i][k - 1] + (i > 1 ? dp[i - 1][k] : 1);
        return dp[n][5];
    }
}
