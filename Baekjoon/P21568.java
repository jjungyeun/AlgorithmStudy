import java.util.Scanner;

public class P21568 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();

        long gcd = GCD(A, B);
        if (C % gcd == 0) {
            long K = C / gcd;
            long[] xy = execute(A, B);
            System.out.print(K * xy[0] + " " + K * xy[1]);
        } else {
            System.out.print(-1);
        }
    }

    private static long[] execute(long A, long B) {
        if (B == 0) {
            return new long[]{1, 0};
        }
        long div = A / B;
        long mod = A % B;
        long[] before = execute(B, mod);
        return new long[]{before[1], before[0] - (before[1] * div)};
    }

    private static long GCD(long A, long B) {
        while (true) {
            long mod = A % B;
            if (mod == 0) break;
            A = B;
            B = mod;
        }
        return B;
    }
}
