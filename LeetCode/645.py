class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        answer = []
        counter = {num for num in range(1, len(nums)+1)}
        
        for num in nums:
            if num in counter:
                counter.remove(num)
            else:
                answer.append(num)
        
        for num in counter.keys():
            answer.append(num)
        
        return answer
