import java.io.*;
import java.util.*;

class P1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 사이클 판별을 위한 유니온-파인드 배열 초기화
        int[] unionFind = new int[V+1];
        for (int i = 1; i <= V; i++) {
            unionFind[i] = i;
        }

        // 엣지 리스트 입력 및 초기화 후 가중치 오름차순으로 정렬
        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s, d, w);
        }
        Arrays.sort(edges);

        // 최소 엣지부터 연결
        // 연결 시 사이클이 생기는 경우 연결 안함
        int edgeCnt = 0;
        Long mstWeight = 0L;
        for (Edge edge : edges) {
            if (!isMakeCycle(unionFind, edge)) {
                union(unionFind, edge);
                edgeCnt++;
                mstWeight += edge.w;
            }

            // V개의 노드가 모두 연결된 경우 종료
            if (edgeCnt == V-1) {
                System.out.println(mstWeight);
                break;
            }
        }
    }

    private static int find(int[] arr, int a) {
        if (arr[a] != a) {
            return arr[a] = find(arr, arr[a]);
        }
        return arr[a];
    }

    private static void union(int[] arr, Edge edge) {
        arr[find(arr, edge.d)] = find(arr, edge.s);
    }

    private static boolean isMakeCycle(int[] arr, Edge edge) {
        return find(arr, edge.s) == find(arr, edge.d);
    }

    private static class Edge implements Comparable<Edge> {
        int s;
        int d;
        int w;

        public Edge(int s, int d, int w) {
            this.s = s;
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Edge other) {
            return this.w - other.w;
        }
    }
}