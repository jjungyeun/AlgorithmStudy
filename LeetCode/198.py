class Solution:
    # with tabulation
    def rob1(self, nums: List[int]) -> int:
        if len(nums) <= 2:
            return max(nums)
        
        dp = dict()
        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])
        
        for i in range(2, len(nums)):
            dp[i] = max(dp[i-1], dp[i-2] + nums[i])
        
        return dp[len(nums)-1]
      
    # with memoization
    def rob2(self, nums: List[int]) -> int:
      if len(nums) <= 2:
          return max(nums)

      dp = collections.defaultdict(int)
      dp[0] = nums[0]
      dp[1] = max(nums[0], nums[1])

      def robbing(n):
          if n not in dp:
                  dp[n] = max(robbing(n-1), robbing(n-2) + nums[n])
          return dp[n]

      return robbing(len(nums)-1)
