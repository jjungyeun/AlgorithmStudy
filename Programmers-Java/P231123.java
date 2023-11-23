import java.util.Arrays;

public class P231123 {
    public static void main(String[] args) {
        int[] answer = solution(2, 8);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(int n, int s) {
        int[] answer = new int[n];
        int div = s / n;
        int mod = s % n;

        // 최고의 집합이 존재하지 않는 경우
        if (div < 1) {
            return new int[]{-1};
        }

        // 나머지만큼 1 더해주기
        for (int i = 0; i < n; i++) {
            answer[i] = i < n - mod ? div : div + 1;
        }

        return answer;
    }
}
