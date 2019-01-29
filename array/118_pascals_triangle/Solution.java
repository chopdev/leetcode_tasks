import java.util.ArrayList;
import java.util.List;

/*
118. Pascal's Triangle
https://leetcode.com/problems/pascals-triangle/

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
* */
public class Solution {
    // My good solution
    // Time O(N^2), space O(N^2)
    public List<List<Integer>> generate(int numRows) {
        int[][] arr = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            arr[i] = new int[i + 1];
            arr[i][0] = 1;
            arr[i][i] = 1;
        }

        for (int row = 2; row < numRows; row++) {
            for (int col = 1; col < arr[row].length - 1; col++) {
                arr[row][col] = arr[row - 1][col - 1] + arr[row - 1][col];
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < arr[i].length; j++) {
                res.get(i).add(arr[i][j]);
            }
        }

        return res;
    }

    // Not mine solution
    // https://leetcode.com/problems/pascals-triangle/solution/
    public List<List<Integer>> generate2222(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // First base case; if user requests zero rows, they get zero rows.
        if (numRows == 0) {
            return triangle;
        }

        // Second base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum-1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }

    // Not mine
    // https://leetcode.com/problems/pascals-triangle/discuss/38125/Solution-in-Java
    public List<List<Integer>> generate3333(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if (numRows <=0){
            return triangle;
        }
        for (int i=0; i<numRows; i++){
            List<Integer> row =  new ArrayList<Integer>();
            for (int j=0; j<i+1; j++){
                if (j==0 || j==i){
                    row.add(1);
                } else {
                    row.add(triangle.get(i-1).get(j-1)+triangle.get(i-1).get(j));
                }
            }
            triangle.add(row);
        }
        return triangle;
    }
}
