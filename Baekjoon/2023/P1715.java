import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            int min1 = pq.poll();
            if (pq.isEmpty()) break;
            int min2 = pq.poll();
            ans += (min1 + min2);
            pq.add(min1+min2);
        }
        System.out.println(ans);
    }
}
