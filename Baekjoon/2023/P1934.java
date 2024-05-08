import java.io.*;
import java.util.StringTokenizer;

public class P1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            bw.write(LCM(A, B) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int LCM(int A, int B) {
        return (A * B) / GCD(A,B);
    }

    private static int GCD(int A, int B) {
        int large = Integer.max(A, B);
        int small = Integer.min(A, B);

        while (large > 0 && small > 0) {
            int mod = large % small;
            if (mod == 0) break;
            large = small;
            small = mod;
        }

        return small;
    }
}
