import java.util.*;
import java.io.*;

class P11724_2 {

    private static int[][] adjs;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        adjs = new int[V+1][V+1];
        visited = new boolean[V+1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adjs[s][d] = 1; adjs[d][s] = 1;
        }

        int answer = 0;
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                answer++;
                visited[i] = true;
                dfs(i);
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int here) {
        for (int next = 1; next < adjs.length; next++) {
            if (adjs[here][next] == 1 && !visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }

}