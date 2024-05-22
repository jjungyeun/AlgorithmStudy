import java.util.*;

class P240522 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int N = truck_weights.length;

        Queue<Truck> trucks = new LinkedList<>();
        int t = 0, idx = 0; // t: 현재 시간, idx: 다음으로 건널 트럭 인덱스
        int restT = N;      // 아직 건너지 않은 트럭 수
        int currentW = 0;   // 현재 다리에 올라간 트럭 무게의 합

        while(restT > 0) {
            // 1초 지남
            t++;

            // 1. 다리 위의 맨 앞 트럭이 도착한지 확인
            // 트럭이 들어간 시간과 현재 시간의 차이가 다리 길이보다 길면 pop
            if (!trucks.isEmpty()) {
                Truck first = trucks.peek();
                if (t - first.enterTime >= bridge_length) {
                    restT--;
                    currentW -= first.weight;
                    trucks.poll();
                }
            }

            // 2. 다리에 새 트럭을 추가할 수 있는지 확인
            // 다음 트럭이 올라가도 최대 하중을 넘지 않으면 push
            if (idx < N && currentW + truck_weights[idx] <= weight) {
                trucks.add(new Truck(truck_weights[idx], t));
                currentW += truck_weights[idx];
                idx++;
            }
        }

        return t;
    }

    static class Truck {
        int weight;
        int enterTime;

        public Truck(int weight, int enterTime) {
            this.weight = weight;
            this.enterTime = enterTime;
        }
    }
}