public class P230810_2 {
    public static void main(String[] args) {
        int n = 200000000;
        int[] stations = {1, 11, 44};
        int w = 2;
        System.out.println(solution(n, stations, w));
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int signalWidth = w * 2 + 1;
        int lastSignal = 0; // 가장 마지막으로 전파가 닿은 곳

        for (int station : stations) {
            // 현재 기지국이 닿기 직전의 위치 (왼쪽)
            int noSignal = station - w - 1;

            // 전파가 닿지 않는 곳이 존재하는 경우
            if (lastSignal < noSignal) {
                // 전파가 안 닿는 길이에 필요한 기지국 수를 구함
                answer += getStationNumber(signalWidth, lastSignal, noSignal);
            }
            lastSignal = station + w;
        }

        // 맨 뒷부분 처리
        if (n - lastSignal > 0) {
            answer += getStationNumber(signalWidth, lastSignal, n);
        }

        return answer;
    }

    private static int getStationNumber(int signalWidth, int lastSignal, int noSignal) {
        int div = (noSignal - lastSignal) / signalWidth;
        int mod = (noSignal - lastSignal) % signalWidth;
        return mod == 0 ? div : div + 1;
    }
}
