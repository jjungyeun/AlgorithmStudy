import java.io.*;
import java.util.*;

class P1929_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] isNotPrime = new boolean[M+1];
        isNotPrime[1] = true;
        for (int i = 1; i*i <= M; i++) {
            if (isNotPrime[i]) {
                continue;
            }

            for (int j = 2; i*j <= M; j++) {
                isNotPrime[i*j] = true;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = N; i <= M; i++) {
            if (!isNotPrime[i]) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}