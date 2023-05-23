import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II
 * https://leetcode.com/problems/meeting-rooms-ii/description/?envType=study-plan-v2&id=premium-algo-100
 *
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 *
 * -------
 * Question to ask:
 * [[0,5],[5,10]] - in this case should we have 2 or 1 room?
 * */
public class Solution {

    /**
     * My solution
     * O(N*logN) time, because of sorting and/or log(N) for heap poll
     * O(N) space because of heap
     * */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> overlapping = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        overlapping.add(intervals[0]);
        int res = 1;

        for (int i = 1; i < intervals.length; i++) {
            int[] currInterval = intervals[i];
            int[] minEndInterval = overlapping.peek();
            if (currInterval[0] < minEndInterval[1]) { // if current interval still in the range
                overlapping.offer(currInterval);
                res = Math.max(res, overlapping.size());
            } else { // curr interval is starting later than min end of overlapping intervals
                while (!overlapping.isEmpty() && currInterval[0] >= overlapping.peek()[1]) // remove interval with min end
                    overlapping.poll();
                overlapping.add(currInterval);
            }
        }

        return res;
    }


    /**
     * here is similar to my above
     * https://leetcode.com/problems/meeting-rooms-ii/solutions/67857/ac-java-solution-using-min-heap/
     * */

    /*
    * https://leetcode.com/problems/meeting-rooms-ii/solutions/67855/explanation-of-super-easy-java-solution-beats-98-8-from-pinkfloyda/
    * */
    public int minMeetingRooms2222(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
}
