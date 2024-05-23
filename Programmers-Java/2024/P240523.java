import java.util.*;

class P240523 {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];

        // 가격 내림차순으로 정렬되는 우선순위큐
        PriorityQueue<Price> pq = new PriorityQueue<>();

        for(int t=0; t<N; t++) {
            int nowPrice = prices[t];

            // 현재 가격보다 높았던 때를 모두 pop
            while(!pq.isEmpty()) {
                if (pq.peek().p > nowPrice) {
                    Price price = pq.poll();
                    answer[price.t] = t - price.t;
                } else {
                    break;
                }
            }

            pq.add(new Price(t, nowPrice));
        }

        // pq에 남은 거(가격이 끝까지 떨어지지 않은 것) 모두 꺼내면서 정답 배열 업데이트
        while(!pq.isEmpty()) {
            Price price = pq.poll();
            answer[price.t] = N - price.t - 1;
        }

        return answer;
    }

    static class Price implements Comparable<Price>{
        int t;
        int p;

        public Price(int t, int p) {
            this.t = t;
            this.p = p;
        }

        @Override
        public int compareTo(Price other) {
            return other.p - this.p;
        }
    }
}