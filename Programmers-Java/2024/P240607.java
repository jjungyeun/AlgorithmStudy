class P240607 {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] dp = new int[N][N];

        // 맨 밑층의 값으로 초기화
        for(int i=0; i<N; i++) {
            dp[N-1][i] = triangle[N-1][i];
        }

        // 한 층씩 올라오며, 왼쪽아래와 오른쪽아래의 현재까지의 합 중 더 큰 값을 가지고 이번 합 계산
        for(int depth = N-2; depth >= 0; depth--) {
            for(int i=0; i<=depth; i++) {
                int nowValue = triangle[depth][i];
                dp[depth][i] = Integer.max(dp[depth+1][i], dp[depth+1][i+1]) + nowValue;
            }
        }

        // 꼭대기 층에 계산된 합을 정답으로 반환
        return dp[0][0];
    }
}