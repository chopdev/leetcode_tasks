import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/***
 * 621. Task Scheduler
 * https://leetcode.com/problems/task-scheduler/
 *
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters
 * represent different tasks. Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at
 * least n intervals that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 *
 * Example:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 *
 * Note:
 *
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 * */
public class Solution {

    // My solution
    // Idea is to execute tasks with biggest count first, repeating the process each n interval
    // time complexity: O(N) + O(26*O(log26)) + O(N*log(26) + N*26*log(26)) = O(N)
    // space O(1) queue and arrays will not exceed 26
    public int leastInterval(char[] tasks, int n) {
        int[] taskCounts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            taskCounts[tasks[i] - 'A']++;
        }
        PriorityQueue<Integer> counts = new PriorityQueue<>(Comparator.reverseOrder());
        for (int count : taskCounts) {
            if(count > 0)
                counts.offer(count);
        }

        return getIntervalsCount(counts, n);
    }

    // PriorityQ add/remove O(K*logK) = O(26*log26) in our case
    // remove operation takes O(N*log(26) each task we remove N times in a tree of size 26
    // add operation takes up to N*26*log(26)
    private int getIntervalsCount(PriorityQueue<Integer> counts, int offset) {
        int intervals = 0;
        List<Integer> temp = new ArrayList<>();
        while (!counts.isEmpty()) {
            for (int i = 0; i <= offset; i++) {
                if(counts.isEmpty() && temp.size() == 0) {
                    break; // finished to process all tasks
                }
                if(counts.isEmpty()) {
                    intervals++; // put idle
                } else {
                    intervals++; // execute task
                    int currTypeCount = counts.poll();
                    if(--currTypeCount > 0) temp.add(currTypeCount);
                }
            }

            for(Integer tmp : temp) {
                counts.offer(tmp);
            }
            temp.clear();
        }
        return intervals;
    }
}
