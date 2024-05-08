import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] res = new int[N];
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int idx = 0; idx < N; idx++) {
            while (!stack.isEmpty() && A[stack.peek()] < A[idx]) {
                int topIdx = stack.pop();
                res[topIdx] = A[idx];
            }
            stack.push(idx);
        }

        while (!stack.isEmpty()) {
            int topIdx = stack.pop();
            res[topIdx] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
