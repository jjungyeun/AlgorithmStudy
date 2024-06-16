class P2145 {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int preVal = 0;
        int maxVal = 0, minVal = 0;
        int maxDiff = upper - lower;

        // diff를 하나씩 살펴보며, hidden의 최댓값과 최솟값을 구함
        for(int i=0; i<differences.length; i++) {
            int nowVal = preVal + differences[i];
            maxVal = Integer.max(maxVal, nowVal);
            minVal = Integer.min(minVal, nowVal);

            // 만약 현재의 최댓값과 최솟값의 차이가
            // 차이값의 최대를 벗어났다면 바로 0 반환하고 종료
            if (maxVal - minVal > maxDiff) {
                return 0;
            }

            preVal = nowVal;
        }

        // 기준값(hidden[0])의 최댓값과 최솟값을 구함
        int max = upper - maxVal;
        int min = lower - minVal;

        // 최솟값에서 최댓값까지의 개수를 정답으로 반환
        return max >= min ? max - min + 1 : 0;
    }
}