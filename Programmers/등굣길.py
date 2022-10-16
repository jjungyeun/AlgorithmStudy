def solution(m, n, puddles):
    DIV = 1000000007
    DP = [[0 for _ in range(m+1)] for _ in range(n+1)]
    DP[1][1] = 1
    for c, r in puddles:
        DP[r][c] = -1
    
    # when col is 1
    for c in range(2, m+1):
        if DP[1][c] == 0:
            DP[1][c] = DP[1][c-1] if DP[1][c-1] != -1 else 0
    
    # when row is 1
    for r in range(2, n+1):
        if DP[r][1] == 0:
            DP[r][1] = DP[r-1][1] if DP[r-1][1] != -1 else 0
        
    # get answer with tabulation
    for r in range(2, n+1):
        for c in range(2, m+1):
            if DP[r][c] == 0:
                DP[r][c] += DP[r-1][c] if DP[r-1][c] != -1 else 0
                DP[r][c] += DP[r][c-1] if DP[r][c-1] != -1 else 0
                DP[r][c] %= DIV
        
    return DP[n][m]
