import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class P1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] nums = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(nums);

        int ans = 0;
        for (int idx = 0; idx < N; idx++) {
            long k = nums[idx];
            int i = 0, j = N-1;
            while (i < j) {
                long sum = nums[i] + nums[j];
                if (sum == k) {
                    if (i != idx && j != idx){
                        ans++;
                        break;
                    } else if (i == idx){
                        i++;
                    } else {
                        j--;
                    }
                } else if (sum < k) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        System.out.println(ans);
        br.close();
    }
}