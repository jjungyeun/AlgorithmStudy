import java.io.*;

public class P10989 {
    public static void main(String[] args) throws IOException {
        // 카운팅 정렬
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] counts = new int[10000+1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            counts[arr[i]]++;
        }

        for (int i = 1; i < 10000; i++) {
            counts[i+1] += counts[i];
        }

        int[] res = new int[N];
        for (int i = N-1; i >= 0; i--) {
            int num = arr[i];
            int idx = (counts[num]--) - 1;
            res[idx] = num;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int num : res) {
            bw.write(num + "\n");
        }
        bw.flush();
        bw.close();
    }
}
