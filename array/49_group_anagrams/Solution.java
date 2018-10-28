import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/description/
 * 49. Group Anagrams
 *
 Given an array of strings, group anagrams together.

 Example:
 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]

 Note:

 All inputs will be in lowercase.
 The order of your output does not matter.

 */
public class Solution {

    // Mine solution O(N*K*logK) time, O(N*K) space
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String str : strs) {
            String  key = getKey(str);
            if(map.containsKey(key))
                map.get(key).add(str);
            else {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        List<List<String>> buckets = new ArrayList<>(map.size());
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet())
            buckets.add(entry.getValue());

        return buckets;
    }

    private String getKey(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

   /* private int getIntKey(String str) {
        int key = 0;
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            key ^= str.charAt(i) - 'a';
            sum += str.charAt(i) - 'a';
        }
        return key * sum;
    }*/



    // Not mine solution, O(N*K) time, O(N*K) space
    //We can transform each string s into a character count, count, consisting of
    // 26 non-negative integers representing the number of a, b, c etc.
    // We use these counts as the basis for our hash map.
    public List<List<String>> groupAnagrams2222(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
