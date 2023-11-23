import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P231123_2 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edge));
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        int maxLen = 0;

        // 주어진 그래프 정보를 연결 리스트로 변환
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            int u = e[0] - 1, v = e[1] - 1; // 인덱스 0번부터로 변환
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // 1번 (index 0) 노드부터 BFS 수행
        Queue<Visited> queue = new LinkedList<>();
        int[] dists = new int[n];
        queue.add(new Visited(0, 0));
        dists[0] = -1;

        while(!queue.isEmpty()) {
            Visited here = queue.poll();

            // 아직 방문하지 않은 인접 노드 방문
            for (Integer nexts : adjList.get(here.node)) {
                if (dists[nexts] == 0) {
                    int nextDist = here.distance + 1;
                    maxLen = Integer.max(maxLen, nextDist);
                    dists[nexts] = nextDist;
                    queue.add(new Visited(nexts, nextDist));
                }
            }
        }

        // 가장 먼 노드 개수 구하기
        for (int dist : dists) {
            if (dist == maxLen) {
                answer++;
            }
        }

        return answer;
    }

    private static class Visited {
        int node;
        int distance;

        public Visited(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
