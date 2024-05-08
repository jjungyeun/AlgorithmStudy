import java.io.*;
import java.util.*;

public class P1707 {

    private static BufferedReader br;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int tc = 0; tc < T; tc++) {
            if (solve()) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        int[] group = new int[V+1];
        for (int node = 1; node <= V; node++) {
            if (group[node] == 0) {
                boolean splitable = checkSplitable(adjList, group, node);
                if (!splitable) return false;
            }
        }
        return true;
    }

    private static boolean checkSplitable(List<List<Integer>> adjList, int[] group, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        group[startNode] = 1;

        while (!queue.isEmpty()) {
            int hereNode = queue.poll();
            int hereGroup = group[hereNode];
            for (Integer nextNode : adjList.get(hereNode)) {
                if (group[nextNode] == 0) {
                    group[nextNode] = nextGroup(hereGroup);
                    queue.add(nextNode);
                } else if (group[nextNode] == hereGroup) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int nextGroup(int hereGroup) {
        return hereGroup == 1 ? 2 : 1;
    }
}
