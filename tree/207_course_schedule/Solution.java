import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 https://leetcode.com/problems/course-schedule/description/
 207. Course Schedule

 There are a total of n courses you have to take, labeled from 0 to n-1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 Example 1:

 Input: 2, [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.


 Example 2:

 Input: 2, [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should
 also have finished course 1. So it is impossible.

 Note:

 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more
 about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.

* */
public class Solution {

    // My good solution using DFS
    // if A references B, than A is dependent from B
    // Topological sort (which means create linear structure from the graph, removing nodes without dependencies one by one)
    // Each node has 3 statuses: Initial, Visited and Visiting
    // if using dfs we get node that is Visiting, that means that there is a cycle in a graph
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Node> graph = new HashMap<>();
        for(int[] pair : prerequisites) {
            if(!graph.containsKey(pair[0])) graph.put(pair[0], new Node(pair[0]));
            if(!graph.containsKey(pair[1])) graph.put(pair[1], new Node(pair[1]));
            graph.get(pair[0]).adj.put(pair[1], graph.get(pair[1]));
        }

        for(Node node : graph.values()) {
            if(node.status != Status.Visited)
                if(isCycle(node)) return false;
        }

        return true;
    }

    private boolean isCycle(Node curr) {
        if(curr.status == Status.Visiting) return true;

        curr.status = Status.Visiting;
        for(Node n : curr.adj.values()) {
            if(n.status != Status.Visited)
                if(isCycle(n)) return true;
        }

        curr.status = Status.Visited;
        return false;
    }



    // Not mine
    // BFS solution
    // IMPORTANT: They are using other logic, if a references b, that is mean that b is dependent from a
    // https://leetcode.com/problems/course-schedule/discuss/58524/Java-DFS-and-BFS-solution
    // The idea is Topological sort using BFS
    //The degree[j] denotes the number of dependencies for course j
    //Then we search for the courses whose degree is zero (all such independent courses can be finished first.)
    //Next for each of such courses mark them as finished(increase count), and subsequently check if other courses were dependent on this course.
    //if any of their degree becomes zero after reduction, that course becomes a candidate to be finished next (no more pre-requisite).
    //At the end we see of count equals total no. of courses.
    public boolean canFinish2222(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;

        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;   // increase number of dependencies
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }

        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else
            return false;
    }


    // Mine not very efficient BFS
    // if A references B, than A is dependent from B
    public boolean canFinish3333(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Node> graph = new HashMap<>();
        for(int[] pair : prerequisites) {
            if(!graph.containsKey(pair[0])) graph.put(pair[0], new Node(pair[0]));
            if(!graph.containsKey(pair[1])) graph.put(pair[1], new Node(pair[1]));
            graph.get(pair[0]).adj.put(pair[1], graph.get(pair[1]));
        }

        Queue<Node> queue = new LinkedList<>();
        for (Node node : graph.values()) {
            if(node.status == Status.Visited) continue;

            queue.offer(node);
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                curr.status = Status.Visiting;

                for (Node child : curr.adj.values()) {
                    if(child.status == Status.Visiting) return false;
                    if(child.status != Status.Visited)
                        queue.offer(child);
                }
            }

            for (Node temp : graph.values()) {
                if(temp.status == Status.Visiting)
                    temp.status = Status.Visited;
            }
        }

        return true;
    }
}
