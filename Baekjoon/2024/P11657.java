import java.io.*;
import java.util.*;

class P11657 {
    
    private static long[] distance;
    private static ArrayList<Pair> edges;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 벨만-포드 알고리즘에 필요한 거리 배열 초기화
        // 최솟값이 INT 범위를 벗어날 수 있으므로 long으로 선언
        distance = new long[N+1];
        for (int i = 1; i <= N; i++) {
            distance[i] = i == 1? 0: Long.MAX_VALUE;
        }

        // 엣지 배열 초기화
        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Pair(u, v, w));
        }

        // N-1번 순회하며 최솟값 구하기
        for (int t = 0; t < N-1; t++) {
            circuit();
        }

        // N번 순회했을 때 업데이트 되는 값이 있다면 사이클이 있는 것이므로 -1 반환
        if (circuit()) {
            System.out.println("-1");
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 2; i <= N; i++) {
                if (distance[i] == Long.MAX_VALUE) {
                    bw.write("-1\n");
                } else {
                    bw.write(distance[i] + "\n");
                }
            }
            bw.flush();
            bw.close();
        }
    }

    private static boolean circuit() {
        boolean updated = false;
        // 모든 엣지를 돌면서 업데이트
        for (Pair edge : edges) {
            if (distance[edge.s] == Long.MAX_VALUE) {
                continue;
            }

            // 현재 엣지를 지나는 경로가 더 가까우면 업데이트
            if (distance[edge.s] + edge.w < distance[edge.d]) {
                distance[edge.d] = distance[edge.s] + edge.w;
                updated = true;
            }
        }
        return updated;
    }

    private static class Pair {
        int s;
        int d;
        int w;

        public Pair(int s, int d, int w) {
            this.s = s;
            this.d = d;
            this.w = w;
        }
    }
}