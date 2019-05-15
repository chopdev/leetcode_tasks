/**
 959. Regions Cut By Slashes
 https://leetcode.com/problems/regions-cut-by-slashes/

 In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.
 These characters divide the square into contiguous regions.
 (Note that backslash characters are escaped, so a \ is represented as "\\".)
 Return the number of regions.

 Example 1:

 Input:
 [
 " /",
 "/ "
 ]
 Output: 2
 Explanation: The 2x2 grid is as follows:


 Example 2:

 Input:
 [
 " /",
 "  "
 ]
 Output: 1
 Explanation: The 2x2 grid is as follows:


 Example 3:

 Input:
 [
 "\\/",
 "/\\"
 ]
 Output: 4
 Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
 The 2x2 grid is as follows:


 Example 4:

 Input:
 [
 "/\\",
 "\\/"
 ]
 Output: 5
 Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
 The 2x2 grid is as follows:


 Example 5:

 Input:
 [
 "//",
 "/ "
 ]
 Output: 3
 Explanation: The 2x2 grid is as follows:



 Note:

 1 <= grid.length == grid[0].length <= 30
 grid[i][j] is either '/', '\', or ' '.
 */
public class Solution {

    // Not mine great solution
    // https://leetcode.com/problems/regions-cut-by-slashes/discuss/205719/Mark-the-boundary-and-then-the-problem-become-Number-of-Islands-(dfs-or-bfs)
    public int regionsBySlashes(String[] grid) {
        int[][] field = new int[grid.length*3][grid.length*3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(grid[i].charAt(j) == '/')
            }
        }
    }
}
