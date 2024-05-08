class P231206 {

    /*
      풀이1: 슬라이딩 윈도우
      (효율성 13번 실패)
    */
    public int solution1(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        int lastMax = answer, lastIdx = -1;
        
        // k 사이즈의 슬라이딩 윈도우
        for(int l=0; l <= stones.length - k; l++) {
            int r = l + k - 1;
            int localMax, localMaxIdx;
            
            // 최대값이 범위를 벗어난 경우 다시 최대값 구함 (O(k))
            if (lastIdx < l) {
                localMax = 0; localMaxIdx = l;
                for(int i = l; i <= r; i++) {
                    int newVal = stones[i];
                    if (newVal >= localMax) {
                        localMax = newVal;
                        localMaxIdx = i;
                    }
                }
            } else { // 최대값이 아직 범위 내에 있는 경우 새로 추가된 값과 비교
                int newVal = stones[r];
                if (newVal >= lastMax) {
                    localMax = newVal;
                    localMaxIdx = r;
                } else {
                    localMax = lastMax;
                    localMaxIdx = lastIdx;
                }
            }
            
            // 현재 윈도우의 최대값으로 갱신
            lastMax = localMax;
            lastIdx = localMaxIdx;
            answer = Integer.min(answer, localMax);
        }
        
        return answer;
    }

  
  /*
    풀이2: 이진탐색
  */
  public int solution2(int[] stones, int k) {
        int answer = bs(stones, 0, 200000001, k);
        return canCross(stones, answer, k)? answer : answer-1;
    }
    
    // 중간번호가 건널 수 있으면 오른쪽, 건널 수 없으면 왼쪽 탐색
    private int bs(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return l;
        }
        
        int mid = (l + r) / 2;
        if (canCross(arr, mid, k)) {
            return bs(arr, mid+1, r, k);
        } else {
            return bs(arr, l, mid-1, k);
        }
    }
    
    // M번이 건널 수 있는지 확인
    // M-1번까지 건넌 상태에서 0이하값이 연속으로 k개 이상 나오는지 확인
    private boolean canCross(int[] stones, int M, int k) {
        int zeroCnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - (M - 1) <= 0) {
                zeroCnt++;
                if (zeroCnt >= k) {
                    return false;
                }
            } else {
                zeroCnt = 0;
            }
        }
        return true;
    }
}
