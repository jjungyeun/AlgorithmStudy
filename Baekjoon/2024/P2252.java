import java.io.*;
import java.util.*;

class P2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 위상 정렬에 필요한 자료구조 세팅
        int[] degrees = new int[N+1];
        ArrayList<Integer>[] adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 간선을 입력받으며 간선 연결 리스트 저장 및 정점 진입차수 증가
        for (int t = 0; t < M; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            degrees[b]++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> queue = new LinkedList<>();
        while (true) {
            // 진입차수가 0인 간선을 큐에 삽입
            for (int i = 1; i < N + 1; i++) {
                if (degrees[i] == 0) {
                    queue.add(i);
                    degrees[i] = -1;
                }
            }

            if (queue.isEmpty()) {
                break;
            }

            // 큐의 첫번째 정점을 방문하고 해당 정점에 연결된 간선 제거
            int top = queue.poll();
            bw.write(top + " ");
            for (int next : adjList[top]) {
                degrees[next]--;
            }
        }

        bw.flush();
        bw.close();
    }
}
