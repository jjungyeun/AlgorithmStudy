class Solution:
    def largestPerimeter(self, nums: List[int]) -> int:
        nums.sort(reverse=True)
        
        for i in range(len(nums)-2):
            l1, l2, l3 = nums[i], nums[i+1], nums[i+2]
            # check it is triangle
            if l1 < l2 + l3:
                return l1+l2+l3
        
        return 0
