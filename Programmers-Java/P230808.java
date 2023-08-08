import java.util.*;

public class P230808 {
    public static void main(String[] args) {
        int[] sales = new int[20000];
        int n = 1000000;
        for (int i = 0; i < 20000; i++) {
            sales[i] = 500;
        }
        long ans = solution2(n, sales);
        System.out.println(ans);
    }

    public static long solution(int n, int[] works) {
        int W = works.length;

        // 작업량 정렬
        Integer[] sortedWorks = Arrays.stream(works).boxed()
                .toArray(Integer[]::new);
        Arrays.sort(sortedWorks, Collections.reverseOrder());

        while (n > 0) {
            int start = sortedWorks[0];

            if (start == 0) {
                break;
            }

            // 현재 상태에서 가장 큰값들을 1씩 줄임
            // ex) [5,5,3,2,1] -> [4,4,3,2,1]
            for (int idx = 0; idx < W; idx++) {
                if (n > 0 && sortedWorks[idx] == start) {
                    sortedWorks[idx]--;
                    n--;
                } else {
                    break;
                }
            }
        }

        // 최종 야근 지수 계산
        long ans = 0;
        for (int i = 0; i < W; i++) {
            ans += (long) sortedWorks[i] * sortedWorks[i];
        }

        return ans;
    }

    public static long solution2(int n, int[] works) {
        int W = works.length;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int j : works) {
            pq.add(j);
        }

        while (n > 0 && !pq.isEmpty()) {
            int top = pq.poll();
            if (top == 0) break;
            pq.add(top -1);
            n--;
        }

        long ans = 0;
        while (!pq.isEmpty()) {
            int work = pq.poll();
            ans += (long) work * work;
        }

        return ans;
    }
}
