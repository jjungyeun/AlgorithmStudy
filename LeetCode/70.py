class Solution:
    # slove with tabulation
    def climbStairs1(self, n: int) -> int:
        dp = collections.defaultdict(int)
        dp[1] = 1
        dp[2] = 1
        
        for i in range(1, n):
            dp[i+1] += dp[i]
            dp[i+2] += dp[i]
        
        return dp[n]
      
    # slove with memoization
    dp = collections.defaultdict(int)
    def climbStairs2(self, n: int) -> int:
        if n <= 2:
            self.dp[n] = n
        elif self.dp[n] == 0:
            self.dp[n] = self.climbStairs2(n-1) + self.climbStairs2(n-2)
        
        return self.dp[n]
