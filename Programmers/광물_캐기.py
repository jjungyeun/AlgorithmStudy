#### 1 ####
import collections

def solution(picks, minerals):
    answer = 25 * 50
    names = {"diamond": 0, "iron": 1, "stone": 2}
    fatigues = [[1,1,1], [5,1,1], [25,5,1]]
    
    queue = collections.deque()
    # (남은 곡괭이 수 array, 현재 광물, 현재까지 피로도)
    queue.append((picks,0,0))
    
    while queue:
        rest_picks, cur, cur_fatigue = queue.popleft()
        
        # 광물을 모두 캤거나, 곡괭이를 모두 사용했으면 answer와 비교하여 갱신
        if cur >= len(minerals) or rest_picks == [0,0,0]:
            answer = min(answer, cur_fatigue)
            continue
        
        # 현재까지의 피로도가 answer보다 크거나 같으면 pass
        if cur_fatigue >= answer:
            continue
        
        # 각 곡괭이를 최대로 사용했을 때의 상태를 큐에 저장
        for pick, rest in enumerate(rest_picks):
            if rest == 0:
                continue
            
            point, fatigue = cur, cur_fatigue

            for _ in range(5):
                if point >= len(minerals):
                    break
                mineral = names[minerals[point]]
                fatigue += fatigues[pick][mineral]
                point += 1
                
            next_rest_picks = [r for r in rest_picks]
            next_rest_picks[pick] -= 1
            queue.append((next_rest_picks, point, fatigue))
    
    return answer

#### 2 ####
import collections

def solution(picks, minerals):
    answer = 25 * 50
    names = {"diamond": 0, "iron": 1, "stone": 2}
    fatigues = [[1,1,1], [5,1,1], [25,5,1]]
    
    queue = collections.deque()
    # (남은 곡괭이 수 array, 현재 곡괭이, 현재 곡괭이 잔여 횟수, 현재 광물, 현재까지 피로도)
    queue.append((picks,-1,0,0,0))
    
    while queue:
        rest_picks, cur_pick, cur_pick_rest, cur, cur_fatigue = queue.popleft()
        
        # 광물을 모두 캤거나, 곡괭이를 모두 사용했으면 answer와 비교하여 갱신
        if cur >= len(minerals) or (rest_picks == [0,0,0] and cur_pick_rest == 0):
            answer = min(answer, cur_fatigue)
            continue
        
        # 현재까지의 피로도가 answer보다 크거나 같으면 pass
        if cur_fatigue >= answer:
            continue
        
        mineral = names[minerals[cur]]
        
        # 현재 사용중인 곡괭이를 더 사용할 수 있는 경우
        if cur_pick_rest > 0:
            next_rest_picks = [rest for rest in rest_picks]
            queue.append((next_rest_picks, cur_pick, cur_pick_rest-1, cur+1, cur_fatigue + fatigues[cur_pick][mineral]))
        
        # 새로운 곡괭이를 선택해야 하는 경우, 각 곡괭이에 대한 경우를 큐에 넣음
        else:
            for pick, rest in enumerate(rest_picks):
                if rest == 0:
                    continue

                next_rest_picks = [rest for rest in rest_picks]
                next_rest_picks[pick] -= 1
                queue.append((next_rest_picks, pick, 4, cur+1, cur_fatigue + fatigues[pick][mineral]))
    
    return answer
