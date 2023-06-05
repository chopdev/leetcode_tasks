import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. Group Shifted Strings
 *
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 *
 * Example 1:
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 *
 *
 * Example 2:
 *
 * Input: strings = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strings.length <= 200
 * 1 <= strings[i].length <= 50
 * strings[i] consists of lowercase English letters.
 *
 * */
public class Solution {

    /***
     * Let's consider this example
     * "cad" -> "dbe" -> "ecf"
     * "cad" and  "ecf" can be in the same group if they have the same shifts in the string itself, same shifts from one letter to another e.g.
     * "cad" can be calculated as "c" - "a" and "a" - "d", so the final hash id "3_-4"
     * "ecf" can be calculated as "e" - "c" and "c" - "f", so the final hash id "3_-4"
     *
     * My solution
     * Not working for all test cases because of incorrect getHash2222() function
     * O
     * */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String str: strings) {
            String hash = getHash(str);
            if (!groups.containsKey(hash)) groups.put(hash, new ArrayList<>());
            groups.get(hash).add(str);
        }
        return new ArrayList<>(groups.values());
    }

    private String getHash2222(String str) {
        if (str.length() == 1) return ""; // one letter strings are always in the same group
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i++) {
            int char1 = str.charAt(i) - 'a';
            int char2 = str.charAt(i + 1) - 'a';
            int diff = Math.abs(char1 - char2) < Math.abs(char1 - char2 + 26) ? char1 - char2 : char1 - char2 + 26; // "a" to "z" might be distance -25 or 1
            builder.append(diff);
            builder.append("_");
        }
        return builder.toString();
    }


    /**
     * Not my https://leetcode.com/problems/group-shifted-strings/editorial/?envType=study-plan-v2&envId=premium-algo-100
     * Same idea as my, but different implementation
     * every string in the group ["acf","gil","xzc"] can be represented as 2,3
     * because c - a = i - g = z - x = 2 and f - c = l - i = c - z = 3. Here the last expression c - z is -23,
     * however, we are doing a circular shift, so we will do a modulo operation on the result to make it 3
     * */
    String getHash(String s) {
        char[] chars = s.toCharArray();
        StringBuilder hashKey = new StringBuilder();

        for (int i = 1; i < chars.length; i++) {
            hashKey.append((char) ((chars[i] - chars[i - 1] + 26) % 26 + 'a'));
        }

        return hashKey.toString();
    }
}
