import java.io.*;
import java.util.*;

class P11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // (i->j)의 최단거리를 저장하는 배열
        // (i,i)를 제외하고 나머지를 큰 수로 초기화 (덧셈 비교가 있으므로 Long 최댓값을 하면 안됨)
        long[][] distances = new long[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 입력받은 엣지 값을 distances 배열에 업데이트 (=최단거리)
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            distances[s][d] = Long.min(distances[s][d], w);
        }

        // s->d보다 s->k->d가 가까운 경우 거리 배열 업데이트
        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= N; s++) {
                for (int d = 1; d <= N; d++) {
                    distances[s][d] = Long.min(distances[s][d], distances[s][k] + distances[k][d]);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (distances[i][j] == Integer.MAX_VALUE) {
                    bw.write("0 ");
                } else {
                    bw.write(distances[i][j] + " ");
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}