import java.util.Scanner;

class P10986 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long[] S = new long[N+1];
        long[] cnt = new long[M];
        for (int i = 1; i <= N; i++) {
            long rest = (S[i - 1] + sc.nextLong()) % M;
            S[i] = rest;
            cnt[(int) rest] ++;
        }
        long ans = cnt[0];
        for (int i = 0; i < M; i++) {
            ans += (cnt[i] * (cnt[i] - 1)) / 2;
        }
        System.out.println(ans);
    }
}