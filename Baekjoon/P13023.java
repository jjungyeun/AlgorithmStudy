import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023 {

    private static boolean isExist = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] friendships = new ArrayList[N+1];
        for (int i = 0; i < N; i++) {
            friendships[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            friendships[s].add(d);
            friendships[d].add(s);
        }

        boolean[] visited = new boolean[N+1];
        for (int i = 0; i < N; i++) {
            dfs(friendships, visited, i, 1);
            if (isExist) break;
        }

        System.out.println(isExist? 1 : 0);

    }

    private static void dfs(ArrayList<Integer>[] adjs, boolean[] visited, int start, int adjLen) {
        if (isExist || adjLen == 5) {
            isExist = true;
            return;
        }
        visited[start] = true;
        for (int next : adjs[start]) {
            if (!visited[next]) {
                dfs(adjs, visited, next, adjLen+1);
            }
        }
        visited[start] = false;
    }
}
