import java.io.*;
import java.util.*;

class P11003_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] results = new int[N];
        Deque<Integer[]> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            // 인덱스의 최소가 0이 되도록 left값 설정
            int left = Integer.max(0, i - L + 1);

            // 현재 deque의 마지막 값보다 새 숫자가 작으면 마지막 값 삭제 후 추가 (정렬 효과)
            while (!deque.isEmpty() && nums[i] < deque.getLast()[1]) {
                deque.removeLast();
            }
            deque.addLast(new Integer[]{i, nums[i]});

            // 현재 범위의 최솟값을 구하기 위해 범위 이전의 최솟값들 삭제
            while (deque.getFirst()[0] < left) {
                deque.removeFirst();
            }

            // 최솟값 저장
            results[i] = deque.getFirst()[1];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(results[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}