/*
438. Find All Anagrams in a String
https://leetcode.com/problems/find-all-anagrams-in-a-string/

fixed sliding window problem

s = "cbaebabacd", p = "abc"
[0,6]

s = "cbaebabaccd", p = "abc"
[0] 

s = "cccc", p = "cc"
[0, 1, 2, 3]

s = "cc", p = "ccc"
[]

map[128] - to track symbols 
left - count of chars in p. Should become 0 to add index to result


*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) 
            return new ArrayList();

        int[] map = new int[128];
        int left = p.length();
        int begin = 0, end = 0;
        List<Integer> res = new ArrayList();

        for (int i = 0; i < p.length(); i++) {
            map[p.charAt(i)] ++;
        }

        // "cbaebabacd"   "abc"
        for (; end < s.length(); end ++) {
            map[s.charAt(end)] --;
            if (map[s.charAt(end)] >= 0) left --; // char from p added in the window

            while (end - begin + 1 > p.length()) { // while window size is longer than p
                char out = s.charAt(begin);
                begin ++;
                map[out] ++;
                if (map[out] > 0) left ++; // char from p disapeared from the window
            }

            if (left == 0) 
                res.add(begin);
        }

        return res;
    }
}