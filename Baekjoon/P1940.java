import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class P1940 {
    public static void main(String[] args) throws IOException {
        int MAX_INGREDIENT_NUM = 100000;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        boolean[] arr = new boolean[MAX_INGREDIENT_NUM +1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
            arr[num] = true;
        }

        int ans = 0;
        for (int num : nums) {
            int pair = M - num;
            if (pair <= 0 || pair > MAX_INGREDIENT_NUM) {
                continue;
            }
            if (num != pair && arr[pair]) {
                ans++;
                arr[num] = false;
            }
        }

        System.out.println(ans);
    }
}