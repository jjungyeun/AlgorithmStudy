import java.io.*;

public class P10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] tmpArr;
        for (int i = 0; i < 5; i++) {
            int pos = (int) Math.pow(10, i);
            int[] bucket = new int[10];
            for (int num : arr) {
                int kthNum = (num / pos) % 10;
                bucket[kthNum]++;
            }

            for (int j = 1; j < 10; j++) {
                bucket[j] += bucket[j-1];
            }

            tmpArr = arr.clone();
            for(int l = N-1; l >= 0; l--) {
                int kthNum = (tmpArr[l] / pos) % 10;
                arr[bucket[kthNum]-1] = tmpArr[l];
                bucket[kthNum]--;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int num : arr) {
            bw.write(num + "\n");
        }
        bw.flush();
        bw.close();
    }
}
