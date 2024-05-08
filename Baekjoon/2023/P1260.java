import java.io.*;
import java.util.*;

public class P1260 {

    private static int N;
    private static ArrayList<Integer>[] adjs;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

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
            Collections.sort(adjs[i]);
        }

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        dfs(V);
        bw.newLine();
        bfs(V);
        bw.flush();
        bw.close();
    }

    private static void dfs(int start) throws IOException {
        boolean[] visited = new boolean[N + 1];
        innerDfs(start, visited);
    }

    private static void innerDfs(int here, boolean[] visited) throws IOException {
        visited[here] = true;
        bw.write(here + " ");
        for (Integer next : adjs[here]) {
            if (!visited[next]) {
                innerDfs(next, visited);
            }
        }
    }

    private static void bfs(int start) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int here = queue.poll();
            bw.write(here + " ");

            for (Integer next : adjs[here]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
