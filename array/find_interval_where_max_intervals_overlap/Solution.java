import java.util.Arrays;

/**
 https://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
 I make task little bit harder.

 There are users that login to some system. Given a list of login intervals (with login/logout time).
 Return the interval with the most users online

 Example

 Input: [{1, 4}, {2, 5}, {9, 12}, {5, 9}, {5, 12} ]
 Output: {5, 5}
 Explanation: There are maximum 3 users online at time in range {5, 5}

 */
public class Solution {

    // My brute force solution
    // time O(N^2), Space O(1)
    public Interval getIntervalWithMaxUsers(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return null;

        int maxUsersOnline = 0;
        int resMin = 0, resMax = 0;

        for (int i = 0; i < intervals.length; i++) {
            int min = intervals[i].start;
            int max = intervals[i].end;
            int currUsersOnline = 0;
            for (int j = 0; j < intervals.length; j++) {
                if(min <= intervals[j].end && max >= intervals[j].start) {
                    currUsersOnline++;
                    if(currUsersOnline > maxUsersOnline) {
                        maxUsersOnline = currUsersOnline;
                        resMin = Integer.max(min, intervals[j].start);
                        resMax = Integer.min(max, intervals[j].end);
                    }
                }
            }
        }

        return new Interval(resMin, resMax);
    }

    // Not mine solution
    // Time O(N*logN), space O(N), but if parameters are presented like two separete arrays, we wouldn't use
    // additional arrays and space would be O(1)
    // Very smart
    // https://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
    /*
       Example
    *  arr[]  = {1, 2, 10, 5, 5}
        dep[]  = {4, 5, 12, 9, 12}

        Below are all events sorted by time.  Note that in sorting, if two
        events have same time, then arrival is preferred over exit.
         Time     Event Type         Total Number of Guests Present
        ------------------------------------------------------------
           1        Arrival                  1
           2        Arrival                  2
           4        Exit                     1
           5        Arrival                  2
           5        Arrival                  3    // Max Guests
           5        Exit                     2
           9        Exit                     1
           10       Arrival                  2
           12       Exit                     1
           12       Exit                     0
    * */
    // So actually we merge sorted starts and sorted ends
    // thats how we can track how many users online
    public Interval getIntervalWithMaxUsers2222(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return null;

        int maxUsersOnline = 0;
        int resMin = 0, resMax = 0;

        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int i = 0, j = 0, currOnline = 0;
        while (i < intervals.length && j < intervals.length){
            if(starts[i] <= ends[j]) {
                currOnline++;
                if(currOnline > maxUsersOnline) {
                    maxUsersOnline = currOnline;
                    resMin = starts[i];
                    resMax = ends[j];
                }
                i++;
            }
            else {
                currOnline--;
                j++;
            }
        }

        return new Interval(resMin, resMax);
    }

    /*
    * Third approach is to create range[] array from min start and max end.
    * then go through intervals and do “range[start[i]]+=1” and “range[end[i]+1]-=1”]
    * then we can go through range array and check when was the biggest count of users
    * O(max(N, M)) time, O(M) space, where M - range length, N count of intervals
    * Disadvantage of this, is that time range could be very huge
    * So 2 method is generally better
    * */
}
