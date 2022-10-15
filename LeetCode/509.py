class Solution:
  
    # with recursion
    def fib1(self, n: int) -> int:
        if n == 0 or n == 1:
            return n
        return self.fib(n-1) + self.fib(n-2)
      
    # recursion with memoization
    dp = collections.defaultdict(int)
    def fib2(self, n: int) -> int:
        if n == 0 or n == 1:
            self.dp[n] = n
        
        elif self.dp[n] == 0:
            self.dp[n] = self.fib(n-1) + self.fib(n-2)
        
        return self.dp[n]
    
    # recursion with tabulation
    def fib(self, n: int) -> int:
        dp = collections.defaultdict(int)
        dp[0] = 0
        dp[1] = 1
        
        for i in range(2, n+1):
            dp[i] = dp[i-1] + dp[i-2]
        
        return dp[n]
