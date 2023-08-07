import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2343 {

    private static int N, M;
    private static int[] playTimes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        playTimes = new int[N];
        int longestTime = 0;
        int totalTime = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int time = Integer.parseInt(st.nextToken());
            playTimes[i] = time;
            totalTime += time;
            if (time > longestTime) longestTime = time;
        }

        int minSize = getMinSize(longestTime, totalTime);
        System.out.println(minSize);

    }

    private static int getMinSize(int minTime, int maxTime) {
        if (minTime > maxTime) return minTime;

        int midTime = (minTime + maxTime) / 2;
        if (canRecord(midTime)) {
            return getMinSize(minTime, midTime-1);
        } else {
            return getMinSize(midTime+1, maxTime);
        }
    }

    private static boolean canRecord(int size) {
        int bluelayCnt = 1;
        int currentSize = 0;
        for (int playTime : playTimes) {
            if (currentSize + playTime > size) {
                bluelayCnt++;
                currentSize = 0;
            }
            currentSize += playTime;
        }
        return bluelayCnt <= M;
    }
}
