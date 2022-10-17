class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        answer = [[1]]
        for h in range(1, numRows):
            row = [1]
            for idx in range(1, h):
                row.append(answer[h-1][idx-1] + answer[h-1][idx])
            row.append(1)
            answer.append(row)
        
        return answer
