# Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
# The robot can only move in two directions, right and down, but certain cells are "off limits" such that
# the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
# the bottom right.

#  0 0 0 0 0 0
#  0 1 0 1 0 0
#  1 0 0 1 0 0
#  0 1 0 1 0 0

from typing import List

def get_path(grid: List[List[int]]) -> List[int]:
    if not grid: return []
    res = []
    dfs(grid, res, set(), len(grid), len(grid[0]), 0, 0)
    return res

def dfs(grid: List[List[int]], res: List[tuple], visited: set[tuple], row_count: int, col_count: int, i: int, j: int) -> bool:
    if (i == row_count - 1 and j == col_count - 1):
        res.append((i, j))
        return True
    
    if (i >= row_count or j >= col_count or grid[i][j] == 1 or (i, j) in visited):
        return False

    res.append((i, j))
    visited.add((i, j))
    found = dfs(grid, res, visited, row_count, col_count, i + 1, j)
    if found: return True

    found = dfs(grid, res, visited, row_count, col_count, i, j + 1)
    if not found: res.pop()

    return found

########################################################################################

grid = [[0, 0, 0, 0, 0, 0], [0, 1, 0, 1, 0, 0], [1, 0, 0, 1, 0, 0], [0, 1, 0, 1, 0, 0]]
   
res = get_path(grid)
print(res)


print(get_path([[0]]))
print(get_path([[0, 0, 0]]))
print(get_path([[0, 1, 0]]))