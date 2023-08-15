import java.io.*;
import java.util.StringTokenizer;

public class P1850 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A1 = Long.parseLong(st.nextToken());
        long B1 = Long.parseLong(st.nextToken());

        // ! 1의 개수를 나타내는 A1, B1의 최대공약수를 구하면, A와 B의 최대공약수의 1의 개수가 나온다.
        long gcd = GCD(A1, B1);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (gcd > 0) {
            bw.write("1");
            gcd--;
        }
        bw.flush();
        bw.close();
    }

    private static long GCD(long A, long B) {
        long large = Long.max(A, B);
        long small = Long.min(A, B);

        while (large > 0 && small > 0) {
            long mod = large % small;
            if (mod == 0) break;
            large = small;
            small = mod;
        }

        return small;
    }
}
