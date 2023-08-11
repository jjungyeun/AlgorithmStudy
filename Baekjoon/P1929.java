import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class P1929 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();

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

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = M; i <= N ; i++) {
            if (!isNotPrime[i]) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
