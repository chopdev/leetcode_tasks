import java.util.HashSet;
import java.util.Set;

/**
 * 2405. Optimal Partition of String
 * https://leetcode.com/problems/optimal-partition-of-string/
 *
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique.
 * That is, no letter appears in a single substring more than once.
 *
 * Return the minimum number of substrings in such a partition.
 *
 * Note that each character should belong to exactly one substring in a partition.
 *
 * Example 1:
 *
 * Input: s = "abacaba"
 * Output: 4
 * Explanation:
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 *
 *
 * Example 2:
 *
 * Input: s = "ssssss"
 * Output: 6
 * Explanation:
 * The only valid partition is ("s","s","s","s","s","s").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only English lowercase letters.
 * */
public class Solution {

    /**
     * My greedy solution
     *
     * the idea is to use window which is extended until we find repeated entry:
     * abcdabklm -> abcd abklm
     * abca -> abc a   - important test case, we need to calculate ending
     *
     * The initial question I had:
     * If not greedy solution. Can we partition string differently so we get less number of substrings?
     * */
    public int partitionString(String s) {
        if (s == null || s.isEmpty()) return 0;

        int res = 0;
        Set<Character> entries = new HashSet<>();
        for (int start = 0; start < s.length(); ) {
            for (int end = start; end < s.length(); end++) {
                if (entries.contains(s.charAt(end))) { // if we found same entry
                    start = end;
                    entries.clear();
                    res ++;
                    break;
                }
                if (end == s.length() - 1) { // first if didn't work, so we processed all the string
                    start = s.length();
                    res ++;
                    break;
                }
                entries.add(s.charAt(end));
            }
        }
        return res;
    }


    // Similar as my, but one loop. Actually second loop is not needed
    // https://leetcode.com/problems/optimal-partition-of-string/solutions/2560408/c-java-easy-solution-explained-beginner-friendly-best-method/?orderBy=most_votes
    public int partitionString2222(String s) {
        int ans = 1;
        HashSet<Character> st = new HashSet<>();
        for(int i=0;i<s.length();i++){
            // Insert Till we find duplicate element.
            if(!st.contains(s.charAt(i))){
                st.add(s.charAt(i));
            }
            else{
                // If we found duplicate char then increment count and clear set and start with new set.
                ans++;
                st.clear();
                st.add(s.charAt(i));
            }
        }
        return ans;
    }
}
