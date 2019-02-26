import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
54. Spiral Matrix
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
* */
public class Solution {
    int rowCount;
    int colCount;
    Move[] moves;

    public Solution() {
        moves = new Move[4];
        moves[0] = new Move(1, 0);
        moves[1] = new Move(0, 1);
        moves[2] = new Move(-1, 0);
        moves[3] = new Move(0, -1);
    }

    private class Move {
        int col;
        int row;

        Move(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }

    /*
    My solution
    O(N^2) time and space (because of recursion depth)
    Idea is to do dfs, but we need to preserve direction that we used to move before
    * */
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new ArrayList<Integer>();

        rowCount = matrix.length;
        colCount = matrix[0].length;
        List<Integer> res = new ArrayList<Integer>();
        dfs(matrix, 0, 0, 0, res, new HashSet<Integer>());
        return res;
    }

    private boolean dfs(int[][] matrix, int row, int col, int direction, List<Integer> res, HashSet<Integer> visited) {
        if(row < 0 || col < 0 || row >= rowCount || col >= colCount) return false;
        int id = colCount * row + col;
        if(visited.contains(id)) return false;

        visited.add(id);
        res.add(matrix[row][col]);
        if(res.size() == colCount * rowCount) return true;

        for (int i = 0; i < moves.length; i++) {
            if(dfs(matrix, row + moves[direction].row, col + moves[direction].col, direction, res, visited)) return true;
            direction++;
            if(direction >= moves.length) direction=0;
        }

        return false;
    }
}
