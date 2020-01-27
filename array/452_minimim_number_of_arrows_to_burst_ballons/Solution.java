import java.lang.reflect.Array;
import java.util.Arrays;

/**
 452. Minimum Number of Arrows to Burst Balloons
 https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

 There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the
 start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence
 the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most
 104 balloons.
 An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend
 bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow
 once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to
 burst all balloons.

 Example:
 Input:
 [[10,16], [2,8], [1,6], [7,12]]
 Output:
 2

 Explanation:
 One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 * */
public class Solution {

    // My solution
    // O(N*logN) time, O(logN) space
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (arr1, arr2) -> arr1[0] > arr2[0] ? 1 : arr1[0] == arr2[0] ? 0 : -1);
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            count++;
            int minRight = points[i][1];
            for (int j = i + 1; j < points.length; j++) {
                if(minRight < points[j][0]) {
                    i = j - 1;
                    break;
                }
                else {
                    minRight = Math.min(minRight, points[j][1]);
                    if(j == points.length - 1) i = j;
                }
            }
        }
        return count;
    }

    // Not mine solution
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                return p1[0] - p2[0];
            }
        });
        int res = 0;
        int idx = 0;
        while (idx < points.length) {
            int rightMost = points[idx][1];
            res++;
            while (idx < points.length && points[idx][0] <= rightMost) {
                rightMost = Math.min(rightMost, points[idx][1]);
                idx++;
            }
        }
        return res;
    }
}
