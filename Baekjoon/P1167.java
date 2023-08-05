import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1167 {

    static ArrayList<Edge>[] adjs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        adjs = new ArrayList[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int here = Integer.parseInt(st.nextToken());
            adjs[here] = new ArrayList<>();
            while (true) {
                int dest = Integer.parseInt(st.nextToken());
                if (dest == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                adjs[here].add(new Edge(dest, dist));
            }
        }

        Edge mostFarNode = getMostFarNode(1, N);
        Edge radiusNode = getMostFarNode(mostFarNode.target, N);
        System.out.println(radiusNode.dist);
    }

    private static Edge getMostFarNode(int start, int N) {
        boolean[] visited = new boolean[N + 1];
        Queue<Edge> queue = new LinkedList<>();
        queue.add(new Edge(start, 0));
        visited[start] = true;
        int mostFarNode = -1;
        long longestDist = 0;

        while (!queue.isEmpty()) {
            Edge here = queue.poll();

            if (here.dist > longestDist) {
                mostFarNode = here.target;
                longestDist = here.dist;
            }

            for (Edge next : adjs[here.target]) {
                if (!visited[next.target]) {
                    visited[next.target] = true;
                    queue.add(new Edge(next.target, here.dist + next.dist));
                }
            }
        }
        return new Edge(mostFarNode, longestDist);
    }

    private static class Edge {
        int target;
        long dist;

        public Edge(int target, long dist) {
            this.target = target;
            this.dist = dist;
        }
    }
}
