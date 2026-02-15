/*
567. Permutation in String
https://leetcode.com/problems/permutation-in-string/

*/


class Solution {
    // O(N) time, O(1) space
    // map - contains chars of s1, if key is negative it means char is not present in s1 
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] map = new int[128];
        int begin = 0;
        int remaining = s1.length();

        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i)] ++;
        }

        for (int end = 0; end < s2.length(); end ++) {
            if (map[s2.charAt(end)] > 0) remaining --;
            map[s2.charAt(end)] --;

            if (remaining == 0) return true;

            // move begin to end+1 to get rid off the char not present in s1
            while (map[s2.charAt(end)] < 0 && begin < end) { 
                map[s2.charAt(begin)] ++;
                if (map[s2.charAt(begin)] > 0) remaining ++;
                begin ++;
            }
        }

        return false;
    }
}