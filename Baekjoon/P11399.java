import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bw.readLine());
        int[] persons = new int[N];
        StringTokenizer st = new StringTokenizer(bw.readLine());
        for (int i = 0; i < N; i++) {
            persons[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            int destIdx = i;
            for (int j = i-1; j >= 0; j--) {
                if (persons[j] > persons[i]) {
                    destIdx = j;
                }
            }
            shift(persons, i, destIdx);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += (persons[i] * (N-i));
        }
        System.out.println(ans);
    }

    private static void shift(int[] arr, int start, int dest) {
        int tmp = arr[start];
        for (int idx = start; idx > dest; idx--) {
            arr[idx] = arr[idx-1];
        }
        arr[dest] = tmp;
    }
}
