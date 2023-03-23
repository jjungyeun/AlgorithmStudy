class Solution:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        if len(connections) < n-1:
            return -1

        adj_dict = {key: [] for key in range(n)}
        for s, d in connections:
            adj_dict[s].append(d)
            adj_dict[d].append(s)

        group_cnt = 0
        visited = [0] * n
        def dfs(here: int):
            for dest in adj_dict[here]:
                if visited[dest] == 0:
                    visited[dest] = group_cnt
                    dfs(dest)
        
        for com in range(n):
            if visited[com] == 0:
                group_cnt += 1
                visited[com] = group_cnt
                dfs(com)
        
        return group_cnt -1
