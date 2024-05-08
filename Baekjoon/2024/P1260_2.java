import java.io.*;
import java.util.*;

class P1260_2 {

    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] visited;
    private static ArrayList<Integer>[] adjs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        visited = new int[N+1];
        adjs = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adjs[s].add(d);
            adjs[d].add(s);
        }

        for (int i = 1; i <= N; i++) {
            adjs[i].sort(Comparator.naturalOrder());
        }


        visited[V] = 1;
        dfs(V);
        bw.write("\n");
        bfs(V);
        bw.flush();
        bw.close();
    }

    private static void dfs(int here) throws IOException {
        bw.write(here + " ");
        for (int next : adjs[here]) {
            if (visited[next] != 1) {
                visited[next] = 1;
                dfs(next);
            }
        }
    }

    private static void bfs(int start) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 2;
        bw.write(start + " ");

        while (!queue.isEmpty()) {
            int here = queue.poll();

            for (int next : adjs[here]) {
                if (visited[next] != 2) {
                    visited[next] = 2;
                    bw.write(next + " ");
                    queue.add(next);
                }
            }
        }
    }
}