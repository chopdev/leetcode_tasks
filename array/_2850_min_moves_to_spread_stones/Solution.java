import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 2850. Minimum Moves to Spread Stones Over Grid
 *
 * https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid/description/
 *
 *
 * You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell.
 * The grid contains exactly 9 stones, and there can be multiple stones in a single cell.
 * In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.
 *
 * Return the minimum number of moves required to place one stone in each cell.
 *
 * Example 1
 *
 * Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
 * Output: 3
 * Explanation: One possible sequence of moves to place one stone in each cell is:
 * 1- Move one stone from cell (2,1) to cell (2,2).
 * 2- Move one stone from cell (2,2) to cell (1,2).
 * 3- Move one stone from cell (1,2) to cell (0,2).
 * In total, it takes 3 moves to place one stone in each cell of the grid.
 * It can be shown that 3 is the minimum number of moves required to place one stone in each cell.
 *
 * Example 2
 *
 * Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
 * Output: 4
 * Explanation: One possible sequence of moves to place one stone in each cell is:
 * 1- Move one stone from cell (0,1) to cell (0,2).
 * 2- Move one stone from cell (0,1) to cell (1,1).
 * 3- Move one stone from cell (2,2) to cell (1,2).
 * 4- Move one stone from cell (2,2) to cell (2,1).
 * In total, it takes 4 moves to place one stone in each cell of the grid.
 * It can be shown that 4 is the minimum number of moves required to place one stone in each cell.
 *
 * Constraints:
 *
 * grid.length == grid[i].length == 3
 * 0 <= grid[i][j] <= 9
 * Sum of grid is equal to 9
 * */
public class Solution {

    /**
     * Greedy solution won't work: When we take 1 stone from the nearest neighbor, then in future we may need to go way longer because of previous step.
     *
     * The idea is to backtrack all variants. Take a stone from a cell with more than 1 stone. THen recursively fill other cells.
     * Then take a stone from another cell and recursively check other cells.
     *
     *
     * My implementation, idea is taken from
     * https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid/solutions/4024629/recursion-brute-force-c-java/
     *
     * Time: The maximum depth of recursion is O(n^2). For each recursive call, there are ~3 possibilities for moves from empty cells.
     * Therefore, the overall time complexity is O(3 ^ (n^2)).
     *
     * Space: O(N^2) max depth of recursion
     * */
    public int minimumMoves(int[][] grid) {
        // base case
        int emptyCount = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i][j] == 0) emptyCount ++;

        if (emptyCount == 0) return 0;

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] > 0) continue; // cell is not empty, look for the next empty

                for (int ni = 0; ni < 3; ni++) {
                    for (int nj = 0; nj < 3; nj++) {
                        if (grid[ni][nj] <= 1) continue; // if the next cell has <= than 1 item skip it

                        int dist = Math.abs(i - ni) + Math.abs(j - nj);
                        grid[ni][nj] -= 1;
                        grid[i][j] += 1;
                        res = Math.min(res, dist + minimumMoves(grid)); // compare current best result with the case when we take from specific cell [ni, nj]
                        grid[ni][nj] += 1; // return grid state to previous state. We can try to take stone from another cell for better result
                        grid[i][j] -= 1;
                    }
                }
            }
        }
        return res;
    }





    /*
    Another interesting solution, using BFS
    https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid/solutions/4024683/java-bfs-easy-solution/

    Initial State: Convert the 2D grid into a 1D representation. This simplification makes it easier to iterate and manipulate the state of the grid.
    Target State: The final configuration that we desire is a grid with a single stone in every cell. So, the target is represented as a 1D array of nines.
    BFS: Start BFS with the initial state. At each step, for every cell that has more than one stone, try to move one stone to its adjacent cell. Keep track of the visited states using a HashSet to avoid unnecessary repetition.
    Generating Adjacent Cells: For any given cell index, its adjacent cells can be deduced based on its position (whether it's on an edge or not). This is done using the helper function getAdjacent.
    End Condition: The BFS continues until the current state matches the target state or the BFS completes without finding a solution.

    Time complexity: O(N!)  - as long as N<=9 it's good one too
    Space complexity: O(N!)
    * **/
    public int minimumMoves2222(int[][] grid) {
        int[] start = new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start[i * 3 + j] = grid[i][j];
            }
        }

        int[] target = {1, 1, 1, 1, 1, 1, 1, 1, 1};

        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(Arrays.toString(start));

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // process all layer, all items from previous iteration
                int[] curr = queue.poll();

                if (Arrays.equals(curr, target)) {
                    return moves;
                }

                for (int j = 0; j < 9; j++) {
                    if (curr[j] > 1) {      // try to move to adjacent cell. We do not know from which cell to start and to which cell to push, so try all variants
                        for (int next : getAdjacent(j)) {
                            int[] newState = curr.clone();
                            newState[j]--;
                            newState[next]++;
                            if (!visited.contains(Arrays.toString(newState))) {
                                visited.add(Arrays.toString(newState));
                                queue.offer(newState);
                            }
                        }
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    private List<Integer> getAdjacent(int index) {
        List<Integer> adjacent = new ArrayList<>();

        if (index % 3 != 0) adjacent.add(index - 1);
        if (index % 3 != 2) adjacent.add(index + 1);
        if (index / 3 != 0) adjacent.add(index - 3);
        if (index / 3 != 2) adjacent.add(index + 3);

        return adjacent;
    }

}
