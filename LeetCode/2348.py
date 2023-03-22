class Solution:

    def zeroFilledSubarray(self, nums: List[int]) -> int:
        ans = 0
        zero_cnt = 0

        def get_subarray_cnt(size):
            return (size * (size + 1)) // 2
        
        for num in nums:
            if num == 0:
                zero_cnt += 1
            else:
                ans += get_subarray_cnt(zero_cnt)
                zero_cnt = 0
        
        ans += get_subarray_cnt(zero_cnt)
        return ans
