import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1920 {

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }
        Arrays.sort(nums);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            if (binarySearch(target, 0, N-1)) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean binarySearch(int target, int startIdx, int endIdx) {
        if (startIdx > endIdx) return false;

        int mid = (startIdx + endIdx) / 2;
        if (nums[mid] == target) {
            return true;
        } else if (target < nums[mid]) {
            return binarySearch(target, startIdx, mid-1);
        } else {
            return binarySearch(target, mid+1, endIdx);
        }
    }
}
