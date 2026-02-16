/*
Boats to Save People
https://leetcode.com/problems/boats-to-save-people/
*/

/**
4
[1, 1, 1, 2, 2, 3, 3]


5
[1, 1, 1, 2, 2, 3, 3, 5]
[5], [3, 1], [3, 1], [2, 1], [2, 1]

 */
import java.util.Arrays;

class Solution {
    public int numRescueBoats(int[] people, int limit) {
      if (people.length <= 1) return 1;

      Arrays.sort(people);
      int begin = 0;
      int end = people.length - 1;
      int res = 0;

      while (begin <= end) {
        int left = limit - people[end];
        if (left >= people[begin]) begin++;
        end--;
        res++;
      }
      return res;
    }
}
