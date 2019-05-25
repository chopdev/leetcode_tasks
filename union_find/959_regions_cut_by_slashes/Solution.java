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


 SOLUTION
 https://leetcode.com/problems/regions-cut-by-slashes/discuss/205719/Mark-the-boundary-and-then-the-problem-become-Number-of-Islands-(dfs-or-bfs)
 */
public class Solution {


    /**
      Not mine great solution
     O(N^2) time and space
     my interpretation of this great explanation
     https://leetcode.com/problems/regions-cut-by-slashes/discuss/205719/Mark-the-boundary-and-then-the-problem-become-Number-of-Islands-(dfs-or-bfs)

     First mark the boundary by painting [n * 3][m * 3] grid, then use the algorithm count number of island (leetcode200) using either bfs or dfs
     Using a 3X3 array to represent '/' or '\'
     0 0 1
     0 1 0
     1 0 0

     1 0 0
     0 1 0
     0 0 1

     On dfs mark visited cells + each dfs is a separate zone
    * */
    public int regionsBySlashes(String[] grid) {
        int[][] field = new int[grid.length*3][grid.length*3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(grid[i].charAt(j) == '/')
                    field[i*3+2][j*3] = field[i*3+1][j*3+1] = field[i*3][j*3+2] = 1;
                else if(grid[i].charAt(j) == '\\')
                    field[i*3][j*3] = field[i*3+1][j*3+1] = field[i*3+2][j*3+2] = 1;
            }
        }

        int regionsCount = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == 0) {
                    dfs(i, j, field);
                    regionsCount++;
                }
            }
        }

        return regionsCount;
    }

    private void dfs(int i, int j, int[][] field) {
        if(i < 0 || i >= field.length || j < 0 || j >= field[0].length || field[i][j] > 0) return;

        field[i][j] = 2;
        dfs(i+1, j, field);
        dfs(i-1, j, field);
        dfs(i, j+1, field);
        dfs(i, j-1, field);
    }


    /*
        // Alternative for DFS
        void dfs2222(int[][] g, int i, int j){
            int n = g.length, m = g[0].length;
            if(i<0 || i>=n || j<0 || j>=m || g[i][j]==1) return;
            g[i][j]=1;
            int d[] = {0,-1,0,1,0};
            for(int k=0;k<4;k++){
                dfs2222(g,i+d[k],j+d[k+1]);
            }
        }
    * */


}
