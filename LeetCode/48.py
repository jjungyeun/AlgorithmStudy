class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        N = len(matrix)
        for r, row in enumerate(matrix):
            for c in range(r, N-r-1):
                # rotate each cell
                cur_r, cur_c, cur_val = r, c, row[c]
                for _ in range(4):
                    cur_r, cur_c = cur_c, N-cur_r-1
                    matrix[cur_r][cur_c], cur_val = cur_val, matrix[cur_r][cur_c]
