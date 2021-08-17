# A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
# The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
# Now consider if some obstacles are added to the grids. How many unique paths would there be?
# An obstacle and space is marked as 1 and 0 respectively in the grid.

# Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
# Output: 2
# Explanation: There is one obstacle in the middle of the 3x3 grid above.
# There are two ways to reach the bottom-right corner:
# 1. Right -> Right -> Down -> Down
# 2. Down -> Down -> Right -> Right

# https://leetcode.com/problems/unique-paths-ii/

from typing import List

# My solution, O(N*M) time, O(N*M) space
class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        if not obstacleGrid: return 0
        memo = {}
        found = self.dfs(obstacleGrid, memo, len(obstacleGrid), len(obstacleGrid[0]), 0, 0)
        if found: return memo[(0, 0)]
        return 0

    def dfs(self, grid: List[List[int]], memo: dict[tuple, int], row_count: int, col_count: int, i: int, j: int) -> bool:
        if (i == row_count - 1 and j == col_count - 1):
            memo[(i, j)] = 1 if grid[i][j] == 0 else 0
            return True
        
        if (i >= row_count or j >= col_count or grid[i][j] == 1):
            return False
        
        if (i, j) in memo:
            return True

        memo[(i, j)] = 0
        foundRight = self.dfs(grid, memo, row_count, col_count, i + 1, j)
        foundDown = self.dfs(grid, memo, row_count, col_count, i, j + 1)

        if foundRight: memo[(i, j)] += memo[(i + 1, j)]
        if foundDown: memo[(i, j)] += memo[(i, j + 1)]

        return foundRight or foundDown

### Not my solution
# https://leetcode.com/problems/unique-paths-ii/discuss/146073/Python-DP-beat-100-python-submissions
class Solution2222(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        dp = [[0]*len(obstacleGrid[0]) for _ in range(len(obstacleGrid))]
        dp[0][0] = 1 if obstacleGrid[0][0] == 0 else 0

        for i in range(len(obstacleGrid)):
            for j in range(len(obstacleGrid[0])):
                if obstacleGrid[i][j] == 1:
                    dp[i][j] = 0
                else:
                    if i-1>=0:
                        dp[i][j] += dp[i-1][j]
                    if j-1>=0:
                         dp[i][j] += dp[i][j-1]
        return dp[-1][-1]


#################################################

sol = Solution()
print(sol.uniquePathsWithObstacles([[0,0,0],[0,1,0],[0,0,0]])) # 2
print(sol.uniquePathsWithObstacles([[0,1],[0,0]])) # 1
print(sol.uniquePathsWithObstacles([[0,0],[0,0]])) # 2
print(sol.uniquePathsWithObstacles([[0,0,0],[0,0,0],[0,0,0]])) # 6
print(sol.uniquePathsWithObstacles([[0]])) # 1


#### Failed tests (but fixed)

print(sol.uniquePathsWithObstacles([[0,0],[0,1]])) # 0