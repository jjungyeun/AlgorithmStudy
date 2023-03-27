class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        H, W = len(grid), len(grid[0])

				# 1행 처리
        for c in range(1, W):
            grid[0][c] += grid[0][c-1]
        
				# 1열 처리
        for r in range(1, H):
            grid[r][0] += grid[r-1][0]

        for r in range(1, H):
            for c in range(1, W):
                grid[r][c] += min(grid[r][c-1], grid[r-1][c])
        
        return grid[-1][-1]
