import java.util.HashMap;

/**
 Given two strings. First one is longer than second. Find out
 if inside the first string there is an anagram of the second string

 Example:

 Input:
 "rectopls"  "pto"
 Output: true, because "top" is an anagram of the "pto"

 Input:
 "prectols"  "pto"
 Output: false, "p" and "to" are not together, not a substring


  My mistakes:
 I didn't fully understand a problem and started to solve it with examples that were present in initial task.
 That's why I did solution that worked only for some cases and after hints, wrote fully working solution

 */
public class Solution {

    // My solution
    // O(B*(S+S+S)) = O(B*S) time
    // O(S) space
    public boolean isAnagramPresent(String big, String small) {
        if(big == null || small == null) return false;
        if(big.length() < small.length()) return false;
        if(small.isEmpty()) return false;

        int smallLen = small.length();
        int bigLen = big.length();
        HashMap<Character, Integer> charToCount = getCharMap(small);
        for (int i = 0; (i + smallLen) <= bigLen;  i++) {
            char curr = big.charAt(i);
            if(!charToCount.containsKey(curr)) continue;

            String substr = big.substring(i, i + smallLen);
            for (int j = 0; j < smallLen; j++) {
                if(!charToCount.containsKey(substr.charAt(j))) {
                    charToCount = getCharMap(small);
                    break;
                }

                char temp = substr.charAt(j);
                charToCount.put(temp, charToCount.get(temp) - 1);
                if(charToCount.get(temp) <= 0) charToCount.remove(temp);
            }

            if(charToCount.size() == 0) return true;
        }

        return false;
    }

    private HashMap<Character, Integer> getCharMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(i, 0) + 1);
        }

        return map;
    }
}
