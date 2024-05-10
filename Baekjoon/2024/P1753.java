import java.io.*;
import java.util.*;

class P1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[V+1];
        int[] distance = new int[V+1];
        ArrayList<Pair>[] adjList = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new Pair(v, w));
        }

        // 다익스트라 알고리즘
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Pair here = pq.poll();
            int hereN = here.v;
            int hereW = here.w;

            // 이미 방문한 노드 패스
            if (visited[hereN]) {
                continue;
            }

            // 현재 노드 방문처리
            visited[hereN] = true;

            // 현재 노드와 연결된 정점 탐색
            for (Pair edge : adjList[hereN]) {
                int next = edge.v;
                int nextCost = edge.w + hereW;
                // 현재 노드를 거쳐가는 경로가 지금까지의 최단거리보다 짧은 경우,
                // 다음 정점 거리 업데이트 및 큐에 추가
                if (distance[next] > nextCost) {
                    distance[next] = nextCost;
                    pq.add(new Pair(next, nextCost));
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i=1; i<=V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                bw.write("INF\n");
            } else {
                bw.write(distance[i] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static class Pair implements Comparable<Pair> {
        int v;
        int w;

        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Pair other) {
            return this.w - other.w;
        }
    }
}