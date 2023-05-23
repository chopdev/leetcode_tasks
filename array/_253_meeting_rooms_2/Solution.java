import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

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

    /**
    * Great solution, also O(N*logN) but interesting approach
     * less error prone and concise
    * https://leetcode.com/problems/meeting-rooms-ii/solutions/203658/hashmap-treemap-resolves-scheduling-problem/
     *
     * Balanced Binary Search Tree: TreeMap uses a balanced binary search tree (specifically, a Red-Black tree) as its underlying data structure.
     * Key-Value Mapping: TreeMap allows key-value pairs, where each key is unique. It ensures that the keys are sorted and provides efficient operations to retrieve, insert, and delete key-value pairs.
     * Iteration: TreeMap provides methods to iterate over the keys, values, or entries in sorted order, such as keySet(), values(), and entrySet(). The iteration order follows the natural ordering or the custom comparator.
     * Submap Views: TreeMap supports operations to obtain submaps based on a range of keys, allowing you to work with a subset of the original map. These submap views are backed by the original map and any changes made to them affect the original map as well.
     *
     * It's important to note that the operations' complexity in TreeMap, such as insertion, deletion, and search, is O(log n), where n is the number of elements in the map.
    * */
    public int minMeetingRooms2222(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>(); // key is a time, value is a count of started or ended intervals at this point of time
        for (int[] itl : intervals) {
            map.put(itl[0], map.getOrDefault(itl[0], 0) + 1);
            map.put(itl[1], map.getOrDefault(itl[1], 0) - 1);
        }
        int room = 0, k = 0;
        for (int v : map.values())
            k = Math.max(k, room += v);

        return k;
    }
}
