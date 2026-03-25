/*
56. Merge Intervals
https://leetcode.com/problems/merge-intervals/
*/

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/*

[[2,3],[4,5],[6,7],[8,9],[1,10]]
[[1,10],[2,3],[4,5],[6,7],[8,9]]

sort by start of range. Compare each item with the latest range in the response
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        res.add(new int[] {intervals[0][0], intervals[0][1]});

        for (int i = 1; i < n; i ++) {
            if (intervals[i][0] <= res.get(res.size() - 1)[1]) {
                res.get(res.size() - 1)[1] = Math.max(intervals[i][1], res.get(res.size() - 1)[1]);
            } else {
                res.add(new int[] {intervals[i][0], intervals[i][1]});
            }
        }

        return res.toArray(int[][]::new);
    }
}
