class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        mod_dict = dict()
        
        cur = 0
        for idx, num in enumerate(nums):
            # get mod of sum
            cur += num
            cur %= k
            
            # if sum is multiple of k
            if idx != 0 and cur == 0:
                return True
            
            # if sum of subset is multiple of k
            if cur in mod_dict:
                last_idx = mod_dict[cur]
                if idx - last_idx >= 2:
                    return True
            else:
                mod_dict[cur] = idx
        
        return False
