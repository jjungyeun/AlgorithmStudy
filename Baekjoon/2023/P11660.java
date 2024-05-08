import java.util.Scanner;

class P11660 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int TC = sc.nextInt();
        long[][] S = new long[N+1][N+1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                S[r][c] = S[r-1][c] + S[r][c-1] + sc.nextInt() - S[r-1][c-1];
            }
        }

        for (int tc = 0; tc < TC; tc++) {
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            long ans = S[r2][c2] - S[r1-1][c2] - S[r2][c1-1] + S[r1-1][c1-1];
            System.out.println(ans);
        }
    }
}