class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        visited = [False for _ in range(n+1)]
        adj_dict = {k:list() for k in range(1, n+1)}
        for source, dest, score in roads:
            adj_dict[source].append((dest, score))
            adj_dict[dest].append((source, score))
        
        def dfs(here: int, ans: int):
            res = ans
            # 연결된 모든 엣지의 점수를 비교
            for next, score in adj_dict[here]:
                res = min(res, score)
                if not visited[next]:
                    visited[next] = True
                    res = min(res, dfs(next,res))
            return res
            
        visited[1] = True
        return dfs(1, float('inf'))
