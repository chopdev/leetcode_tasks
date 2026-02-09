/*
76 Minumum window substring

https://leetcode.com/problems/minimum-window-substring/


sliding window problem

s = "ADOBEC ODE BANC", t = "ABC" -> BANC

s="SSSSS", t = "SS" -> "SS"

s="A", t = "AA" -> ""


map - char to count of remaining chars in t
missing - totatal count of chars in t still missing in the window of s

*/

class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        int missing = t.length();
        
        int bestBegin = 0, bestEnd = Integer.MAX_VALUE;

        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)] ++;
        }

        int begin = 0;
        for (int end = 0; end < s.length(); end ++) {
            map[s.charAt(end)] --; // extend the window with new char
            if (map[s.charAt(end)] >= 0) missing --;

            while (missing == 0 && begin < s.length()) {
                if (bestEnd - bestBegin > end - begin) {
                    bestEnd = end;
                    bestBegin = begin;
                }

                int out = s.charAt(begin);
                begin ++;
                map[out] ++;
                if (map[out] > 0) missing ++;
            }
        }

        return bestEnd != Integer.MAX_VALUE ? s.substring(bestBegin, bestEnd + 1) : "";
    }
}