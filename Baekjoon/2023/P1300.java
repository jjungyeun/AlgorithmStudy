import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1300 {

    private static long N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        K = Long.parseLong(br.readLine());

        System.out.println(getKth(1, K));
    }

    private static long getKth(long startIdx, long endIdx) {
        if (startIdx > endIdx) return startIdx;
        long mid = (startIdx + endIdx) / 2;
        long cnt = getCntSmallerThan(mid);
        if (cnt < K) {
            return getKth(mid+1, endIdx);
        } else {
            return getKth(startIdx, mid-1);
        }
    }

    private static long getCntSmallerThan(long num) {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            cnt += Math.min(N, num / i);
        }
        return cnt;
    }
}
