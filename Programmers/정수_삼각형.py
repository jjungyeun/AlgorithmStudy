def solution(triangle):
    for h in range(1, len(triangle)):
        triangle[h][0] += triangle[h-1][0]
        triangle[h][-1] += triangle[h-1][-1]
        for idx in range(1, h):
            triangle[h][idx] += max(triangle[h-1][idx], triangle[h-1][idx-1])
    return max(triangle[-1])
