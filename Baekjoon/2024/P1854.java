import java.io.*;
import java.util.*;

class P1854 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] visitCnt = new int[N+1];  // 해당 노드에 몇번 방문했는지
        int[] distances = new int[N+1]; // x번 방문하는 동안의 최단거리
        ArrayList<Node>[] adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adjList[u].add(new Node(v, t));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node hereNode = pq.poll();
            int hereV = hereNode.v, hereW = hereNode.w;

            // 현재 노드에 이미 K번 방문한 경우 넘어가기
            if (visitCnt[hereV] == K) {
                continue;
            }
            // 방문 처리 및 x번째 최단거리 업데이트
            visitCnt[hereV]++;
            distances[hereV] = Integer.max(distances[hereV], hereW);

            // 현재 노드와 연결된 노드 큐에 삽입
            for (Node nextNode : adjList[hereV]) {
                int nextV = nextNode.v;
                int nextW = nextNode.w + hereW;
                pq.add(new Node(nextV, nextW));
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i=1; i<=N; i++) {
            if (visitCnt[i] == K) {
                bw.write(distances[i] + "\n");
            } else {
                bw.write("-1\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static class Node implements Comparable<Node>{
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node other) {
            return w - other.w;
        }
    }
}