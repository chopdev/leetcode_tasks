import java.util.ArrayList;
import java.util.List;

/**
 * 56. Merge Intervals
 https://leetcode.com/problems/merge-intervals/

 Given a collection of intervals, merge all overlapping intervals.

 Example 1:

 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

 Example 2:

 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.


 QUESTIONS:
 1) are intervals sorted or not (by start for example)?
 */

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class Solution {

    // My solution
    // O(N*logN) time, space O(logN) because of sorting
    // similar to https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null) return null;
        if(intervals.size() == 0) return new ArrayList<>();

        intervals.sort((o1, o2) -> Integer.compare(o1.start, o2.start));
        List<Interval> res = new ArrayList<>();
        int min = intervals.get(0).start;
        int max = intervals.get(0).end;

        for (Interval interval : intervals) {
            if(max >= interval.start) {
                max = Math.max(interval.end, max);
            }
            else {
                res.add(new Interval(min, max));
                min = interval.start;
                max = interval.end;
            }
        }

        res.add(new Interval(min, max));
        return res;
    }
}
