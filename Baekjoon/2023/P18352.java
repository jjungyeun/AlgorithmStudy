import java.io.*;
import java.util.*;

public class P18352 {
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 인접 리스트 생성
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adjList.get(s).add(d);
        }

        // BFS로 각 노드까지의 최단거리 구하기
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{X, 0});
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        while (!queue.isEmpty()) {
            int[] here = queue.poll();
            int hereNode = here[0];
            int hereDist = here[1];

            if (hereDist == K) break;

            for (Integer nextNode : adjList.get(hereNode)) {
                if (dist[nextNode] == Integer.MAX_VALUE) {
                    dist[nextNode] = hereDist+1;
                    queue.add(new int[] {nextNode, hereDist+1});
                }
            }
        }

        printResult(N, K, dist);
    }

    private static void printResult(int N, int K, int[] dist) throws IOException {
        boolean hasAnswer = false;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int node = 1; node <= N; node++) {
            if (dist[node] == K) {
                hasAnswer = true;
                bw.write(node + "\n");
            }
        }
        if (!hasAnswer) {
            bw.write("-1");
        }

        bw.flush();
        bw.close();
    }
}
