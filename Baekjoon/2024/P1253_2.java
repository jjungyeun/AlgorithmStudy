import java.io.*;
import java.util.*;

class P1253_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int target = nums[i];
            int l = 0, r = N - 1;
            while (l < r) {
                if (l == i) {
                    l++;
                    continue;
                }

                if (r == i) {
                    r--;
                    continue;
                }

                int sum = nums[l] + nums[r];
                if (sum > target) {
                    r--;
                } else if (sum < target) {
                    l++;
                } else {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}