import java.util.Scanner;

class P1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int oldSum = 0, maxScore = 0;
        for (int i = 0; i < N; i++) {
            int score = sc.nextInt();
            oldSum += score;
            maxScore = Math.max(maxScore, score);
        }
        double answer = (double) (oldSum * 100) / (maxScore * N);
        System.out.println(answer);
    }
}