/*
744. Find Smallest Letter Greater Than Target
https://leetcode.com/problems/find-smallest-letter-greater-than-target/
*/

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length; 
        int l = 0;
        int r = letters.length;

        while (l < r) {
           int mid = l + (r - l) / 2;

           if (letters[mid] <= target) {
              l = mid + 1;
           } else {
               r = mid;
           }
        }

        return l == n ? letters[0] : letters[l];
    }
}
