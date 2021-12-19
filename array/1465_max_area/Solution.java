import java.util.Arrays;

import javax.management.MXBean;

/* 
1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/

You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.

Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.

Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
*/

// Example 1:
// heights [1, 1, 2, 1]
// width [1, 2, 1]
 
// Example 2:
// heights [1, 2, 2]
// width [1, 3]


//1000000000
//2147483647

class Solution {

    // My solution
    // O(N*logN + M*lonM) time and O(N+M) space
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long[] heights = getValues(horizontalCuts, 0, h);
        long[] widths = getValues(verticalCuts, 0, w);
        long square =  getMax(heights) * getMax(widths);
        return (int)(square % (Math.pow(10, 9) + 7));
    }
    
    private long[] getValues(int[] cuts, int low, int max) {
        long[] lengths = new long[cuts.length + 1];
        if (cuts.length == 0) {
            lengths[0] = max;
            return lengths;
        }
        lengths[0] = cuts[0] - 0;
        for (int i = 0; i<cuts.length - 1; i++) {
            lengths[i+1] = cuts[i+1] - cuts[i];
        }
        lengths[lengths.length - 1] = max - cuts[cuts.length - 1];
        return lengths;
    }

    private long getMax(long[] values) {
        long max = Integer.MIN_VALUE;
        for (long val : values) {
            max = Math.max(max, val);
        }
        return max;
    }




    // Not mine
    // https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/discuss/661666/Java-max-dist-btw-cuts
    // O(N*logN + M*lonM) time and O(N+M) space - because of sorting
    public int maxArea2222(int h, int w, int[] hc, int[] vc) {
        return (int) ((getMaxDist(h, hc) * getMaxDist(w, vc)) % (Math.pow(10, 9) + 7));
    }
    
    private long getMaxDist(int end, int[] cuts) {
        Arrays.sort(cuts);
        long res = 0, from = 0;
        for (int c : cuts) {
            res = Math.max(res, c - from);
            from = c;
        }
        return Math.max(res, end - from);
    }
    
    
}