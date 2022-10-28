class Solution:
    def largestOverlap(self, img1: List[List[int]], img2: List[List[int]]) -> int:
        answer = 0
        N = len(img1)
        
        # brute-force
        for dr in range(-N+1, N):
            for dc in range(-N+1, N):
                overlap = 0
                
                # shifted range
                r_min1, r_max1 = (-dr, N) if dr <= 0 else (0, N-dr)
                c_min1, c_max1 = (-dc, N) if dc <= 0 else (0, N-dc)
                r_min2, r_max2 = (0, N+dr) if dr <= 0 else (dr, N)
                c_min2, c_max2 = (0, N+dc) if dc <= 0 else (dc, N)
                
                # shift and compare
                for row1, row2 in zip(img1[r_min1:r_max1], img2[r_min2:r_max2]):
                    for bit1, bit2 in zip(row1[c_min1:c_max1], row2[c_min2:c_max2]):
                        if bit1 == bit2 == 1:
                            overlap += 1
            
                answer = max(answer, overlap)
        return answer
