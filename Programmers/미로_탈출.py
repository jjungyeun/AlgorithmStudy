import collections


# 같은 maps 배열을 여러번 사용하기 위해 방문한 곳을 표시하는 mark 파라미터를 같이 받는다.
def bfs(start, dest, maps, mark):
    H, W = len(maps), len(maps[0])
    dr, dc = [1,-1,0,0], [0,0,1,-1]
    
    start_r, start_c = start
    queue = collections.deque()
    queue.append((start_r, start_c, 0))
    
    while queue:
        here_r, here_c, here_dist = queue.popleft()
        
        if (here_r, here_c) == dest:
            return here_dist
        
        if maps[here_r][here_c] != 'X' and maps[here_r][here_c] != mark:
            maps[here_r][here_c] = mark
            for i in range(4):
                next_r, next_c = here_r + dr[i], here_c + dc[i]
                if 0 <= next_r < H and 0 <= next_c < W:
                    queue.append((next_r, next_c, here_dist + 1))
    
    return -1
    

def solution(maps):
		# maps를 visited 배열로 사용하기 위해 각 row를 char array로 변환한다.
    maps = [[ s for s in row] for row in maps]
    H, W = len(maps), len(maps[0])
    start, lever, exit = (0,0), (0,0), (0,0)
    for r, row in enumerate(maps):
        for c, s in enumerate(row):
            if s == 'S':
                start = (r, c)
            if s == 'L':
                lever = (r, c)
            if s == 'E':
                exit = (r, c)
            
    to_lever = bfs(start, lever, maps, 'V1')
    if to_lever == -1:
        return -1
    to_exit = bfs(lever, exit, maps, 'V2')
    
    return to_lever + to_exit if to_exit != -1 else -1
