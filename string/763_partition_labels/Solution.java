import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
763. Partition Labels
https://leetcode.com/problems/partition-labels/

 A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

 Example 1:

 Input: S = "ababcbacadefegdehijhklij"
 Output: [9,7,8]

 Explanation:
 The partition is "ababcbaca", "defegde", "hijhklij".
 This is a partition so that each letter appears in at most one part.
 A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 Note:

 S will have length in range [1, 500].
 S will consist of lowercase letters ('a' to 'z') only

* */
public class Solution {
    // My solution
    // O(N) time, O(1) memory (collections will take no more than 26 elements)
    // I use a hashmap to check if the rest of the string do not contains elements of current substring
    public List<Integer> partitionLabels(String S) {
        HashMap<Character, Integer> charsToCount = new HashMap<>();
        for(char ch : S.toCharArray()) {
            charsToCount.put(ch, charsToCount.getOrDefault(ch, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        HashSet<Character> invalid = new HashSet<>();
        int len = 0;

        for (int i = 0; i < S.length(); i++) {
            char curr = S.charAt(i);
            charsToCount.put(curr, charsToCount.get(curr) - 1);
            len++;
            if(charsToCount.get(curr) > 0) invalid.add(curr);
            else invalid.remove(curr);

            if(invalid.size() == 0) {
                res.add(len);
                len = 0;
            }
        }

        return res;
    }


    // Not mine solution
    // O(N) time, O(1) memory (collections will take no more than 26 elements)
    // we remember the last position of each character
    // then as we go we try to get to last character of current substring
    public List<Integer> partitionLabels2222(String S) {
        if(S == null || S.length() == 0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for(int i = 0; i < S.length(); i++){
            map[S.charAt(i)-'a'] = i;
        }
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for(int i = 0; i < S.length(); i++){
            last = Math.max(last, map[S.charAt(i)-'a']);
            if(last == i){
                list.add(last - start + 1);
                start = last + 1;
            }
        }
        return list;
    }
}
