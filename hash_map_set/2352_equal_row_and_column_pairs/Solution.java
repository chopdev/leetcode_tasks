/* 
https://leetcode.com/problems/equal-row-and-column-pairs/description/?envType=study-plan-v2&envId=leetcode-75

2352. Equal Row and Column Pairs

Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

 

Example 1:


Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
Example 2:


Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
1 <= grid[i][j] <= 105
 */

class Solution {

    // O(R*C) time and space
    public int equalPairs(int[][] grid) {
        Map<List<Integer>, Integer> rows = new HashMap<>(); // row to count of the same rows
        Map<List<Integer>, Integer> columns = new HashMap<>(); // column to count of the same rows

        for (int[] row : grid) {
            List<Integer> key = Arrays.stream(row).boxed().collect(Collectors.toList());
            rows.computeIfAbsent(key, k -> 0);
            rows.put(key, rows.get(key) + 1);
        }

        
        for (int colInd = 0; colInd < grid[0].length; colInd++) {
            List<Integer> column = new ArrayList<>();
            for (int rInd= 0; rInd < grid.length; rInd++) {
                column.add(grid[rInd][colInd]);
            }
            columns.computeIfAbsent(column, k -> 0);
            columns.put(column, columns.get(column) + 1);
        }

        System.out.println(columns);
        System.out.println(rows);

        int result = 0;
        for (List<Integer> row : rows.keySet()) {
            result += (columns.get(row) == null ? 0 : columns.get(row) * rows.get(row));
        }

        return result;
    }


// more concise
// O(R*C) time and space
    public int equalPairs(int[][] grid) {
        HashMap<String,Integer> freqmap = new HashMap<>();
        int pairs = 0;
        for(int [] row : grid){
            freqmap.merge(Arrays.toString(row), 1, Integer::sum);
        }
        for(int i=0;i<grid[0].length;i++){
            int col[] = new int[grid.length];
            for(int j=0;j<grid.length;j++){
                col[j] = grid[j][i];
            }
            pairs+=freqmap.getOrDefault(Arrays.toString(col),0);
        }
        return pairs;
    }
}