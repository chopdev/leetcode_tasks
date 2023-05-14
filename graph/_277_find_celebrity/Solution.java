import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 277. Find the Celebrity
 * https://leetcode.com/problems/find-the-celebrity/?envType=study-plan-v2&id=premium-algo-100
 *
 * Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know
 * the celebrity, but the celebrity does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. You are only allowed to ask questions like:
 * "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) that tells you whether a knows b. Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.
 * Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.
 *
 * Example 1
 * Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j,
 * otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 *
 * Example 2:
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * Output: -1
 * Explanation: There is no celebrity.
 *
 * Constraints:
 *
 * n == graph.length == graph[i].length
 * 2 <= n <= 100
 * graph[i][j] is 0 or 1.
 * graph[i][i] == 1
 *
 *
 * Follow up: If the maximum number of allowed calls to the API knows is 3 * n, could you find a solution without exceeding the maximum number of calls?
 * */
public class Solution {

    /**
     * My brute force
     *
     * Important to note:
     * The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.
     *
     * We might have the case where there are multiple persons known to everyone else.
     * We might also have a case when there is a person not known to anyone and who doesn't know anyone.
     *
     * O(N^2) time
     *
     * */
    public int findCelebrity(int n) {
        List<Integer> famous = new ArrayList<>();
        for (int i = 0; i < n; i ++) { // find persons known to everyone
            boolean knownToEveryone = true;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (!knows(j, i)) {
                    knownToEveryone = false;
                    break;
                }
            }
            if (knownToEveryone)
                famous.add(i);
        }

        for (int curr : famous) {  // find out if famous persons know any others
            boolean knowsSomeone = false;
            for (int i = 0; i < n; i ++) {
                if (curr == i) continue;
                if (knows(curr, i)) {
                    knowsSomeone = true;
                    break;
                }
            }
            if (!knowsSomeone) return curr;
        }

        return -1;
    }

    private boolean knows(int a, int b) {
        // stub function
        return false;
    }

    /**
     * O(N) time and space, not my
     * https://leetcode.com/problems/find-the-celebrity/solutions/71240/ac-java-solution-using-stack/?envType=study-plan-v2&id=premium-algo-100&orderBy=most_votes
     * */
    public int findCelebrity3333(int n) {
        // base case
        if (n <= 0) return -1;
        if (n == 1) return 0;

        Stack<Integer> stack = new Stack<>();

        // put all people to the stack
        for (int i = 0; i < n; i++) stack.push(i);

        int a = 0, b = 0;

        while (stack.size() > 1) {
            a = stack.pop(); b = stack.pop();

            if (knows(a, b))
                // a knows b, so a is not the celebrity, but b may be
                stack.push(b);
            else
                // a doesn't know b, so b is not the celebrity, but a may be
                stack.push(a);
        }

        // double check the potential celebrity
        int c = stack.pop();

        for (int i = 0; i < n; i++)
            // c should not know anyone else
            if (i != c && (knows(c, i) || !knows(i, c)))
                return -1;

        return c;
    }

    /**
     *
     * not my solution
     * O(N) time, O(1) space
     * https://leetcode.com/problems/find-the-celebrity/solutions/71227/java-solution-two-pass/?envType=study-plan-v2&id=premium-algo-100&orderBy=most_votes
     *
     * suppose the candidate after the first for loop is person k, it means 0 to k-1 cannot be the celebrity, because they know a previous or current candidate.
     * Also, since k knows no one between k+1 and n-1, k+1 to n-1 can not be the celebrity either. Therefore, k is the only possible celebrity, if there exists one.
     * The remaining job is to check if k indeed does not know any other persons and all other persons know k.
     *
     * TO SUM UP:
     * knows(i,j) eliminates either i or j. knows(i,j) == true then i can't be a celeb. since a celeb knows nobody and knows(i,j) == false then j can't be a celeb since everyone must know the celeb.
     * */
    public int findCelebrity22222(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i))
                candidate = i;
        }
        for(int i = 0; i < n; i++){
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
}
