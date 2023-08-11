import java.io.IOException;
import java.util.Scanner;

public class P1456 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        long A = scanner.nextLong();
        long B = scanner.nextLong();

        int N = (int) Math.sqrt(B);

        // false면 소수. true면 소수 아님.
        boolean[] isNotPrime = new boolean[N+1];
        isNotPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (!isNotPrime[i]) {
                for (int j = 2; i * j <= N ; j++) {
                    isNotPrime[i * j] = true;
                }
            }
        }

        int cnt = 0;
        for (int primeNum = 2; primeNum <= N ; primeNum++) {
            if (!isNotPrime[primeNum]) {
                for (int K = 2; Math.pow(primeNum, K) <= B; K++) {
                    if (Math.pow(primeNum, K) >= A) cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
