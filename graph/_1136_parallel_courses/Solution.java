import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 1136. Parallel Courses
 *
 *  You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei],
 *  representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
 *
 * In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.
 *
 * Example 1:
 *
 * Input: n = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 1 and 2.
 * In the second semester, you can take course 3.
 *
 * Example 2:
 *
 * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: No course can be studied because they are prerequisites of each other.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5000
 * 1 <= relations.length <= 5000
 * relations[i].length == 2
 * 1 <= prevCoursei, nextCoursei <= n
 * prevCoursei != nextCoursei
 * All the pairs [prevCoursei, nextCoursei] are unique.
 * */
public class Solution {


    /*
    * My working DFS solution, Time Limit
    *
    * The number of semesters needed is equal to the length of the longest path in the graph.
    * */
    public int minimumSemesters2222(int n, int[][] relations) {
        Map<Node, List<Node>> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) graph.put(new Node(i), new ArrayList<>());

        Set<Node> firstYearCourses = new HashSet<>();
        for (int[] relation : relations) {
            Node curr = new Node(relation[0]);
            graph.get(curr).add(new Node(relation[1]));
            firstYearCourses.add(curr);
        }

        for (int[] relation : relations) firstYearCourses.remove(new Node(relation[1])); // leave only courses on which there are no references

        if (firstYearCourses.isEmpty()) return -1; // loop dependency

        int minSemesters = -1;
        for (Node course : firstYearCourses) {
            minSemesters = Math.max(minSemesters, dfs(graph, course));
        }

        return minSemesters;
    }

    private int dfs(Map<Node, List<Node>> graph, Node parent) {
        if (parent.status == Status.VISITING) throw new IllegalArgumentException("Loop detected");

        int minSemesters = parent.level;

        parent.status = Status.VISITING;
        for (Node child : graph.get(parent)) {
            child.level = Math.max(child.level, parent.level + 1);
            minSemesters = Math.max(minSemesters, dfs(graph, child));
        }
        parent.status = Status.VISITED;
        return minSemesters;
    }

    public class Node {
        public int node;
        public Status status;

        public int level = 1;

        public Node(int node) {
            status = Status.NOT_SEEN;
            this.node = node;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node other = (Node) obj;
            return other.node == this.node;
        }

        @Override
        public int hashCode() {
            return node;
        }
    }

    public enum Status {
        NOT_SEEN,
        VISITING,
        VISITED;
    }


    /*
    Not my solution, but my implementation. Khan's Algorithm
    https://leetcode.com/problems/parallel-courses/editorial/?envType=study-plan-v2&envId=premium-algo-100


    Time Complexity: O(N+E). For building the graph, we spend O(N) to initialize the graph, and spend O(E) to add egdes since we iterate relations once. For BFS, we spend O(N+E) since we need to visit every node and edge once in BFS in the worst case.
    Space Complexity: O(N+E)\mathcal{O}(N+E)O(N+E). For the graph, we spend O(N+E) since we have O(N) keys and O(E) values. For BFS, we spend O(N) since in the worst case, we need to add all nodes to the queue in the same time.

     Step 1: Build a directed graph from relations.

    Step 2: Record the in-degree of each node. (i.e., the number of edges towards the node)

    Step 3: Initialize a queue, queue. Put nodes with an in-degree of 0 into queue. Initialize step = 0, visited_count = 0.

    Step 4: Start BFS: Loop until queue is empty:
        Initialize a queue next_queueto record the nodes needed in the next iteration.
        Increment step.
        For each node in queue:
        Increment visitedCount
        For each end_node reachable from node:
        Decrement the in-degree of end_node
        If the in-degree of end_node reaches 0, push it into next_queue
        Assign queue to next_queue

    Step 5: If visited_count == N, return step. Otherwise, return -1.
    * */
    public int minimumSemesters(int n, int[][] relations) {
        int[] inDegree = new int[n + 1]; // count of references to specific node
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) graph.put(i, new ArrayList<>());
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            inDegree[relation[1]] ++;
        }

        Queue<Integer> bfsQueue = new LinkedList<>();
        int visitedCourses = 0;
        int countOfSemesters = 0;

        for (int i = 1; i <= n; i++)
            if (inDegree[i] == 0)
                bfsQueue.add(i); //  add courses that do not depend from others (no references on them)

        while (!bfsQueue.isEmpty()) {
            countOfSemesters ++;
            Queue<Integer> nextSemesterCourses = new LinkedList<>();

            while (!bfsQueue.isEmpty()) {
                int course = bfsQueue.poll();
                visitedCourses ++;
                for (int dependantCourse : graph.get(course)) {
                    inDegree[dependantCourse] --;
                    if (inDegree[dependantCourse] == 0) nextSemesterCourses.add(dependantCourse);
                }
            }

            bfsQueue = nextSemesterCourses;
        }

        return visitedCourses == n ? countOfSemesters : -1;
    }
}
