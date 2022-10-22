class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        last_idx = dict()
        
        for idx, num in enumerate(nums):
            if num in last_idx:
                if idx - last_idx[num] <= k:
                    return True
            last_idx[num] = idx
        
        return False
