import java.util.Scanner;

class P11659 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int TC = sc.nextInt();
        long[] S = new long[N+1];
        for (int i = 1; i <= N; i++) {
            S[i] = S[i-1] + sc.nextInt();
        }

        for (int tc = 0; tc < TC; tc++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            long ans = S[j] - S[i-1];
            System.out.println(ans);
        }
    }
}