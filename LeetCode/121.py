class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        profit, lowest = 0, (-1, float('inf'))
        
        for day, price in enumerate(prices):
            if price > lowest[1]:
                new_profit = price - lowest[1]
                profit = max(profit, new_profit)
            else:
                lowest = (day, price)
        
        return profit
