class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        counter = collections.Counter(nums)
        num, cnt = counter.most_common(1)[0]
        return num
