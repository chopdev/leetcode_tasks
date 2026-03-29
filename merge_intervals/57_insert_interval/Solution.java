/*
57. Insert Interval
https://leetcode.com/problems/insert-interval/
*/

import java.util.List;
import java.util.ArrayList;

/**

[[1, 10], [12, 15]]

1. interval out of others - BEFORE

2.1. both start and end inside another interval [1, 5] - > [[1, 10], [12, 15]]
2.2. start inside interval, end outside any  -> [2, 11] -> [[1, 11], [12, 15]]
2.3. start inside interval, end covers other intervals -> [2, 20] -> [[1, 20]]

3.  -  [11, 11] -> [[1, 10], [11, 11], [12, 15]]

 */


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][] {newInterval};
        int start = newInterval[0];
        int end = newInterval[1];
        int n = intervals.length;

        List<int[]> res = new ArrayList<>();

        // Add all intervals completely before the new interval 
        int i = 0;
        while (i < n && start > intervals[i][1]) {
            res.add(new int[] {intervals[i][0], intervals[i][1]});
            i++;
        }

        // Merge all overlapping intervals
        while (i < n && end >= intervals[i][0]) {
            start = Math.min(intervals[i][0], start);
            end = Math.max(intervals[i][1], end);
            i++;
        }
        res.add(new int[] {start, end});

        // add remaining intervals
        while (i < n) {
            res.add(new int[] {intervals[i][0], intervals[i][1]});
            i++;
        }

        return res.toArray(size -> new int[size][]);
    }
}
