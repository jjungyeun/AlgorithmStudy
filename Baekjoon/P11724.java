import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adjs = new ArrayList[N+1];
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

        int ans = 0;
        boolean[] visited = new boolean[N+1];
        for (int node = 1; node < N + 1; node++) {
            if (visited[node]) continue;
            ans++;
            visited[node] = true;
            dfs(adjs, visited, node);
        }
        System.out.println(ans);
    }

    private static void dfs(ArrayList<Integer>[] adjs, boolean[] visited, int start) {
        for (int next : adjs[start]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(adjs, visited, next);
            }
        }
    }
}
