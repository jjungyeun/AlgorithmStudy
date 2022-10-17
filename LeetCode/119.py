class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        pre_row = [1]
        
        for r in range(1, rowIndex+1):
            current_row = [1]
            for i in range(1, r):
                current_row.append(pre_row[i-1] + pre_row[i])
            current_row.append(1)
            pre_row = current_row
        
        return pre_row
