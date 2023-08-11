public class P230811 {
    public static void main(String[] args) {
        int[] sticker = {1};
//        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};
//        int[] sticker = {1, 3, 2, 5, 4};
        System.out.println(solution(sticker));
    }

    // int[][][] DP
    //  index 0: 0번째 수 고름/안고름
    //  index 1: i번째 수
    //  index 2: i번째 수 고름/안고름
    public static int solution(int sticker[]) {
        int N = sticker.length;
        if (N == 1) return sticker[0];

        int[][][]  DP = new int[2][N][2];
        DP[1][0][1] = sticker[0];

        // 각 숫자를 고르는 경우, 안고르는 경우를 나눠서 최대값을 구함
        for (int firstChoice = 0; firstChoice <= 1; firstChoice++) {
            for (int numIdx = 1; numIdx < N; numIdx++) {
                DP[firstChoice][numIdx][0] = Integer.max(DP[firstChoice][numIdx-1][0], DP[firstChoice][numIdx-1][1]);
                DP[firstChoice][numIdx][1] = DP[firstChoice][numIdx-1][0] + sticker[numIdx];
            }
        }

        int maxWhenNoFirstChoice = Integer.max(DP[0][N - 1][1], DP[0][N - 1][0]);
        int maxWhenFirstChoice = DP[1][N - 1][0];
        return Integer.max(maxWhenFirstChoice, maxWhenNoFirstChoice);
    }
}
